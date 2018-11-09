package no.hiof.leventen.actionbar;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RegistrerActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        Button registrerProfilBildeButton = findViewById(R.id.registrerProfilBildeButton);
        Button registrerButton = findViewById(R.id.registrerButton);

        registrerProfilBildeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        registrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Må gjøres!
                /*Her skal da all infomasjon lagres i firebase og deretter kunne hentes ut i annonser,
                minSide og redigerSide*/

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

}
