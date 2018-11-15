package no.hiof.leventen.actionbar.Chat;


import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.hiof.leventen.actionbar.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Conversation> conversationList;
    private List<Message> d1;
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
        d1 = new ArrayList<Message>();
        Conversation d2 = new Conversation();
        d1.add(new Message("Hei du! Jeg vil gjerne passe ungen din!", "Fredrik Kalsberg", new Date(81996972)));
        d1.add(new Message("Hei du! Jeg har ingen unger jeg!", "Joakim Granaas", new Date(81996972)));
        d1.add(new Message("Uff, det var dumt!", "Fredrik Kalsberg", new Date(81996972)));
        d1.add(new Message("Men jeg kjenner en person som har unger da!", "Joakim Granaas", new Date(81996972)));
        d1.add(new Message("Javell? Hvem da?", "Fredrik Kalsberg", new Date(81996972)));
        d1.add(new Message("Han heter Petter!", "Joakim Granaas", new Date()));

        conversationList = new ArrayList<>();
        conversationList.add(d2);

    }
    private void initializeAdapter(){
        ChatAdapter adapter = new ChatAdapter(d1);
        recyclerView.setAdapter(adapter);
    }
}



