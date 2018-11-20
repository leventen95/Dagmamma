package no.hiof.leventen.actionbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
//import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import no.hiof.leventen.actionbar.Person;

public class SearchActivity extends AppCompatActivity {
    private EditText searchField; //
    private ImageButton searchBtn;//
    private RecyclerView recyclerView;//
    private DatabaseReference mUserDatabase;
    private CheckBox byCheckBox;
    private CheckBox navnCheckBox;
    private int clicked = 0;
    //private Query query;
    //private String searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("users/dagmamma/");

        searchField = (EditText) findViewById(R.id.search_field);//
        searchBtn = (ImageButton) findViewById(R.id.imageButton);//
        recyclerView = (RecyclerView) findViewById(R.id.result_list);//
        byCheckBox = (CheckBox) findViewById(R.id.byCheckBox);
        navnCheckBox = (CheckBox) findViewById(R.id.navnCheckBox);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        byCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(byCheckBox.isChecked()){

                    //changeQuery("by",searchField.getText().toString());
                }
            }
        });





        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchField.getText().toString();

                firebaseUserSearch(searchText);
            }
        });

        /*searchField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    searchField.setText("");

                    String searchText = searchField.getText().toString();
                    firebaseUserSearch(searchText);
                return true;
            }
        });*/

        searchField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus && clicked == 0){
                    searchField.setText("");
                    clicked = 1;

                    String searchText = searchField.getText().toString();
                    firebaseUserSearch(searchText);
                    detectChangeInEditText((EditText) v);
                }
                else if(hasFocus && clicked == 1){
                    /*String searchText = searchField.getText().toString();
                    firebaseUserSearch(searchText);*/
                    detectChangeInEditText((EditText) v);
                }
                //This one doesn't work yet, cuz wherever i click in the emulator, the edittext still has focus.... strange
                else if(!hasFocus){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                }
            }
        });
    }
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

        final Query query = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        byCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(byCheckBox.isChecked()){
                    final Query query = mUserDatabase.orderByChild("by").startAt(searchText).endAt(searchText + "\uf8ff");
                }
            }
        });



        /*FirebaseRecyclerOptions<Users> options = new FirebaseRecyclerOptions.Builder<Users>()
                .setQuery(query,Users.class)
                .build();
        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users,
                UsersViewHolder>(
                options) {*/
        FirebaseRecyclerOptions<Person> options = new FirebaseRecyclerOptions.Builder<Person>()// Disse skal slettes hvis kommentaren ovenfor fjernes
                .setQuery(query, Person.class)
                .setLifecycleOwner(this)
                .build();//

        FirebaseRecyclerAdapter<Person, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Person,
                UsersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder viewHolder, int position, @NonNull Person model) {

        //        viewHolder.setDetails(getApplicationContext(),model.getName(), model.getbeskrivelse(), model.getPhotoId());
                viewHolder.setDetails(getApplicationContext(),model.getName(), "Hei", "");
            }

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.cardview_layout, viewGroup, false);

                return new UsersViewHolder(view);
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
   /* public void changeQuery(String string, String searchText){
        Query query = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
    }
    /*public Query getQuery(){
        //return query;
    }*/
}
