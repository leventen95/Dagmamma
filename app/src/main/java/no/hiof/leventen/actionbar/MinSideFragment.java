package no.hiof.leventen.actionbar;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;


public class MinSideFragment extends Fragment {
    Button button;
    ImageButton imageButton;

    private RedigerSideFragment redigerSideFragment;
    private FrameLayout frameRedigerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_min_side, container, false);

        ImageButton redigerSideButton = view.findViewById(R.id.redigerSideButton);
        redigerSideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redigerSide = new Intent(view.getContext(), RedigerSideActivity.class);
                startActivity(redigerSide);
            }
        });




        return view;
    }
}
