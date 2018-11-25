package no.hiof.leventen.actionbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import no.hiof.leventen.actionbar.Classes.UserType;
import no.hiof.leventen.actionbar.Firebasehandler.DidCreateUserCallback;
import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;

import static android.media.MediaRecorder.VideoSource.CAMERA;


public class RegistrerActivity extends AppCompatActivity {

    private static final int GALLERY = 100;
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
                showPictureDialog();
            }
        });

        registrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();

            }
        });
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        ImageView registrerProfilBilde = findViewById(R.id.registrerBildeView);

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    //String path = saveImage(bitmap);
                    Toast.makeText(RegistrerActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    registrerProfilBilde.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegistrerActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            registrerProfilBilde.setImageBitmap(thumbnail);
            //saveImage(thumbnail);
            //Toast.makeText(RegistrerActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    /*public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }*/

    /*private void openGallery() {
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
    }*/

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
            Toast.makeText(getApplicationContext(),"Fyll inn begge felter", Toast.LENGTH_LONG).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(), "Skriv inn en epost-adresse", Toast.LENGTH_LONG).show();
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
