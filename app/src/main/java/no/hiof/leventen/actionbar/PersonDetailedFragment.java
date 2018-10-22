package no.hiof.leventen.actionbar;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        personIndex = savedInstanceState == null? DEFAULT_PERSON_INDEX : savedInstanceState.getInt(PERSON_INDEX, DEFAULT_PERSON_INDEX);
        setDisplayedPersonDetail(personIndex);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(PERSON_INDEX,personIndex);
    }

    public void setDisplayedPersonDetail(int personIndex) {
        this.personIndex = personIndex;
        personList = Person.getData();

        Person person = personList.get(personIndex);
        personNameTextView.setText(person.getName());
        personAlderTextView.setText(person.getAge() + " år.");

        Drawable picture = ContextCompat.getDrawable(getActivity(), person.getPhotoId());
        if(picture != null){
            personImageView.setImageDrawable(picture);
        }
    }

}
