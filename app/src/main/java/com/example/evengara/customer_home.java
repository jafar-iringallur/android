package com.example.evengara;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.evengara.databinding.ActivityCustomerHomeBinding;

public class customer_home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityCustomerHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_customer_home);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.navigation_home)
        {
            Intent ij = new Intent(getApplicationContext(), set_ip.class);
            startActivity(ij);
        }
        if(id==R.id.navigation_cart)
        {
            Intent ij = new Intent(getApplicationContext(), login.class);
            startActivity(ij);
        }
        if(id==R.id.navigation_search)
        {
            Intent ij = new Intent(getApplicationContext(), signup.class);
            startActivity(ij);
        }
        return false;
    }
}