package no.hiof.leventen.actionbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button loginButton = findViewById(R.id.loginButton);
        Button registrerButton = findViewById(R.id.loginRegistrerButton);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            FirebaseAuth.getInstance().signOut();

        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Her skal det legges inn kode som sjekker at en bruker finnes.
                Om noe er tastet inn feil vil en warning dukke opp med vennligst prøv igjen.
                Om tiden strekker til legges det også inn en glemt passord egenskap, der man vil
                automatisk sende en mail til brukernavn(mail) hvor man kan følge en link og
                endre sitt passord.
                 */

                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        registrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegistrerActivity.class);
                startActivity(intent);
            }
        });
    }
}
