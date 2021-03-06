package br.com.localhost8080.findyourbus.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.localhost8080.findyourbus.R;
import br.com.localhost8080.findyourbus.dto.BusStopDTO;
import br.com.localhost8080.findyourbus.task.BusStopTask;

public class BusStopActivity extends AppCompatActivity {

    private List<BusStopDTO> busStopList;
    private ArrayAdapter<BusStopDTO> busStopListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop);

        initializeBusDescription();
        initializeStopListView();
    }

    private void initializeBusDescription() {
        String busDescription = getIntent().getStringExtra(BusActivity.BUS_DESCRIPTION);

        TextView textBusDescription = (TextView) findViewById(R.id.text_bus_description);
        textBusDescription.setText(busDescription);
    }

    private void initializeStopListView() {
        Long busId = getIntent().getLongExtra(BusActivity.BUS_ID, 0L);

        ListView listView = (ListView) findViewById(R.id.list_bus_stop);

        try {
            this.busStopList = new BusStopTask().execute(busId.toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.busStopListAdapter = new ArrayAdapter<BusStopDTO>(this, android.R.layout.simple_list_item_1, android.R.id.text1, busStopList);
        listView.setAdapter(busStopListAdapter);
    }
}
