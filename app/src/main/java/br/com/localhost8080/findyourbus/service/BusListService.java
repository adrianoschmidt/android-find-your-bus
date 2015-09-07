package br.com.localhost8080.findyourbus.service;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    /**
     * Username:Password encoded in Base64 (https://www.base64encode.org/)
     */
    private String authEncoded = "V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==";

    private String param1key = "X-AppGlu-Environment";
    private String param1value = "staging";

    private String param2key = "Accept";
    private String param2value = "application/json";

    private String param3key = "Content-Type";
    private String param3value = "application/json";

    @Override
    protected List doInBackground(String... params) {
        if (params.length > 0) {
            return this.doPost(params[0]);
        } else {
            return this.doPost("");
        }
    }

    public List<String> doPost(String param) {
        String body = "{\"params\": {\"stopName\": \"%" + param + "%\"}}";

        List<String> values = new ArrayList<String>();

        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + authEncoded);
            conn.setRequestProperty(param1key, param1value);
            conn.setRequestProperty(param2key, param2value);
            conn.setRequestProperty(param3key, param3value);
            conn.setRequestMethod("POST");

            conn.setDoOutput(true);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(body);
            out.close();

            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(inputStreamReader);

            String jsonReturn = br.readLine();

            BusListDTO busListDTO = new ObjectMapper().readValue(jsonReturn, BusListDTO.class);

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