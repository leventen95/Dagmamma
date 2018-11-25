package no.hiof.leventen.actionbar;


import android.content.Intent;
import android.graphics.Bitmap;
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
import no.hiof.leventen.actionbar.Firebasehandler.DidReceiveProfile;
import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;


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
    private FirebaseDatasource datasource = new FirebaseDatasource();


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
                if(!(email.equals(Person.getCurrentUser().getEmail()))) {
                    Person.getCurrentUser().getConversations().add(
                            new Conversation(Person.getCurrentUser().getConversations().size(), email, personNameTextView.getText().toString()));
                    Intent intent = new Intent(getActivity(), ChatListActivity.class);
                    intent.putExtra("id", Person.getCurrentUser().getConversations().size() -1);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Dette er jo deg din tulling!", Toast.LENGTH_SHORT).show();
                }
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

    public void setDisplayedPersonDetail(String name, String alder, String desc, String by, final String email, String uid) {

        this.email = email;
        personNameTextView.setText(name);
        personAlderTextView.setText(alder);
        personDescriptionTextView.setText(desc);
        personByTextView.setText(by);

        datasource.getImage(uid, new DidReceiveProfile() {
            @Override
            public void didRecieve(Bitmap picture) {
                if(picture != null) {
                    personImageView.setImageBitmap(picture);
                }
            }
        });

        for(final Conversation c : Person.getCurrentUser().getConversations()) {
            if(c.getOtherUserEmail().equals(email)) {
                goChat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Person.getCurrentUser().getConversations().add(
                                new Conversation(Person.getCurrentUser().getConversations().size(), email, personNameTextView.getText().toString()));
                        Intent intent = new Intent(getActivity(), ChatListActivity.class);
                        intent.putExtra("id", c.getId());
                        startActivity(intent);
                    }
                });
            }
        }
    }

}
