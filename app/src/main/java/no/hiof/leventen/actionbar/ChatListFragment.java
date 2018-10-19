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
    private List<Dialog> dialogList;
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
        View fragment_annonser = inflater.inflate(R.layout.fragment_annonser, container, false);
        recyclerView = (RecyclerView) fragment_annonser.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        initializeData();
        initializeAdapter();
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }
    private void initializeData(){
        Dialog d1 = new Dialog();
        Dialog d2 = new Dialog();
        d1.addMessage(new ChatMessage("Hei du! Jeg vil gjerne passe ungen din!", "Fredrik Kalsberg", 81992972));
        d1.addMessage(new ChatMessage("Hei du! Jeg har ingen unger jeg!", "Joakim Granaas", 81996972));
        d1.addMessage(new ChatMessage("Uff, det var dumt!", "Fredrik Kalsberg", 81997972));
        d1.addMessage(new ChatMessage("Men jeg kjenner en person som har unger da!", "Joakim Granaas", 81998972));
        d1.addMessage(new ChatMessage("Javell? Hvem da?", "Fredrik Kalsberg", 81999972));
        d1.addMessage(new ChatMessage("Han heter Rolf!", "Joakim Granaas", 820100072));

        d2.addMessage(new ChatMessage("Hei du!", "Fredrik Kalsberg", 81992972));
        d2.addMessage(new ChatMessage("Hei du!", "Joakim Granaas", 81996972));
        d2.addMessage(new ChatMessage("Skjer?", "Fredrik Kalsberg", 81997972));
        d2.addMessage(new ChatMessage("Masse rart med sukker på!", "Joakim Granaas", 81998972));
        d2.addMessage(new ChatMessage("Javell? Hva da?", "Fredrik Kalsberg", 81999972));
        d2.addMessage(new ChatMessage("Ingen ting..!", "Joakim Granaas", 820100072));
        dialogList = new ArrayList<>();
        dialogList.add(d1);
        dialogList.add(d2);

    }
    private void initializeAdapter(){
 //       RecyclerViewAdapter adapter = new RecyclerViewAdapter(dialogList);
 //       recyclerView.setAdapter(adapter);
    }
}



