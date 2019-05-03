package com.example.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    Button btn;
    EditText search_ctn;
    Button category_nuong,category_xao,category_ran,category_khaivi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search_ctn = findViewById(R.id.search_content);
        btn = findViewById(R.id.button_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = search_ctn.getText().toString().trim();
                Intent i = new Intent(SearchActivity.this, DailyRecipe.class);
                i.putExtra("recipe_category", a);
                startActivity(i);
            }
        });
        category_khaivi = findViewById(R.id.category_monkhaivi);
        category_khaivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, DailyRecipe.class);
                i.putExtra("recipe_category", 10+"");
                startActivity(i);
            }
        });
        category_nuong = findViewById(R.id.category_nuong);
        category_nuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, DailyRecipe.class);
                i.putExtra("recipe_category", "nướng");
                startActivity(i);
            }
        });
        category_xao = findViewById(R.id.category_xao);
        category_xao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, DailyRecipe.class);
                i.putExtra("recipe_category", "xào");
                startActivity(i);
            }
        });
        category_ran = findViewById(R.id.category_ran);
        category_ran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, DailyRecipe.class);
                i.putExtra("recipe_category", "rán");
                startActivity(i);
            }
        });






        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu:
                        Intent a = new Intent(SearchActivity.this,HomeActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_search:

                        break;
                    case R.id.navigation_person:
                        Intent b = new Intent(SearchActivity.this,UserActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });
    }
}
