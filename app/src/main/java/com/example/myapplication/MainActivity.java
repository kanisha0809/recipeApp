package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Home homeFragment = new Home();
    Recipe recipeFragment = new Recipe();
    Feedback feedbackFragment = new Feedback();
    Timer timerFragment = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, homeFragment, "homeFragment")
                        .add(R.id.container, recipeFragment, "recipeFragment")
                        .add(R.id.container, feedbackFragment, "feedbackFragment")
                        .add(R.id.container, timerFragment, "timerFragment")
                        .hide(recipeFragment)
                        .hide(feedbackFragment)
                        .hide(timerFragment)
                        .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.home){
                    getSupportFragmentManager().beginTransaction().
                            hide(recipeFragment).hide(feedbackFragment).hide(timerFragment).show(homeFragment).commit();
                    return true;
                }
                else if(id==R.id.recipe){
                    getSupportFragmentManager().beginTransaction().
                            hide(homeFragment).hide(feedbackFragment).hide(timerFragment).show(recipeFragment).commit();
                    return true;
                }
                else if(id==R.id.feedback){
                    getSupportFragmentManager().beginTransaction()
                            .hide(homeFragment).hide(recipeFragment).hide(timerFragment).show(feedbackFragment).commit();
                    return true;
                }
                else if(id==R.id.timer){
                    getSupportFragmentManager().beginTransaction()
                            .hide(homeFragment).hide(recipeFragment).hide(feedbackFragment).show(timerFragment).commit();
                    return true;
                }
                return false;
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void launchStrawberryCake(View view) {
        Intent intent = new Intent(this, StrawberryCake.class);
        startActivity(intent);
    }

    public void launchChocolateCake(View view) {
        Intent intent = new Intent(this, ChocolateCake.class);
        startActivity(intent);
    }

    public void launchBlueberryCake(View view) {
        Intent intent = new Intent(this, BlueberryCake.class);
        startActivity(intent);
    }

    public void launchMatchaCake(View view) {
        Intent intent = new Intent(this, MatchaCake.class);
        startActivity(intent);
    }

    public void launchVanillaCake(View view) {
        Intent intent = new Intent(this, VanillaCake.class);
        startActivity(intent);
    }

    public void launchLemonCake(View view) {
        Intent intent = new Intent(this, LemonCake.class);
        startActivity(intent);
    }
}