package br.com.localhost8080.findyourbus.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.localhost8080.findyourbus.R;

public class BusDepartureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_departure);

        initializeBusDescription();
        initializeTabLayout();
    }

    private void initializeBusDescription() {
        String busDescription = getIntent().getStringExtra(BusActivity.BUS_DESCRIPTION);

        TextView textBusDescription = (TextView) findViewById(R.id.text_bus_description);
        textBusDescription.setText(busDescription);
    }

    private void initializeTabLayout() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new DepartureListFragmentPagerAdapter(getSupportFragmentManager(), BusDepartureActivity.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_calendar);
        tabLayout.setupWithViewPager(viewPager);
    }


}
