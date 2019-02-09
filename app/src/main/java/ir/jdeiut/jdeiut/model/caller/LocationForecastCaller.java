package ir.jdeiut.jdeiut.model.caller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Calendar;
import java.util.Date;

import ir.jdeiut.jdeiut.LocationForecastActivity;
import ir.jdeiut.jdeiut.MainActivity;
import ir.jdeiut.jdeiut.model.entities.LocationForecastEntity;
import ir.jdeiut.jdeiut.model.entities.SearchEntity;

public class LocationForecastCaller extends AsyncTask<Integer,Void,String>{

    OnLocationForecastReceived onLocationForecastReceived = null;


    public void setOnLocationForecastReceived(OnLocationForecastReceived onLocationForecastReceived) {
        this.onLocationForecastReceived = onLocationForecastReceived;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... inputs) {
        if(inputs.length<1) return null;
            int woeid = inputs[0];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String sDate = sdf.format(new Date());


        String webServiceAddress =
                "https://www.metaweather.com/api/location/" + woeid + "/"+ "2017/05/05";
        String output = "";
        HttpURLConnection con = null;
        try {
            URL url = new URL(webServiceAddress);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            if (con.getResponseCode() != 200) {
                throw new Exception("Error");
            }
            InputStreamReader isr = new InputStreamReader(con.getInputStream());
            BufferedReader br = new BufferedReader(isr);

            String temp;
            while ((temp = br.readLine()) != null) {
                output += temp;
            }

        } catch (Exception ex) {
            output = null;
            ex.printStackTrace();
        } finally {
            if (con != null)
                con.disconnect();
        }
        return output;

    }

    @Override
    protected void onPostExecute(String s) {
        Gson gson = new Gson();
        LocationForecastEntity[] results = gson.fromJson(s, LocationForecastEntity[].class);

        if(this.onLocationForecastReceived!=null)
             onLocationForecastReceived.onlocationForecastReceived(results);

        super.onPostExecute(s);
    }

}
