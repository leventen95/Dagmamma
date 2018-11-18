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

import no.hiof.leventen.actionbar.Classes.UserType;
import no.hiof.leventen.actionbar.Firebasehandler.DidGetUsersCallBack;
import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;


public class AnnonserFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Person> userList;
    RecyclerViewAdapter recyclerViewAdapter;
    FirebaseDatasource datasource;
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

        datasource = new FirebaseDatasource();

        initializeData();

        return fragment_annonser;
    }
    private void initializeData(){
        userList = new ArrayList<>();
        datasource.getAllUsers(UserType.DAGMAMMA, new DidGetUsersCallBack() {
            @Override
            public void didRecieve(List<Person> personList) {
                userList = personList;
                initializeAdapter();
            }
        });

    }
    private void initializeAdapter(){
        RecyclerViewAdapter.RecyclerViewClickListener clickListener = new RecyclerViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), userList.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), PersonDetailedActivity.class);
                intent.putExtra(PersonDetailedActivity.PERSON_ID_KEY, 1);
                startActivity(intent);
            }
        };

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(userList, clickListener);
        recyclerView.setAdapter(adapter);

    }

}
