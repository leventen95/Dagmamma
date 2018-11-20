package no.hiof.leventen.actionbar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;

public class LogInActivity extends AppCompatActivity {

    Button button;
    private FirebaseAuth mAuth;
    private EditText emailInput, passwordInput;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatasource datasource = new FirebaseDatasource();
        intent = new Intent(LogInActivity.this, MainActivity.class);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            datasource.getUser(FirebaseAuth.getInstance().getCurrentUser().getUid(),true);
            startActivity(intent);
        }

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_log_in);
        Button loginButton = findViewById(R.id.loginButton);
        Button registrerButton = findViewById(R.id.loginRegistrerButton);
        emailInput = findViewById(R.id.loginBrukerInput);
        passwordInput = findViewById(R.id.passordInput);
        passwordInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction()!=KeyEvent.ACTION_DOWN)
                    return false;
                if(keyCode == KeyEvent.KEYCODE_ENTER ){
                    signIn(view);
                    return true;
                }
                return false;
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    /*    if(currentUser != null){
            FirebaseAuth.getInstance().signOut();

        }
*/
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn(v);
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
    private void signIn(View view) {
        String email = emailInput.getText().toString().trim();
        String passord = passwordInput.getText().toString();

        if(email.isEmpty() || passord.isEmpty()){
            Toast.makeText(getApplicationContext(), "Fyll inn begge felter", Toast.LENGTH_LONG);
            return;
        }

        mAuth.signInWithEmailAndPassword(email,passord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Innlogging vellykket!", Toast.LENGTH_LONG);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Innlogging feilet", Toast.LENGTH_LONG);
                    System.out.println(task.getException().toString());
                }
            }
        });
    }
}
