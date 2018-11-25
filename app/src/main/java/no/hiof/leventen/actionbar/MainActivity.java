package no.hiof.leventen.actionbar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

import no.hiof.leventen.actionbar.Chat.ChatListFragment;
import no.hiof.leventen.actionbar.Dialog.DialogListFragment;
import no.hiof.leventen.actionbar.Firebasehandler.FirebaseDatasource;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private AnnonserFragment annonserFragment;
    private ChatListFragment chatListFragment;
    private DialogListFragment dialogListFragment;
    private MinSideFragment minSideFragment;
    public FirebaseDatasource test = new FirebaseDatasource();

    private FirebaseAuth mAuth;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.main_frame);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_nav);
       // goToSearch = findViewById(R.id.goToSearchBtn);

        annonserFragment = new AnnonserFragment();
        chatListFragment = new ChatListFragment();
        dialogListFragment = new DialogListFragment();
        minSideFragment = new MinSideFragment();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_annonser:
                        setFragment(annonserFragment);
                        return true;
                    case R.id.nav_chat:
                        setFragment(dialogListFragment);
                        return true;
                    case R.id.nav_minSide:
                        setFragment(minSideFragment);
                        return true;
                    default:
                        return false;

                }
            }
        });
        setFragment(annonserFragment);

        FirebaseDatasource datasource = new FirebaseDatasource();

        intent = new Intent(MainActivity.this, LogInActivity.class);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){

            datasource.getUser(FirebaseAuth.getInstance().getCurrentUser().getUid(),true);
            mAuth = FirebaseAuth.getInstance();
        }
        else {
            startActivity(intent);
        }



    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }




    private void testCreate(){


    }
}
