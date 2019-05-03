package com.example.demo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo.bean.Recipe;
import com.example.demo.bean.RecipeAdapter;
import com.example.demo.bean.RecyclerViewAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Recipe> list_recipe = new ArrayList<Recipe>();
    ArrayList<Recipe> newestRecipe = new ArrayList<Recipe>();
    RequestOptions option;
    LinearLayout layout;
    static int countOffset = 0;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView dailyLink = findViewById(R.id.daily_link);
        TextView dailyTitle = findViewById(R.id.newest_recipe_title);
        dailyTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this,countOffset+" ",Toast.LENGTH_SHORT).show();
                RecipeAdapter mDbHelper = new RecipeAdapter(HomeActivity.this);
                mDbHelper.createDatabase();
                mDbHelper.open();
                option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
                ArrayList<Recipe> temp;
                temp = mDbHelper.getRecipeByCategory(44,countOffset);
                newestRecipe.addAll(temp) ;
                countOffset +=10;
                mDbHelper.close();
                RecyclerViewAdapter myadapter = new RecyclerViewAdapter(HomeActivity.this,newestRecipe) ;
                recyclerView.setAdapter(myadapter);
            }
        });
        layout = (LinearLayout) findViewById(R.id.linear);
        dailyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, DailyRecipe.class);
                i.putExtra("recipe_category", 44+"");
                startActivity(i);
            }
        });



        recyclerView = findViewById(R.id.recycler_view_id);
//        recyclerView.addOnScrollListener(new OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                RecipeAdapter mDbHelper = new RecipeAdapter(HomeActivity.this);
//                mDbHelper.createDatabase();
//                mDbHelper.open();
//                ArrayList<Recipe> temp;
//                temp = mDbHelper.getRecipeByCategory(44,countOffset);
//                newestRecipe.addAll(temp) ;
//                countOffset +=10;
//                mDbHelper.close();
//                RecyclerViewAdapter myadapter = new RecyclerViewAdapter(HomeActivity.this,newestRecipe) ;
//                recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
//                recyclerView.setAdapter(myadapter);
//            }
//        });
        renderDaylyRecipe();
        setuprecyclerview();




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu:
                        break;
                    case R.id.navigation_search:
                        Intent a = new Intent(HomeActivity.this,SearchActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_person:
                        Intent b = new Intent(HomeActivity.this,UserActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {


    }
    private void setuprecyclerview() {

        RecipeAdapter mDbHelper = new RecipeAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        newestRecipe = mDbHelper.getRecipeByCategory(44,countOffset);
        countOffset +=10;
        mDbHelper.close();
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,newestRecipe) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    public void renderDaylyRecipe() {
        RecipeAdapter mDbHelper = new RecipeAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        newestRecipe = mDbHelper.getRandomRecipe();
        mDbHelper.close();
        for (int i = 0; i < 5; i++) {
            final int a = i;
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
            imageView.setLayoutParams(layoutParams);
            Glide.with(this).load(newestRecipe.get(i).getLinkImage()).apply(option).into(imageView);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            final ArrayList<Recipe> finalLstrecipe = newestRecipe;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(HomeActivity.this, DetailRecipe.class);
                    i.putExtra("recipe_name", finalLstrecipe.get(a).getName());
                    i.putExtra("recipe_category", finalLstrecipe.get(a).getCategory());
                    i.putExtra("recipe_img", finalLstrecipe.get(a).getLinkImage());
                    i.putExtra("recipe_content", finalLstrecipe.get(a).getContent());
                    i.putExtra("recipe_Html_content",finalLstrecipe.get(a).getHtmlContent());
                    startActivity(i);
                }
            });
            TextView tv = new TextView(this);
            tv.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(200, 200);
            tv.setLayoutParams(layoutParams2);
            tv.setId(i + 10);
            tv.setText(finalLstrecipe.get(a).getName());
            tv.setTextSize(14);
            tv.setPadding(5, 5, 5, 5);


            LinearLayout recipeLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
            recipeLayout.setLayoutParams(layoutParams3);
            recipeLayout.setOrientation(LinearLayout.VERTICAL);
            recipeLayout.setPadding(5, 5, 5, 5);
            recipeLayout.addView(imageView);
            recipeLayout.addView(tv);


            layout.addView(recipeLayout);


        }
    }
}
