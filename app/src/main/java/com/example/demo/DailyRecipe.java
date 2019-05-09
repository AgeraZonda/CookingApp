package com.example.demo;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.demo.bean.Recipe;
import com.example.demo.bean.RecipeAdapter;
import com.example.demo.bean.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DailyRecipe extends AppCompatActivity implements View.OnClickListener {
    private List<Recipe> lstrecipe ;
    private RecyclerView recyclerView ;
    String userid;
    private LinearLayout linearLayout;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_daily_recipe);
            lstrecipe = new ArrayList<Recipe>() ;
            getSupportActionBar().hide();
            recyclerView = findViewById(R.id.recyclerviewid);
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            ActionBar actionBar = getActionBar();
            actionBar.hide();
            RecipeAdapter mDbHelper = new RecipeAdapter(this);
            mDbHelper.createDatabase();
            mDbHelper.open();
            userid = getIntent().getExtras().getString("user_id");
            String content = getIntent().getExtras().getString("recipe_category");
            if(isInteger(content))
            {
                lstrecipe = mDbHelper.getRecipeByCategory(Integer.parseInt(content),0);
            }
            else{
                lstrecipe = mDbHelper.searchRecipeByName(content);
            }

            mDbHelper.close();



            setuprecyclerview(lstrecipe);



        }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(Exception e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    @Override
    public void onClick(View v) {

    }
    private void setuprecyclerview(List<Recipe> lstRecipe) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstRecipe,userid) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

}
