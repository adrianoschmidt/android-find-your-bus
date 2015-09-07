package br.com.localhost8080.findyourbus.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class BusListListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // ListView Clicked item index
        int itemPosition = position;

        // ListView Clicked item value
        String itemValue = (String) ((ListView) view).getItemAtPosition(position);

//        // Show Alert
//        Toast.makeText(getApplicationContext(),
//                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
//                .show();

    }


}
