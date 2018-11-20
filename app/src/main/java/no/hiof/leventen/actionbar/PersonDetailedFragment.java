package no.hiof.leventen.actionbar;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class PersonDetailedFragment extends Fragment {
    private List<Person> personList;
    public final static String PERSON_INDEX="personIndex";
    private static final int DEFAULT_PERSON_INDEX=1;
    private TextView personNameTextView;
    private  TextView personAlderTextView;
    private TextView personDescriptionTextView;
    private ImageView personImageView;
    private int personIndex;
    private Button goChat;


    public PersonDetailedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personList = Person.getData();
        View view = inflater.inflate(R.layout.fragment_person_detailed, container, false);

        personNameTextView = view.findViewById(R.id.textViewNavn);
        personAlderTextView = view.findViewById(R.id.textViewAlder);
        personDescriptionTextView = view.findViewById(R.id.textViewDescription);
        personImageView = view.findViewById(R.id.imageViewPerson);
        goChat = view.findViewById(R.id.goToChatBtn);

        personIndex = savedInstanceState == null? DEFAULT_PERSON_INDEX : savedInstanceState.getInt(PERSON_INDEX, DEFAULT_PERSON_INDEX);
        //setDisplayedPersonDetail(personIndex);

        goChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),"Go away...",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(PERSON_INDEX,personIndex);
    }

    public void setDisplayedPersonDetail(String name, String alder, String desc) {


        personNameTextView.setText(name);
        personAlderTextView.setText(alder);
        personDescriptionTextView.setText(desc);

    }

}
