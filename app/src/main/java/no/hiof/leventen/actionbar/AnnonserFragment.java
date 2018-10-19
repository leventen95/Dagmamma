package no.hiof.leventen.actionbar;


import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


public class AnnonserFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Person> personList;
    RecyclerViewAdapter recyclerViewAdapter;

    public AnnonserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment_annonser = inflater.inflate(R.layout.fragment_annonser, container, false);
        recyclerView = (RecyclerView) fragment_annonser.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        initializeData();
        initializeAdapter();
        return fragment_annonser;
    }
    private void initializeData(){
        personList = new ArrayList<>();
        personList.add(new Person("Emma Watson",29,R.drawable.person));
        personList.add(new Person("Scarlet Johansson",33,R.drawable.person));
        personList.add(new Person("Keanu Reeves",54,R.drawable.person));
        personList.add(new Person("Sandra Bullock",54,R.drawable.person));
        personList.add(new Person("Sandra Bullock",54,R.drawable.person));
        personList.add(new Person("Keanu Reeves",54,R.drawable.person));
        personList.add(new Person("Keanu Reeves",54,R.drawable.person));
        personList.add(new Person("Sandra Bullock",54,R.drawable.person));
    }
    private void initializeAdapter(){
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(personList);
        recyclerView.setAdapter(adapter);
    }

}
