package br.com.localhost8080.findyourbus.task;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.localhost8080.findyourbus.dto.BusDTO;
import br.com.localhost8080.findyourbus.dto.BusListDTO;
import br.com.localhost8080.findyourbus.rest.BusQueriesRestClient;

public class BusTask extends AsyncTask<String, Void, List> {

    private BusQueriesRestClient restClient = new BusQueriesRestClient();
    private String urlString = "https://api.appglu.com/v1/queries/findRoutesByStopName/run";

    @Override
    protected List doInBackground(String... params) {
        List<BusDTO> values = new ArrayList<BusDTO>();

        String param = (params.length > 0) ? params[0] : "";
        String body = "{\"params\": {\"stopName\": \"%" + param + "%\"}}";

        String jsonReturn = restClient.doPost(urlString, body);

        try {
            BusListDTO busListDTO = new ObjectMapper().readValue(jsonReturn, BusListDTO.class);
            values.addAll(busListDTO.getRows());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }
}