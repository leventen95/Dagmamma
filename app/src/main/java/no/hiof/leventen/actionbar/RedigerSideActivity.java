package no.hiof.leventen.actionbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RedigerSideActivity extends AppCompatActivity {

    TextView email, navn, fDato, by, beskrivelse;
    Button redigerLagreButton, redigerBildeButton;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rediger_side);

        email = findViewById(R.id.redigerEmailEdit);
        navn = findViewById(R.id.redigerNavnEdit);
        fDato = findViewById(R.id.redigerFodselEdit);
        by = findViewById(R.id.redigerByEdit);
        beskrivelse = findViewById(R.id.redigerDescriptionView);

        if(Person.getCurrentUser() != null) {
            email.setText(Person.getCurrentUser().getEmail());
            navn.setText(Person.getCurrentUser().getName());
            fDato.setText(Person.getCurrentUser().getfDato());
            by.setText(Person.getCurrentUser().getBy());
            beskrivelse.setText(Person.getCurrentUser().getProfilBeskrivelse());
        }

        redigerLagreButton = findViewById(R.id.redigerLagreButton);
        redigerBildeButton = findViewById(R.id.redigerBildeButton);

        redigerLagreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Her skal da all ny info lagres(eller overskrive den gamle)
                slik at det igjen dukker opp på min side og i annonser.
                 */
            }
        });


        redigerBildeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Her blir man sendt til sitt galleri på telefonen der man
                kan velge et nytt bilde.
                 */

                openGallery();

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

            ImageView redigerBilde = findViewById(R.id.redigerBildeView);
            if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                imageUri = data.getData();
                redigerBilde.setImageURI(imageUri);
            }
        }
}
