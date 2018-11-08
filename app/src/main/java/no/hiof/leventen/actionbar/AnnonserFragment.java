package no.hiof.leventen.actionbar;


import android.content.Intent;
import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        personList = Person.getData();
        /*personList.add(new Person("Emma Watson",29,R.drawable.person));
        personList.add(new Person("Scarlet Johansson",33,R.drawable.person));
        personList.add(new Person("Keanu Reeves",54,R.drawable.person));
        personList.add(new Person("Sandra Bullock",54,R.drawable.person));
        personList.add(new Person("Sandra Bullock",54,R.drawable.person));
        personList.add(new Person("Keanu Reeves",54,R.drawable.person));
        personList.add(new Person("Keanu Reeves",54,R.drawable.person));
        personList.add(new Person("Sandra Bullock",54,R.drawable.person));*/
    }
    private void initializeAdapter(){
        RecyclerViewAdapter.RecyclerViewClickListener clickListener = new RecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), personList.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), PersonDetailedActivity.class);
                intent.putExtra(PersonDetailedActivity.PERSON_ID_KEY, 1);
                startActivity(intent);
            }
        };

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(personList, clickListener);
        recyclerView.setAdapter(adapter);

    }

}
