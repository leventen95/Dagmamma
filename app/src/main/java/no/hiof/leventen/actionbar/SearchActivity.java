package no.hiof.leventen.actionbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import no.hiof.leventen.actionbar.Test.Users;

public class SearchActivity extends AppCompatActivity {
    private EditText searchField;
    private ImageButton searchBtn;
    private RecyclerView recyclerView;
    private DatabaseReference mUserDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("users");

        searchField = (EditText) findViewById(R.id.search_field);
        searchBtn = (ImageButton) findViewById(R.id.imageButton);
        recyclerView = (RecyclerView) findViewById(R.id.result_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseUserSearch();
            }
        });
    }

    private void firebaseUserSearch() {
        Query query = mUserDatabase;

        FirebaseRecyclerOptions<Users> options = new FirebaseRecyclerOptions.Builder<Users>()
                .setQuery(query,Users.class)
                .build();
        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Users,
                UsersViewHolder>(
                options) {
            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder viewHolder, int position, @NonNull Users model) {

                viewHolder.setDetails(getApplicationContext(),model.getName(), model.getDescription(), model.getImage());

            }

            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return null;
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
            TextView user_description = (TextView) mView.findViewById(R.id.textViewDescription);
            ImageView user_image = (ImageView) mView.findViewById(R.id.imageView);

            user_name.setText(userName);
            user_description.setText(description);

            Glide.with(context).load(userImage).into(user_image);

        }


    }
}
