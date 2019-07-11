package com.eywa_kitchen.eywalauncher.Weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.eywa_kitchen.eywalauncher.MainScreen.MainModel;

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

public class getWeatherTask extends AsyncTask<Void, Void, Void> {



    private final MainModel.WeatherCallback callback;
    private String WeatherDegrees;
    private Bitmap WeatherBitmap;
    private boolean getIsGood;



    public getWeatherTask(MainModel.WeatherCallback callback) {

        this.callback = callback;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        WeatherBitmap=null;
        WeatherDegrees=null;
        getIsGood=true;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String stream = getJSONstream();
            Log.e("STREAM ", stream);
            parseJSON(stream);
            Log.e("Weather ", WeatherDegrees);
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
            callback.Received(WeatherBitmap, WeatherDegrees);
        }
    }

    private String getJSONstream(){
        URL url = null;
        String URL = "http://api.apixu.com/v1/current.json?key=148bc58bb39d48b389390051190606&q=Moscow";
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
            callback.Loading();
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
        try{
            JSONObject jsonObject = new JSONObject(JsonInput);

            JSONObject data = jsonObject.getJSONObject("current");
            JSONObject resources = data.getJSONObject("condition");

            String imageurl = resources.getString("icon");
            String temp = data.getString("temp_c");
            Log.e("JSON parser", "Res " + temp + " " + imageurl);
            WeatherBitmap = getBitmapFromURL("http://" + imageurl);
            WeatherDegrees = temp;
        }catch (JSONException e){
            Log.e("JSON parser", "JSON error" + e.getMessage());
        }
    }

    private static Bitmap getBitmapFromURL(String src) {
        try {
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
            return null;
        }
    }


}
