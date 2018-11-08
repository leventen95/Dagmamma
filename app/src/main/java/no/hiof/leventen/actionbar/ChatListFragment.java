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


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Conversation> conversationList;
    private List<ChatMessage> d1;
    private String lastMessage;
    private String dialogUser;
    private String lastMessageTime;

    public ChatListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment_chat_list = inflater.inflate(R.layout.fragment_chat_list, container, false);
        recyclerView = (RecyclerView) fragment_chat_list.findViewById(R.id.fragment_chat);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        initializeData();
        initializeAdapter();
        return fragment_chat_list;
    }
    private void initializeData(){
        d1 = new ArrayList<ChatMessage>();
        Conversation d2 = new Conversation();
        d1.add(new ChatMessage("Hei du! Jeg vil gjerne passe ungen din!", "Fredrik Kalsberg", 81992972));
        d1.add(new ChatMessage("Hei du! Jeg har ingen unger jeg!", "Joakim Granaas", 81996972));
        d1.add(new ChatMessage("Uff, det var dumt!", "Fredrik Kalsberg", 81997972));
        d1.add(new ChatMessage("Men jeg kjenner en person som har unger da!", "Joakim Granaas", 81998972));
        d1.add(new ChatMessage("Javell? Hvem da?", "Fredrik Kalsberg", 81999972));
        d1.add(new ChatMessage("Han heter Rolf!", "Joakim Granaas", 820100072));

        d2.addMessage(new ChatMessage("Hei du!", "Fredrik Kalsberg", 81992972));
        d2.addMessage(new ChatMessage("Hei du!", "Joakim Granaas", 81996972));
        d2.addMessage(new ChatMessage("Skjer?", "Fredrik Kalsberg", 81997972));
        d2.addMessage(new ChatMessage("Masse rart med sukker på!", "Joakim Granaas", 81998972));
        d2.addMessage(new ChatMessage("Javell? Hva da?", "Fredrik Kalsberg", 81999972));
        d2.addMessage(new ChatMessage("Ingen ting..!", "Joakim Granaas", 820100072));
        conversationList = new ArrayList<>();
        //conversationList.add(d1);
        conversationList.add(d2);

    }
    private void initializeAdapter(){
        ChatAdapter adapter = new ChatAdapter(d1);
        recyclerView.setAdapter(adapter);
    }
}



