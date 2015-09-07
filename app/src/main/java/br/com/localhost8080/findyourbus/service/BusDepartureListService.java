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

import br.com.localhost8080.findyourbus.dto.BusDepartureDTO;
import br.com.localhost8080.findyourbus.dto.BusDepartureListDTO;

public class BusDepartureListService extends AsyncTask<String, Void, List> {

    private String urlString = "https://api.appglu.com/v1/queries/findDeparturesByRouteId/run";
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
        return this.doPost(params[0]);
    }

    public List<BusDepartureDTO> doPost(String param) {
        String body = "{\"params\": {\"routeId\":" + param + "}}";

        List<BusDepartureDTO> values = new ArrayList<BusDepartureDTO>();

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

            BusDepartureListDTO busDepartureListDTO = new ObjectMapper().readValue(jsonReturn, BusDepartureListDTO.class);

            values.addAll(busDepartureListDTO.getRows());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }
}