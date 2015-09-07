package br.com.localhost8080.findyourbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.localhost8080.findyourbus.R;
import br.com.localhost8080.findyourbus.listener.BusListListener;
import br.com.localhost8080.findyourbus.service.BusListService;

public class BusActivity extends AppCompatActivity {

    private List<String> busList;
    private ArrayAdapter<String> busListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        initializeListView();
    }

    private void initializeListView() {
        ListView listView = (ListView) findViewById(R.id.list);

        try {
            this.busList = new BusListService().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.busListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, busList);
        listView.setAdapter(busListAdapter);
        listView.setOnItemClickListener(new BusListListener());
    }

    private void updateListView(String param) {
        try {
            this.busList.clear();
            this.busList.addAll(new BusListService().execute(param).get());
            this.busListAdapter.notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public void search(View view) {
        EditText editSearch = (EditText) findViewById(R.id.edit_search);
        String param = editSearch.getText().toString();
        this.updateListView(param);
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

