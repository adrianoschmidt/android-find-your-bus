package br.com.localhost8080.findyourbus.activity;

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
import br.com.localhost8080.findyourbus.dto.BusDepartureDTO;
import br.com.localhost8080.findyourbus.service.BusDepartureListService;

public class BusDepartureActivity extends AppCompatActivity {

    private List<BusDepartureDTO> busDepartureList;
    private ArrayAdapter<BusDepartureDTO> busDepartureListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_departure);

        initializeBusDescription();
        initializeDepartureListView();
    }

    private void initializeBusDescription() {
        String busDescription = getIntent().getStringExtra(BusActivity.BUS_DESCRIPTION);

        TextView textBusDescription = (TextView) findViewById(R.id.text_bus_description);
        textBusDescription.setText(busDescription);
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
