package br.com.localhost8080.findyourbus.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.localhost8080.findyourbus.R;

public class BusDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);

        initializeBusDescription();
    }

    private void initializeBusDescription() {
        String busDescription = getIntent().getStringExtra(BusActivity.BUS_DESCRIPTION);

        TextView textBusDescription = (TextView) findViewById(R.id.text_bus_description);
        textBusDescription.setText(busDescription);
    }


    public void goToDepartures(View view) {
        Long busId = getIntent().getLongExtra(BusActivity.BUS_ID, 0L);
        String busDescription = getIntent().getStringExtra(BusActivity.BUS_DESCRIPTION);

        Intent intent = new Intent(this, BusDepartureActivity.class);
        intent.putExtra(BusActivity.BUS_ID, busId);
        intent.putExtra(BusActivity.BUS_DESCRIPTION, busDescription);
        startActivity(intent);
    }

    public void goToStops(View view) {
        Long busId = getIntent().getLongExtra(BusActivity.BUS_ID, 0L);
        String busDescription = getIntent().getStringExtra(BusActivity.BUS_DESCRIPTION);

        Intent intent = new Intent(this, BusStopActivity.class);
        intent.putExtra(BusActivity.BUS_ID, busId);
        intent.putExtra(BusActivity.BUS_DESCRIPTION, busDescription);
        startActivity(intent);
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
