package com.example.demo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Recipe> list_recipe = new ArrayList<Recipe>();
    ArrayList<Recipe> newestRecipe = new ArrayList<Recipe>();
    RequestOptions option;
    LinearLayout layout;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView dailyLink = findViewById(R.id.daily_link);
        layout = (LinearLayout) findViewById(R.id.linear);
        dailyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, DailyRecipe.class);
                i.putExtra("recipe_category", 44);
                startActivity(i);
            }
        });



        recyclerView = findViewById(R.id.recycler_view_id);
        renderRecipeByCategory(44);
        setuprecyclerview();

    }

    @Override
    public void onClick(View v) {

    }
    private void setuprecyclerview() {

        RecipeAdapter mDbHelper = new RecipeAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        newestRecipe = mDbHelper.getRecipeByCategory(44);
        mDbHelper.close();
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,newestRecipe) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    public void renderRecipeByCategory(int category) {
        RecipeAdapter mDbHelper = new RecipeAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        newestRecipe = mDbHelper.getRecipeByCategory(category);
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
