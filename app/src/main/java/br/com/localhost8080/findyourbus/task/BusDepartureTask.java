package br.com.localhost8080.findyourbus.task;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.localhost8080.findyourbus.dto.BusDepartureDTO;
import br.com.localhost8080.findyourbus.dto.BusDepartureListDTO;
import br.com.localhost8080.findyourbus.rest.BusQueriesRestClient;

public class BusDepartureTask extends AsyncTask<String, Void, List> {

    private BusQueriesRestClient restClient = new BusQueriesRestClient();
    private String urlString = "https://api.appglu.com/v1/queries/findDeparturesByRouteId/run";

    @Override
    protected List doInBackground(String... params) {
        List<BusDepartureDTO> values = new ArrayList<BusDepartureDTO>();

        String body = "{\"params\": {\"routeId\":" + params[0] + "}}";

        String jsonReturn = restClient.doPost(urlString, body);

        try {
            BusDepartureListDTO busDepartureListDTO = new ObjectMapper().readValue(jsonReturn, BusDepartureListDTO.class);
            values.addAll(busDepartureListDTO.getRows());
            Collections.sort(values);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }


}