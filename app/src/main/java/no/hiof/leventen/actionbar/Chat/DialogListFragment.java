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
public class DialogListFragment extends Fragment {
    RecyclerView recyclerView;
    private List<Message> dialog;

    public DialogListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cardview_dialog_list, container, false);
       // recyclerView = (RecyclerView) view.findViewById(R.id.cardview_dialog_item);
        recyclerView = (RecyclerView) view.findViewById(R.id.dialogRecyclerView);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
     //   initializeData();
    //    initializeAdapter();
        return view;
    }
    private void initializeData(){
        dialog = new ArrayList<>();
        dialog.add(new Message("Hei du! Jeg vil gjerne passe ungen din!", "Fredrik Kalsberg", new Date(81996972)));
        dialog.add(new Message("Hei du! Jeg har ingen unger jeg!", "Joakim Granaas", new Date(81996972)));
        dialog.add(new Message("Uff, det var dumt!", "Fredrik Kalsberg", new Date(81996972)));
        dialog.add(new Message("Men jeg kjenner en person som har unger da!", "Joakim Granaas", new Date(81996972)));
        dialog.add(new Message("Javell? Hvem da?", "Fredrik Kalsberg", new Date(81996972)));
        dialog.add(new Message("Han heter Rolf!", "Joakim Granaas", new Date(81996972)));
    }
}
