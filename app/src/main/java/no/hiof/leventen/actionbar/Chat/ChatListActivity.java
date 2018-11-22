package no.hiof.leventen.actionbar.Chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import no.hiof.leventen.actionbar.Person;
import no.hiof.leventen.actionbar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListActivity extends AppCompatActivity {

    public ChatListActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        int id = getIntent().getIntExtra("id", -1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChatListFragment chatListFragment = (ChatListFragment) fragmentManager.findFragmentById(R.id.fragment_chat_list);
        chatListFragment.initializeAdapter(id);
    }
}