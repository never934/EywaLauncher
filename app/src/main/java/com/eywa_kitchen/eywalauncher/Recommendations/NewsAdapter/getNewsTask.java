package com.eywa_kitchen.eywalauncher.Recommendations.NewsAdapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.eywa_kitchen.eywalauncher.MainScreen.MainModel;
import com.eywa_kitchen.eywalauncher.MainScreen.MainView;
import com.eywa_kitchen.eywalauncher.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class getNewsTask extends AsyncTask<Void, Void, Void> {

    private boolean getIsGood;
    private List<News> News;
    private final MainModel.NewsCallback callback;



    public getNewsTask(MainModel.NewsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        News = new ArrayList<News>();
        getIsGood=true;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String stream = getJSONstream();
            Log.e("STREAM ", stream);
            parseJSON(stream);
        }catch (Exception e){
            getIsGood=false;
            callback.Error();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(getIsGood) {
            NewsAdapter NewsAdapter = new NewsAdapter(News);
            callback.Received(NewsAdapter);
        }
    }

    private String getJSONstream(){
        URL url = null;
        String URL = "https://newsapi.org/v2/top-headlines?country=ru&apiKey=27d1abc5d9c94d23b4ef18ea475dd108";
        String stream = "";
        try {
            //create url object to point to the file location on internet
            url = new URL(URL);
            //make a request to server
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //get InputStream instance
            InputStream is = con.getInputStream();
            //create BufferedReader object
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            //read content of the file line by line
            while ((line = br.readLine()) != null) {
                stream += line;

            }
            br.close();
            String JsonInput = stream;
            Log.e("Weather receiver: ", JsonInput);
            return stream;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void parseJSON(String JsonInput)
    {
        try {
            JSONObject jsonObject = new JSONObject(JsonInput);
            JSONArray articles = jsonObject.getJSONArray("articles");
            int count = articles.length();
            for (int currentNews=0; currentNews<count; currentNews++){

                News newsItem = new News();

                JSONObject newsObject = articles.getJSONObject(currentNews);
                JSONObject source = newsObject.getJSONObject("source");

                newsItem.title = newsObject.getString("title");
                newsItem.ClickUrl = newsObject.getString("url");
                newsItem.publisher = source.getString("name");
                newsItem.image = getBitmapFromURL(newsObject.getString("urlToImage"));
                News.add(newsItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON PARSING ", "ERR " + e.getMessage());
        }
    }

    private static Bitmap getBitmapFromURL(String src) {
        try {
            if((!(src.contains("http"))) || (!(src.contains("https"))))
            {
                src = "https:" + src;
            }
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            connection.disconnect();
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return BitmapFactory.decodeResource(MainView.MainContext.getResources(),R.drawable.blue);
        }
    }


}