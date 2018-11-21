package no.hiof.leventen.actionbar.Dialog;

import android.content.Intent;
import android.os.Bundle;;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.hiof.leventen.actionbar.Chat.ChatListActivity;
import no.hiof.leventen.actionbar.Chat.Conversation;
import no.hiof.leventen.actionbar.Chat.Message;
import no.hiof.leventen.actionbar.Firebasehandler.ConversationCallback;
import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;
import no.hiof.leventen.actionbar.Person;
import no.hiof.leventen.actionbar.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogListFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Conversation> conversationsList;
    private FirebaseDatasource datasource = new FirebaseDatasource();
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



      /*  datasource.getConversations(Person.getCurrentUser(), new ConversationCallback() {
            @Override
            public void didRecieve(List<Conversation> conversations) {
                conversationsList = conversations;
            }
        });
*/      initializeData();
        initializeAdapter();
        return view;
    }

    private void initializeData(){
        conversationsList = new ArrayList<Conversation>();
        Conversation con = new Conversation("1", "Fredrik Kalsberg");
        con.addMessage(new Message("1","Hei du! Jeg vil gjerne passe ungen din!","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        con.addMessage(new Message("2","Hei du! Jeg har ingen unger jeg!","Joakim Granaas","Fredrik Kalsberg",new Date(81996972)));
        con.addMessage(new Message("3","Uff, det var dumt!","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        con.addMessage(new Message("4","Men jeg kjenner en person som har unger da!","Joakim Granaas", "Fredrik Kalsberg",new Date(81996972)));
        con.addMessage(new Message("5","Javell? Hvem da?","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
        con.addMessage(new Message("6","Han heter Petter!","Joakim Granaas","Fredrik Kalsberg",new Date()));
        conversationsList.add(con);
    }
    private void initializeAdapter(){
        DialogAdapter.DialogClickListener clickListener = new DialogAdapter.DialogClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(getActivity(), conversationsList.get(position).getOtherUser(), Toast.LENGTH_SHORT).show();
                Conversation con = new Conversation("1", "Fredrik Karlsberg");
                //= conversations.get(position);
                con.addMessage(new Message("1","Hei du! Jeg vil gjerne passe ungen din!","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
                con.addMessage(new Message("2","Hei du! Jeg har ingen unger jeg!","Joakim Granaas","Fredrik Kalsberg",new Date(81996972)));
                con.addMessage(new Message("3","Uff, det var dumt!","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
                con.addMessage(new Message("4","Men jeg kjenner en person som har unger da!","Joakim Granaas", "Fredrik Kalsberg",new Date(81996972)));
                con.addMessage(new Message("5","Javell? Hvem da?","Fredrik Kalsberg","Joakim Granaas",new Date(81996972)));
                con.addMessage(new Message("6","Han heter Petter!","Joakim Granaas","Fredrik Kalsberg",new Date()));
                Intent intent = new Intent(getActivity(), ChatListActivity.class);
          //      intent.putExtra("conversation",conversations.get(position));
                intent.putExtra("conversation",con);   // FJERN DENNE!!!!! BRUK DEN OVER!!
                startActivity(intent);
            }
        };

        DialogAdapter adapter = new DialogAdapter(conversationsList, clickListener);
        recyclerView.setAdapter(adapter);
    }
}
