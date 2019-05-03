package com.example.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu:
                        Intent a = new Intent(UserActivity.this,HomeActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_search:
                        Intent b = new Intent(UserActivity.this,SearchActivity.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_person:

                        break;
                }
                return false;
            }
        });
    }
}
