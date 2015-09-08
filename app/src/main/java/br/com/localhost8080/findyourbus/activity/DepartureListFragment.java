package br.com.localhost8080.findyourbus.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.localhost8080.findyourbus.R;
import br.com.localhost8080.findyourbus.dto.BusDepartureCalendarEnum;
import br.com.localhost8080.findyourbus.dto.BusDepartureDTO;
import br.com.localhost8080.findyourbus.task.BusDepartureTask;

public class DepartureListFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private List<BusDepartureDTO> busDepartureList;
    private ArrayAdapter<BusDepartureDTO> busDepartureListAdapter;

    public static DepartureListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DepartureListFragment fragment = new DepartureListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_departure_list, container, false);

        this.initializeDepartureListView(view);

        return view;
    }

    /**
     * This method calls the rest service and filters the list every time that a tab is selected
     * TODO: change it to call the rest service once time
     */
    private void initializeDepartureListView(View view) {
        Long busId = this.getActivity().getIntent().getLongExtra(BusActivity.BUS_ID, 0L);

        ListView listView = (ListView) view.findViewById(R.id.list_bus_departure);

        try {
            this.busDepartureList = new BusDepartureTask().execute(busId.toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        BusDepartureCalendarEnum filter = BusDepartureCalendarEnum.values()[mPage-1];
        Iterator<BusDepartureDTO> i = this.busDepartureList.iterator();
        while (i.hasNext()) {
            if (i.next().getCalendar() != filter) {
                i.remove();
            }
        }

        this.busDepartureListAdapter = new ArrayAdapter<BusDepartureDTO>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, busDepartureList);
        listView.setAdapter(busDepartureListAdapter);
    }

}