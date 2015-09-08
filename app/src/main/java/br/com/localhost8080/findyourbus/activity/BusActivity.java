package br.com.localhost8080.findyourbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.localhost8080.findyourbus.R;
import br.com.localhost8080.findyourbus.dto.BusDTO;
import br.com.localhost8080.findyourbus.task.BusTask;

public class BusActivity extends AppCompatActivity {

    public final static String BUS_ID = "br.com.localhost8080.findyourbus.BUS_ID";
    public final static String BUS_DESCRIPTION = "br.com.localhost8080.findyourbus.BUS_DESCRIPTION";

    private List<BusDTO> busList;
    private ArrayAdapter<BusDTO> busListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        initializeListView();
    }

    private void initializeListView() {
        ListView listView = (ListView) findViewById(R.id.list_bus);

        try {
            this.busList = new BusTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.busListAdapter = new ArrayAdapter<BusDTO>(this, android.R.layout.simple_list_item_1, android.R.id.text1, busList);
        listView.setAdapter(busListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BusDTO busDTO = (BusDTO) ((ListView) parent).getItemAtPosition(position);
                System.out.println(busDTO);
                System.out.println(busDTO.getId());

                Intent intent = new Intent(getActivityInstanceForIntents(), BusDetailActivity.class);
                intent.putExtra(BUS_ID, busDTO.getId());
                intent.putExtra(BUS_DESCRIPTION, busDTO.toString());
                startActivity(intent);
            }
        });
    }



    public void search(View view) {
        EditText editSearch = (EditText) findViewById(R.id.edit_search);
        String param = editSearch.getText().toString();
        this.updateListView(param);
    }

    private void updateListView(String param) {
        try {
            this.busList.clear();
            this.busList.addAll(new BusTask().execute(param).get());
            this.busListAdapter.notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Workaround to pass the instance of Activity to the Intent
     * TODO: research how is the most common way to get the instance
     */
    private BusActivity getActivityInstanceForIntents() {
        return this;
    }
}

