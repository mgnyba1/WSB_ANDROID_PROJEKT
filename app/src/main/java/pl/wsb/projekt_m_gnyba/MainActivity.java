package pl.wsb.projekt_m_gnyba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import pl.wsb.projekt_m_gnyba.databinding.ActivityLoginBinding;
import pl.wsb.projekt_m_gnyba.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentHome = null;
    Fragment fragmentBMI = null;
    Fragment fragmentAccount = null;
    FragmentManager fm = null;
    Fragment active = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fragmentHome = new HomeFragment();
        fragmentBMI = new BMIFragment();
        fragmentAccount = new AccountFragment();
        fm = getSupportFragmentManager();
        active = fragmentHome;

        fm.beginTransaction().add(R.id.main_container, fragmentHome, "Home").hide(fragmentAccount).commit();
        fm.beginTransaction().add(R.id.main_container, fragmentBMI, "BMI").hide(fragmentBMI).commit();
        fm.beginTransaction().add(R.id.main_container,fragmentAccount, "Account").commit();

        //NavController navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        fm.beginTransaction().hide(active).show(fragmentHome).commit();
                        active = fragmentHome;
                        return true;

                    case R.id.bmi:
                        fm.beginTransaction().hide(active).show(fragmentBMI).commit();
                        active = fragmentBMI;
                        return true;

                    case R.id.account:
                        fm.beginTransaction().hide(active).show(fragmentAccount).commit();
                        active = fragmentAccount;
                        return true;
                }

                return false;

            }}
        );


    }

}