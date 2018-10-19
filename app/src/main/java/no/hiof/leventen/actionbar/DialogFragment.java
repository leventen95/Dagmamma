package no.hiof.leventen.actionbar;


import android.app.Dialog;
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
public class DialogFragment extends Fragment {
    RecyclerView recyclerView;
    private List<ChatMessage> dialog;

    public DialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment_dialog = inflater.inflate(R.layout.fragment_dialog, container, false);
        recyclerView = (RecyclerView) fragment_dialog.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        initializeData();
    //    initializeAdapter();
        return fragment_dialog;
    }
    private void initializeData(){
        dialog = new ArrayList<>();
        dialog.add(new ChatMessage("Hei du! Jeg vil gjerne passe ungen din!", "Fredrik Kalsberg", 81992972));
        dialog.add(new ChatMessage("Hei du! Jeg har ingen unger jeg!", "Joakim Granaas", 81996972));
        dialog.add(new ChatMessage("Uff, det var dumt!", "Fredrik Kalsberg", 81997972));
        dialog.add(new ChatMessage("Men jeg kjenner en person som har unger da!", "Joakim Granaas", 81998972));
        dialog.add(new ChatMessage("Javell? Hvem da?", "Fredrik Kalsberg", 81999972));
        dialog.add(new ChatMessage("Han heter Rolf!", "Joakim Granaas", 820100072));
    }
}
