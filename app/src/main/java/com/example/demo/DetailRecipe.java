package com.example.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo.bean.Like;
import com.example.demo.bean.Recipe;
import com.example.demo.bean.RecipeAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailRecipe extends AppCompatActivity{
    private Drawable empty;
    TextView tv_htmlcontent;
    String userid;
    Button buttonLike;
    private DatabaseReference databaseReference;
    public  FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);
        getSupportActionBar().hide();
        LinearLayout line = findViewById(R.id.container);
        databaseReference = database.getReference("likes");



        // Recieve data
        userid = getIntent().getExtras().getString("user_id");
        final int stt = getIntent().getExtras().getInt("recipe_stt");
        String name = getIntent().getExtras().getString("recipe_name");
        String content = getIntent().getExtras().getString("recipe_content");
        int category = getIntent().getExtras().getInt("recipe_category");
        String image_url = getIntent().getExtras().getString("recipe_img");
        String source  = getIntent().getExtras().getString("recipe_Html_content");
        Query query = databaseReference.child(userid).orderByChild("recipeSTT").equalTo(stt);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        buttonLike.setText("Liked");

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // ini views

        TextView tv_recipetitle = findViewById(R.id.recipe_title);
        TextView tv_content = findViewById(R.id.recipe_content);
        TextView tv_categorie = findViewById(R.id.aa_categorie);
        TextView tv_des = findViewById(R.id.recipe_content);
        ImageView img = findViewById(R.id.aa_thumbnail);
        tv_htmlcontent = findViewById(R.id.HTML_content);
        buttonLike = findViewById(R.id.btn_like);
        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonLike.getText().equals("Like")){
                    buttonLike.setText("Liked");
                    Like like = new Like();
                    like.setUserid(userid);
                    like.setRecipeSTT(stt);
                    databaseReference.child(like.getUserid()).child(like.getRecipeSTT()+"").setValue(like);
                }
                else
                {
                    buttonLike.setText("Like");
                    databaseReference.child(userid).child(stt+"").removeValue();
                }

            }
        });


        // setting values to each view

        tv_recipetitle.setText(name);
        tv_categorie.setText(category + "");
        tv_des.setText(content);
        WebView wb = new WebView(this);
        wb.loadDataWithBaseURL(null, "<style>img{display: inline;height: auto;max-width: 100%;}</style>" + source, "text/html", "UTF-8", null);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wb.setLayoutParams(layoutParams2);
        line.addView(wb);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);


    }



}

