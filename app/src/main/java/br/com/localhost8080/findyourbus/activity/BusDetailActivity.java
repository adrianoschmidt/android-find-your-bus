package br.com.localhost8080.findyourbus.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.localhost8080.findyourbus.R;
import br.com.localhost8080.findyourbus.dto.BusDTO;
import br.com.localhost8080.findyourbus.dto.BusDepartureDTO;
import br.com.localhost8080.findyourbus.dto.BusStopDTO;
import br.com.localhost8080.findyourbus.service.BusDepartureListService;
import br.com.localhost8080.findyourbus.service.BusListService;
import br.com.localhost8080.findyourbus.service.BusStopListService;

public class BusDetailActivity extends AppCompatActivity {

    private List<BusStopDTO> busStopList;
    private ArrayAdapter<BusStopDTO> busStopListAdapter;

    private List<BusDepartureDTO> busDepartureList;
    private ArrayAdapter<BusDepartureDTO> busDepartureListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);

        initializeBusDescription();
        initializeDepartureListView();
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
            this.busStopList = new BusStopListService().execute(busId.toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.busStopListAdapter = new ArrayAdapter<BusStopDTO>(this, android.R.layout.simple_list_item_1, android.R.id.text1, busStopList);
        listView.setAdapter(busStopListAdapter);
    }

    private void initializeDepartureListView() {
        Long busId = getIntent().getLongExtra(BusActivity.BUS_ID, 0L);

        ListView listView = (ListView) findViewById(R.id.list_bus_departure);

        try {
            this.busDepartureList = new BusDepartureListService().execute(busId.toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.busDepartureListAdapter = new ArrayAdapter<BusDepartureDTO>(this, android.R.layout.simple_list_item_1, android.R.id.text1, busDepartureList);
        listView.setAdapter(busDepartureListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
