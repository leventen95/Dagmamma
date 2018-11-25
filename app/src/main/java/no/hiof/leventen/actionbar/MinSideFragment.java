package no.hiof.leventen.actionbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import no.hiof.leventen.actionbar.Firebasehandler.DidReceiveProfile;
import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;


public class MinSideFragment extends Fragment {
    TextView txtNavn;
    TextView txtAlder;
    TextView txtBy;
    TextView txtDesc;
    ImageButton redigerSideButton;
    Button btnSignOut;
    ImageView profileImageView;
    FirebaseDatasource datasource = new FirebaseDatasource();

    private RedigerSideFragment redigerSideFragment;
    private FrameLayout frameRedigerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_min_side, container, false);

        txtNavn = view.findViewById(R.id.minSideNavnText);
        txtAlder = view.findViewById(R.id.minSideAlderText);
        txtBy = view.findViewById(R.id.minSideByText);
        txtDesc = view.findViewById(R.id.minSideDescriptionView);
        btnSignOut = view.findViewById(R.id.btnSignOut);
        profileImageView = view.findViewById(R.id.minSideBildeView);
        if(Person.getCurrentUser() != null) {
            txtNavn.setText(Person.getCurrentUser().getName());
            txtAlder.setText(Person.getCurrentUser().getfDato());   //Person har kun fDato og ikke alder
            txtBy.setText(Person.getCurrentUser().getBy());
            txtDesc.setText(Person.getCurrentUser().getProfilBeskrivelse());
            datasource.getImage(Person.getCurrentUser().getFirebaseUid(), new DidReceiveProfile() {
                @Override
                public void didRecieve(Bitmap picture) {
                    profileImageView.setImageBitmap(picture);
                }
            });

        }

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(FirebaseAuth.getInstance().getCurrentUser() != null) {

                    FirebaseAuth.getInstance().signOut();
                    Person.setCurrentUser(null);
                    startActivity(new Intent(view.getContext(), LogInActivity.class));
                }else{
                    return;
                }
            }
        });

        redigerSideButton = view.findViewById(R.id.redigerSideButton);
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
