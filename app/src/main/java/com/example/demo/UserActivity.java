package com.example.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.bean.Like;
import com.example.demo.bean.Recipe;
import com.example.demo.bean.RecipeAdapter;
import com.example.demo.bean.RecyclerViewAdapter;
import com.example.demo.bean.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private List<Recipe> lstrecipe ;
    private RecyclerView recyclerView ;
    TextView user_name,user_sex;
    ArrayList<Integer> listRecipeInteger;
    RecipeAdapter mDbHelper;;
    String userid;
    Button logout;
    private LinearLayout linearLayout;
    private DatabaseReference databaseReference;
    private DatabaseReference userReference;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu:
                        Intent a = new Intent(UserActivity.this,HomeActivity.class);
                        a.putExtra("user_id", userid);
                        startActivity(a);
                        break;
                    case R.id.navigation_search:
                        Intent b = new Intent(UserActivity.this,SearchActivity.class);
                        b.putExtra("user_id", userid);
                        startActivity(b);
                        break;
                    case R.id.navigation_person:
                        break;
                }
                return false;
            }
        });
        logout = findViewById(R.id.log_out);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserActivity.this, LoginActivity.class);
                String userid = "";
                i.putExtra("user_id", userid);
                startActivity(i);
            }
        });
        lstrecipe = new ArrayList<Recipe>() ;
        recyclerView = findViewById(R.id.recyclerview_id);
        mDbHelper = new RecipeAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        userid = getIntent().getExtras().getString("user_id");




        databaseReference = database.getReference("likes");
        userid = getIntent().getExtras().getString("user_id");
        listRecipeInteger = new ArrayList<Integer>();

        Query query = databaseReference.child(userid);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot issue : dataSnapshot.getChildren()) {
                    // do something with the individual "issues"
                    Like like = issue.getValue(Like.class);
                    listRecipeInteger.add(like.getRecipeSTT());
                }
                lstrecipe = mDbHelper.getRecipeByListSTT(listRecipeInteger);
                mDbHelper.close();
                setuprecyclerview(lstrecipe);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        user_name = findViewById(R.id.user_name);
        user_sex = findViewById(R.id.user_sex);
        databaseReference = database.getReference("users");


        Query query1 = databaseReference.child(userid);


        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    User user = dataSnapshot.getValue(User.class);
                    user_name.setText(user.getEmail());
                    if(user.getSex().equals(""))
                    {
                        user_sex.setText("Male");
                    }
                    else
                    {
                        user_sex.setText(user.getSex());
                    }




            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });















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

    private void setuprecyclerview(List<Recipe> lstRecipe) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstRecipe,userid) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

}
