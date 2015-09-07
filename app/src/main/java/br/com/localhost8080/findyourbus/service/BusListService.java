package br.com.localhost8080.findyourbus.service;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import br.com.localhost8080.findyourbus.dto.BusDTO;
import br.com.localhost8080.findyourbus.dto.BusListDTO;

public class BusListService extends AsyncTask<String, Void, List> {

    private String urlString = "https://api.appglu.com/v1/queries/findRoutesByStopName/run";
    private String authEncoded = "V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==";

    private String param1key = "X-AppGlu-Environment";
    private String param1value = "staging";

    @Override
    protected List doInBackground(String... params) {
        List<String> values = this.doPost();
        return values;
    }

    public List<String> doPost() {
        List<String> values = new ArrayList<String>();

        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + authEncoded);
            conn.setRequestProperty(param1key, param1value);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");

            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(inputStreamReader);

            String retornoRest = br.readLine();

            BusListDTO busListDTO = new ObjectMapper().readValue(retornoRest, BusListDTO.class);

            for (BusDTO bus : busListDTO.getRows()) {
                values.add(bus.getShortName() + " - " + bus.getLongName());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }
}