package com.example.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.LinearLayout;

import com.example.demo.bean.Recipe;
import com.example.demo.bean.RecipeAdapter;
import com.example.demo.bean.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DailyRecipe extends AppCompatActivity implements View.OnClickListener {
    private List<Recipe> lstrecipe ;
    private RecyclerView recyclerView ;
        private LinearLayout linearLayout;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_daily_recipe);
            lstrecipe = new ArrayList<Recipe>() ;
            recyclerView = findViewById(R.id.recyclerviewid);
            RecipeAdapter mDbHelper = new RecipeAdapter(this);
            mDbHelper.createDatabase();
            mDbHelper.open();

            lstrecipe = mDbHelper.getRecipeByCategory(44);
            mDbHelper.close();



            setuprecyclerview(lstrecipe);



        }
    @Override
    public void onClick(View v) {

    }
    private void setuprecyclerview(List<Recipe> lstRecipe) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstRecipe) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

}
