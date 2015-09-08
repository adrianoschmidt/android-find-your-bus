package br.com.localhost8080.findyourbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        this.goTo(BusDepartureActivity.class);
    }

    public void goToStops(View view) {
        this.goTo(BusStopActivity.class);
    }

    public void goTo(Class activityClass) {
        Long busId = getIntent().getLongExtra(BusActivity.BUS_ID, 0L);
        String busDescription = getIntent().getStringExtra(BusActivity.BUS_DESCRIPTION);

        Intent intent = new Intent(this, activityClass);
        intent.putExtra(BusActivity.BUS_ID, busId);
        intent.putExtra(BusActivity.BUS_DESCRIPTION, busDescription);
        startActivity(intent);
    }

}
