package no.hiof.leventen.actionbar;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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
    private EditText searchField; //
    private ImageButton searchBtn;//
    private RecyclerView recyclerView1;//
    private DatabaseReference mUserDatabase;
    //private CheckBox byCheckBox, navnCheckBox;
    private int clicked = 0;
    public RadioGroup radioGroup,radioGroupForeldre;
    public RadioButton radio_by,radio_navn,radio_dagmamma,radio_foreldre;
    public int radio_checked = 1;


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

        searchField = (EditText) fragment_annonser.findViewById(R.id.search_field);//
        searchBtn = (ImageButton) fragment_annonser.findViewById(R.id.imageButton);//
        radioGroup = (RadioGroup) fragment_annonser.findViewById(R.id.radioGroup);
        radioGroupForeldre = (RadioGroup) fragment_annonser.findViewById(R.id.radioGroupForeldre);
        radio_dagmamma = (RadioButton) fragment_annonser.findViewById(R.id.radio_Dagmamma);
        radio_foreldre = (RadioButton) fragment_annonser.findViewById(R.id.radio_Foreldre);
        radio_by = (RadioButton) fragment_annonser.findViewById(R.id.radio_by);
        radio_navn = (RadioButton) fragment_annonser.findViewById(R.id.radio_navn);

        radio_dagmamma.setChecked(true);

        datasource = new FirebaseDatasource();

        initializeData();

        mUserDatabase = FirebaseDatabase.getInstance().getReference("users/dagmamma/");

        radioGroupForeldre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_Dagmamma:
                        mUserDatabase = FirebaseDatabase.getInstance().getReference("users/dagmamma/");
                        String searchText = searchField.getText().toString();
                        firebaseUserSearch(searchText);
                        break;
                    case R.id.radio_Foreldre:
                        mUserDatabase = FirebaseDatabase.getInstance().getReference("users/foreldre/");
                        String searchText1 = searchField.getText().toString();
                        firebaseUserSearch(searchText1);
                        break;
                }
            }
        });


        radio_by.setChecked(true);
        searchField.setText("");
        String searchText = searchField.getText().toString();
        //query = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        //recyclerView.setHasFixedSize(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_by:
                        radio_checked = 1;
                        String searchText = searchField.getText().toString();
                        firebaseUserSearch(searchText);
                        break;
                    case R.id.radio_navn:
                        radio_checked = 2;
                        String searchText1 = searchField.getText().toString();
                        firebaseUserSearch(searchText1);
                        break;
                }
            }
        });

        searchField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && clicked == 0){
                    clicked = 1;

                    String searchText = searchField.getText().toString();
                    firebaseUserSearch(searchText);
                    detectChangeInEditText((EditText) v);
                }
                else if(hasFocus && clicked == 1){
                    detectChangeInEditText((EditText) v);
                }

            }
        });
        firebaseUserSearch("");
        return fragment_annonser;
    }
    /*public void onRadioButtonClicked(){
    }*/
    private void detectChangeInEditText(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = searchField.getText().toString();
                firebaseUserSearch(searchText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void firebaseUserSearch(final String searchText) {

        Query query;
        if(radio_checked == 1){
             query = mUserDatabase.orderByChild("by").startAt(searchText).endAt(searchText + "\uf8ff");
        }
        else {
             query = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
        }
        FirebaseRecyclerOptions<Person> options = new FirebaseRecyclerOptions.Builder<Person>()
                .setQuery(query, Person.class)
                .setLifecycleOwner(this)
                .build();//

        FirebaseRecyclerAdapter<Person, SearchActivity.UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Person,
                SearchActivity.UsersViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull SearchActivity.UsersViewHolder viewHolder, final int position, @NonNull final Person model) {
                viewHolder.setDetails(getActivity().getApplicationContext(),model.getName(), "Hei", "");
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Person thisUser = userList.get(position);
                        for(int i=0; i<userList.size();i++) {
                            if (userList.get(i).getName().equals(thisUser.getName())) {
                                //thisUser = userList.get(i);
                                Intent intent = new Intent(getActivity(), PersonDetailedActivity.class);
                                intent.putExtra("name", thisUser.getName());
                                intent.putExtra("alder", thisUser.getfDato());
                                intent.putExtra("by", thisUser.getBy());
                                intent.putExtra("bilde", "");
                                intent.putExtra("desc", thisUser.getProfilBeskrivelse());
                                intent.putExtra("email", thisUser.getEmail());
                                startActivity(intent);
                            }
                        }
                    }
                });
            }

            @NonNull
            @Override
            public SearchActivity.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.cardview_layout, viewGroup, false);
                return new SearchActivity.UsersViewHolder(view);
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setDetails(Context context, String userName, String description, String userImage){
            TextView user_name = (TextView) mView.findViewById(R.id.person_name);
            //TextView user_description = (TextView) mView.findViewById(R.id.textViewDescription);
            ImageView user_image = (ImageView) mView.findViewById(R.id.imageView);

            user_name.setText(userName);
            //user_description.setText(description);

            //    Glide.with(context).load(userImage).into(user_image);

        }
    }
    private void initializeData(){
        userList = new ArrayList<>();
        datasource.getAllUsers(UserType.DAGMAMMA, new DidGetUsersCallBack() {
            @Override
            public void didRecieve(List<Person> personList) {
                userList = personList;
            }
        });
    }
    public void changeCheckedInt(int integer){
        radio_checked = integer;
    }
}

