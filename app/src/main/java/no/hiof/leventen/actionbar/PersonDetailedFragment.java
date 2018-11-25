package no.hiof.leventen.actionbar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import no.hiof.leventen.actionbar.Chat.ChatListActivity;
import no.hiof.leventen.actionbar.Chat.Conversation;


public class PersonDetailedFragment extends Fragment {
    private List<Person> personList;
    public final static String PERSON_INDEX="personIndex";
    private static final int DEFAULT_PERSON_INDEX=1;
    private TextView personNameTextView;
    private TextView personAlderTextView;
    private TextView personByTextView;
    private TextView personDescriptionTextView;
    private ImageView personImageView;
    private int personIndex;
    private Button goChat;
    private String email;
    private View view;


    public PersonDetailedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personList = Person.getData();
        view = inflater.inflate(R.layout.fragment_person_detailed, container, false);

        personNameTextView = view.findViewById(R.id.textViewNavn);
        personAlderTextView = view.findViewById(R.id.textViewAlder);
        personDescriptionTextView = view.findViewById(R.id.textViewDescription);
        personImageView = view.findViewById(R.id.imageViewPerson);
        personByTextView = view.findViewById(R.id.textViewBy);

        goChat = view.findViewById(R.id.goToChatBtn);
        goChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person.getCurrentUser().getConversations().add(
                        new Conversation(Person.getCurrentUser().getConversations().size(), email, personNameTextView.getText().toString()));
                Intent intent = new Intent(getActivity(), ChatListActivity.class);
                intent.putExtra("id", Person.getCurrentUser().getConversations().size() -1);
                startActivity(intent);
            }
        });

        personIndex = savedInstanceState == null? DEFAULT_PERSON_INDEX : savedInstanceState.getInt(PERSON_INDEX, DEFAULT_PERSON_INDEX);
        //setDisplayedPersonDetail(personIndex);


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(PERSON_INDEX,personIndex);
    }

    public void setDisplayedPersonDetail(String name, String alder, String desc, String by, final String email) {

        this.email = email;
        personNameTextView.setText(name);
        personAlderTextView.setText(alder);
        personDescriptionTextView.setText(desc);
        personByTextView.setText(by);
        if(Person.getCurrentUser().getEmail().equals(email))
            goChat.setVisibility(View.GONE);
    }

}
