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

import java.util.Date;

import no.hiof.leventen.actionbar.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {
    RecyclerView recyclerView;
    EditText messageInupt;
    ImageButton btnSend;
    //private List<Conversation> conversationList;
    private Conversation con;
    private String lastMessage;
    private String dialogUser;
    private String lastMessageTime;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
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
        initializeData();
        initializeAdapter();
        return view;
    }
    private void initializeData(){
        this.con = new Conversation("1", "Fredrik Kalsberg");
        this.con.addMessage(new Message("1","Hei du! Jeg vil gjerne passe ungen din!","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        this.con.addMessage(new Message("2","Hei du! Jeg har ingen unger jeg!","Joakim Granaas","Fredrik Kalsberg",new Date(81996972)));
        this.con.addMessage(new Message("3","Uff, det var dumt!","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        this.con.addMessage(new Message("4","Men jeg kjenner en person som har unger da!","Joakim Granaas", "Fredrik Kalsberg",new Date(81996972)));
        this.con.addMessage(new Message("5","Javell? Hvem da?","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        this.con.addMessage(new Message("6","Han heter Petter!","Joakim Granaas","Fredrik Kalsberg",new Date()));

     //   conversationList = new ArrayList<>();
     //   conversationList.add(con);

    }

    private void initializeData(Conversation conversation){
        this.con = conversation;
    }

    private void initializeAdapter(){
        ChatAdapter adapter = new ChatAdapter(con);
        recyclerView.setAdapter(adapter);
    }
    private void sendAction(View view) {
        String msg = messageInupt.getText().toString();
        if(msg.matches("")) {
            Toast.makeText(getContext(), "Skriv melding først!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Sender melding..", Toast.LENGTH_LONG).show();
            // TODO - Send til database og legg til i listen slik at man slipper å hente data fra database hver gang man sender mld
            messageInupt.setText("");
        }
    }
    public void setDisplayedValues(Conversation conversation) {
 /*       this.con = new Conversation(conversation.getId(), conversation.getOtherUser());
        for(Message m : conversation.getConversationMessages())
            this.con.addMessage(m);
    //    initializeAdapter();*/
    }
}



