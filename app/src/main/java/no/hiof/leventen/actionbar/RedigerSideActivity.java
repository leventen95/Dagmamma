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

public class RedigerSideActivity extends AppCompatActivity {

    Button button;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rediger_side);

        Button redigerLagreButton = findViewById(R.id.redigerLagreButton);
        Button redigerBildeButton = findViewById(R.id.redigerBildeButton);

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
