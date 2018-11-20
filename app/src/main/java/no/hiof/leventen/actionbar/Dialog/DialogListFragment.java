package no.hiof.leventen.actionbar.Dialog;

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

import no.hiof.leventen.actionbar.Chat.Conversation;
import no.hiof.leventen.actionbar.Chat.Message;
import no.hiof.leventen.actionbar.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogListFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Conversation> conversations;

    public DialogListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cardview_dialog_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.dialogRecyclerView);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        initializeData();
        initializeAdapter();
        return view;
    }
    private void initializeData(){
        conversations = new ArrayList<Conversation>();
        Conversation con = new Conversation("1", "Fredrik Kalsberg");
        con.addMessage(new Message("1","Hei du! Jeg vil gjerne passe ungen din!","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        con.addMessage(new Message("2","Hei du! Jeg har ingen unger jeg!","Joakim Granaas","Fredrik Kalsberg",new Date(81996972)));
        con.addMessage(new Message("3","Uff, det var dumt!","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        con.addMessage(new Message("4","Men jeg kjenner en person som har unger da!","Joakim Granaas", "Fredrik Kalsberg",new Date(81996972)));
        con.addMessage(new Message("5","Javell? Hvem da?","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        con.addMessage(new Message("6","Han heter Petter!","Joakim Granaas","Fredrik Kalsberg",new Date()));
        conversations.add(con);
    }
    private void initializeAdapter(){
        DialogAdapter adapter = new DialogAdapter(conversations);
        recyclerView.setAdapter(adapter);
    }
}
