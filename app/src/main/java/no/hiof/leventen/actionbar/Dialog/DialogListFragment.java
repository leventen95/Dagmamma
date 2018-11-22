package no.hiof.leventen.actionbar.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import no.hiof.leventen.actionbar.Chat.ChatListActivity;
import no.hiof.leventen.actionbar.Chat.Conversation;
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

*/      initializeAdapter();
        return view;
    }

    private void initializeAdapter(){
        DialogAdapter.DialogClickListener clickListener = new DialogAdapter.DialogClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), ChatListActivity.class);
                intent.putExtra("id", Person.getCurrentUser().getConversations().get(position).getId());
                startActivity(intent);
            }
        };

        DialogAdapter adapter = new DialogAdapter(Person.getCurrentUser().getConversations(), clickListener);
        recyclerView.setAdapter(adapter);
    }
}
