package no.hiof.leventen.actionbar.Chat;

import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.Date;

import no.hiof.leventen.actionbar.Person;
import no.hiof.leventen.actionbar.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {
    RecyclerView recyclerView;
    EditText messageInupt;
    ImageButton btnSend;
    ChatAdapter adapter;
    private Conversation conversation;
    //private FirebaseRecyclerAdapter;

    public ChatListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_chat);
        recyclerView.hasFixedSize();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));
        messageInupt = view.findViewById(R.id.input);
        messageInupt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction()!=KeyEvent.ACTION_DOWN)
                    return false;
                if(keyCode == KeyEvent.KEYCODE_ENTER ){
                    sendAction(view);
                    return true;
                }
                return false;
            }
        });
        btnSend = view.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAction(view);
            }
        });
        return view;
    }

    private void initializeAdapter(){
        adapter = new ChatAdapter(conversation);
        recyclerView.setAdapter(adapter);
    }
    private void sendAction(View view) {
        String msg = messageInupt.getText().toString();
        if(msg.matches("")) {
            Toast.makeText(getContext(), "Skriv melding først!", Toast.LENGTH_LONG).show();
        } else {
            // TODO - Send til database og legg til i listen slik at man slipper å hente data fra database hver gang man sender mld
            conversation.addMessage(new Message(msg, Person.getCurrentUser().getEmail(), new Date()));
            adapter.notifyDataSetChanged();
            messageInupt.setText("");
        }
    }
    public void setDisplayedValues(Conversation conversation) {
        this.conversation = new Conversation(conversation.getId(), conversation.getOtherUser());

        for(int i = conversation.getConversationMessages().size() -1; i >= 0; i--)
            this.conversation.addMessage(conversation.getMessage(i));

        initializeAdapter();
    }
}
