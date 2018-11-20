package no.hiof.leventen.actionbar;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PersonDetailedActivity extends AppCompatActivity {
    public static final String PERSON_ID_KEY="person_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detailed);

        String name = getIntent().getStringExtra("name");
        String alder = getIntent().getStringExtra("alder");
        String desc = getIntent().getStringExtra("desc");
        String by = getIntent().getStringExtra("by");
        FragmentManager fragmentManager = getSupportFragmentManager();
        PersonDetailedFragment personDetailedFragment = (PersonDetailedFragment) fragmentManager.findFragmentById(R.id.personDetailedFragment);

        personDetailedFragment.setDisplayedPersonDetail(name,alder,desc,by);
    }
}
