package no.hiof.leventen.actionbar;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import no.hiof.leventen.actionbar.Classes.UserType;
import no.hiof.leventen.actionbar.Firebasehandler.DidCreateUserCallback;
import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;


public class RegistrerActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    EditText emailInput, passInput, navnInput, fDatoInput, beskrivelseInput, byInput;
    FirebaseAuth mAuth;
    RadioGroup radioGroup;
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build()
    );

    String email;
    String pass;
    String navn;
    String fDato;
    String beskrivelse;
    String by;
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        Button registrerProfilBildeButton = findViewById(R.id.registrerProfilBildeButton);
        Button registrerButton = findViewById(R.id.registrerButton);
        emailInput = findViewById(R.id.registrerEmailEdit);
        passInput = findViewById(R.id.registrerPasswordEdit);
        navnInput = findViewById(R.id.registrerNavnEdit);
        fDatoInput = findViewById(R.id.registrerFodselEdit);
        beskrivelseInput = findViewById(R.id.editText11);
        byInput = findViewById(R.id.registrerByEdit);
        radioGroup = findViewById(R.id.userTypeRadioGroup);

        mAuth = FirebaseAuth.getInstance();

        registrerProfilBildeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        registrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();

            }
        });



    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView registrerProfilBilde = findViewById(R.id.registrerBildeView);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            registrerProfilBilde.setImageURI(imageUri);
        }
    }

    private void registerUser(){

        email = emailInput.getText().toString().trim();
        pass = passInput.getText().toString().trim();
        navn = navnInput.getText().toString().trim();
        fDato  = fDatoInput.getText().toString().trim();
        beskrivelse = beskrivelseInput.getText().toString().trim();
        by = byInput.getText().toString().trim();
        //TODO - Gjør noe med by(ENUM)
        System.out.println(email + pass + navn + fDato + beskrivelse);

        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Fyll inn begge felter", Toast.LENGTH_LONG);
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(), "Skriv inn en epost-adresse", Toast.LENGTH_LONG);
            return;
        }


        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Person newPerson = new Person(navn,
                            email,
                            FirebaseAuth.getInstance().getCurrentUser().getUid(),
                            beskrivelse,
                            "ikke satt",
                            by,
                            fDato,
                            true);

                    FirebaseDatasource fb = new FirebaseDatasource();
                    fb.addExtraInfoForUser(newPerson, FirebaseAuth.getInstance().getCurrentUser().getUid(),userType, new DidCreateUserCallback() {
                        @Override
                        public void didCreateUser(boolean didComplete) {

                            Intent intent = new Intent(RegistrerActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }

            }
        });

    }


    public void onRadioButtonClicked(View view) {

        switch (view.getId()){
            case R.id.registrerForelderCheckBox:
                userType = UserType.FORELDRE.toString().toLowerCase();
                break;
            case R.id.registrerBarnepassCheckBox:
                userType = UserType.DAGMAMMA.toString().toLowerCase();
                break;
        }


    }
}
