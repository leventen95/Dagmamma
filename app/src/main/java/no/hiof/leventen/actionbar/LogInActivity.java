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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    Button button;
    private FirebaseAuth mAuth;
    private EditText emailInput, passwordInput;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = new Intent(LogInActivity.this, MainActivity.class);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
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
        if(currentUser != null){
            FirebaseAuth.getInstance().signOut();

        }

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
            System.out.println("Email eller passord er tomt");
            return;
        }

        mAuth.signInWithEmailAndPassword(email,passord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    System.out.println("Login success");
                    startActivity(intent);
                }else{
                    System.out.println("Something went wrong");
                    System.out.println(task.getException().toString());
                }
            }
        });
    }
}
