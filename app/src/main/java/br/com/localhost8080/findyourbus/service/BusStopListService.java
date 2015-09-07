package br.com.localhost8080.findyourbus.service;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.localhost8080.findyourbus.dto.BusStopDTO;
import br.com.localhost8080.findyourbus.dto.BusStopListDTO;
import br.com.localhost8080.findyourbus.rest.BusQueriesRestClient;

public class BusStopListService extends AsyncTask<String, Void, List> {

    private BusQueriesRestClient restClient = new BusQueriesRestClient();
    private String urlString = "https://api.appglu.com/v1/queries/findStopsByRouteId/run";

    @Override
    protected List doInBackground(String... params) {
        List<BusStopDTO> values = new ArrayList<BusStopDTO>();

        String body = "{\"params\": {\"routeId\":" + params[0] + "}}";

        String jsonReturn = restClient.doPost(urlString, body);

        try {
            BusStopListDTO busStopListDTO = new ObjectMapper().readValue(jsonReturn, BusStopListDTO.class);
            values.addAll(busStopListDTO.getRows());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }
}