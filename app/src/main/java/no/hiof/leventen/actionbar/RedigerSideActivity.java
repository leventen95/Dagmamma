package no.hiof.leventen.actionbar;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import no.hiof.leventen.actionbar.Classes.UserType;
import no.hiof.leventen.actionbar.Firebasehandler.DidCompleteCallback;
import no.hiof.leventen.actionbar.Firebasehandler.DidReceiveProfile;
import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class RedigerSideActivity extends AppCompatActivity {

    EditText email, navn, fDato, by, beskrivelse, password;
    Button redigerLagreButton, redigerBildeButton;
    FirebaseDatasource datasource = new FirebaseDatasource();
    ImageView imgView;
    Uri imageUri;
    private static final int GALLERY = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rediger_side);

        email = findViewById(R.id.redigerEmailEdit);
        navn = findViewById(R.id.redigerNavnEdit);
        fDato = findViewById(R.id.redigerFodselEdit);
        by = findViewById(R.id.redigerByEdit);
        beskrivelse = findViewById(R.id.redigerDescriptionView);
        password = findViewById(R.id.redigerPasswordEdit);
        imgView = findViewById(R.id.redigerBildeView);


        if(Person.getCurrentUser() != null) {
            email.setText(Person.getCurrentUser().getEmail());
            navn.setText(Person.getCurrentUser().getName());
            fDato.setText(Person.getCurrentUser().getfDato());
            by.setText(Person.getCurrentUser().getBy());
            beskrivelse.setText(Person.getCurrentUser().getProfilBeskrivelse());
        }

        datasource.getImage(Person.getCurrentUser().getFirebaseUid(), new DidReceiveProfile() {
            @Override
            public void didRecieve(Bitmap picture) {
                imgView.setImageBitmap(picture);
            }
        });

        redigerLagreButton = findViewById(R.id.redigerLagreButton);
        redigerBildeButton = findViewById(R.id.redigerBildeButton);

        redigerLagreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person newInfo = new Person(navn.getText().toString(),
                        email.getText().toString(),
                        "",
                        beskrivelse.getText().toString(),
                        UserType.DAGMAMMA.toString().toLowerCase(),
                        by.getText().toString(),
                        fDato.getText().toString(),
                        true);

            String pass;
           if(imageUri != null) {
               datasource.uploadImage(Person.getCurrentUser().getFirebaseUid(),imageUri);

           }



           if(password == null){
               pass = "";
           }else{
               pass = password.getText().toString();
           }

               datasource.changeUserDetails(Person.getCurrentUser(), newInfo, pass, new DidCompleteCallback() {
                   @Override
                   public void didComplete(boolean didComplete) {
                       if (didComplete) {
                           System.out.println("Did change details");
                       }
                   }
               });
           }
        });


        redigerBildeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showPictureDialog();

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
                                if(isStoragePermissionGranted()){
                                    choosePhotoFromGallary();
                                }
                                else{
                                    ActivityCompat.requestPermissions(RedigerSideActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                                    if(isStoragePermissionGranted())
                                        choosePhotoFromGallary();
                                }
                                break;
                            case 1:
                                if(isCameraPermissionGranted()) {
                                    takePhotoFromCamera();
                                }
                                else{
                                    ActivityCompat.requestPermissions(RedigerSideActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                                    if(isCameraPermissionGranted())
                                        takePhotoFromCamera();
                                }
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

        ImageView registrerProfilBilde = findViewById(R.id.redigerBildeView);

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                imageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    //String path = saveImage(bitmap);
                    Toast.makeText(RedigerSideActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    registrerProfilBilde.setImageBitmap(bitmap);



                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RedigerSideActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
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


    @TargetApi(Build.VERSION_CODES.M)
    public  boolean isStoragePermissionGranted() {
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return false;
        }
    }
    @TargetApi(Build.VERSION_CODES.M)
    public  boolean isCameraPermissionGranted() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            return false;
        }
    }
}
