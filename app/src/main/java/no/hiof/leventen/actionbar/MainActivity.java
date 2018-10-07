package no.hiof.leventen.actionbar;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private AnnonserFragment annonserFragment;
    private ChatFragment chatFragment;
    private MinSideFragment minSideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.main_frame);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_nav);

        annonserFragment = new AnnonserFragment();
        chatFragment = new ChatFragment();
        minSideFragment = new MinSideFragment();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_annonser:
                        //bottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(annonserFragment);
                        return true;
                    case R.id.nav_chat:
                        //bottomNavigationView.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(chatFragment);
                        return true;
                    case R.id.nav_minSide:
                        //bottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(minSideFragment);
                        return true;

                    default:
                        return false;

                }
            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
}
