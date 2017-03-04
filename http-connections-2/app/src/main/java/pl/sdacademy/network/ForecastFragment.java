package pl.sdacademy.network;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import pl.sdacademy.network.dto.Person;

public class ForecastFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PersonAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        adapter = new PersonAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        onRefresh();

        return rootView;
    }

    @Override
    public void onRefresh() {
        List<Person> data = getData();
        adapter.setData(data);
        swipeRefreshLayout.setRefreshing(false);
    }

    private List<Person> getData() {
        Person person = new Person();
        person.setFirstName("Jan");
        person.setLastName("Kowalski");
        person.setBirthDay(new DateTime(2000, 1, 1, 0, 0));
        person.setPhoneNumber("+4812345678");
        person.setEmail("adres@email.pl");

        List<Person> list = new ArrayList();
        list.add(person);
        list.add(person);
        list.add(person);

        return list;
    }
}
