package ir.jdeiut.jdeiut.model.caller;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ir.jdeiut.jdeiut.model.entities.SearchEntity;

public class SearchCaller extends AsyncTask<String, Void, String> {
    OnSearchResultReceived onSearchResultReceived=null;

    public void setOnSearchResultReceived(OnSearchResultReceived onSearchResultReceived) {
        this.onSearchResultReceived = onSearchResultReceived;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... inputs) {
        if (inputs.length < 1) return null;
        String city = inputs[0];

        String webServiceAddress =
                "https://www.metaweather.com/api/location/search/?query=" + city;
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
        super.onPostExecute(s);
        //convert to java object
        Gson gson = new Gson();
        SearchEntity[] results = gson.fromJson(s, SearchEntity[].class);
        if(this.onSearchResultReceived!=null)
            onSearchResultReceived.onSearchResultReceived(results);
    }
}
