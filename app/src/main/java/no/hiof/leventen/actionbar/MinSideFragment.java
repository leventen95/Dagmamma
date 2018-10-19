package no.hiof.leventen.actionbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


public class MinSideFragment extends Fragment {
    Button button;

    private RedigerSideFragment redigerSideFragment;
    private FrameLayout frameRedigerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_min_side, container, false);





        Button btn = view.findViewById(R.id.buttonEditProfil);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*if(container != null){
                    container.removeAllViews();
                }*/

                frameRedigerLayout = (FrameLayout) view.findViewById(R.id.fragment_rediger);

                // (ChangeFragmentInterface) getActivity().changeFragment();
                //Fragment redigerSideFragment = new RedigerSideFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_rediger, redigerSideFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

}
