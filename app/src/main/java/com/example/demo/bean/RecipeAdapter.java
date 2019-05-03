package com.example.demo.bean;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demo.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RecipeAdapter {
    protected static final String TAG = "RecipeAdapter";
    private final Context mContext;
    Cursor cursor;
    int currentCount = 0;


    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public RecipeAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }


    public RecipeAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public RecipeAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Recipe getRecipeBySTT(int stt) throws SQLException
    {
        try
        {
            Recipe recipe = new Recipe();
            String query = "select * from mon_an where stt ='"+ stt +"'";
            cursor = mDb.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setStt(cursor.getInt(0));
            }
            return recipe;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
    public ArrayList<Recipe> getRecipeByCategory(int categoryID,int offset)
    {
        ArrayList<Recipe> list = new  ArrayList<Recipe>();
        String query = "select * from mon_an where id_danh_muc_con ='"+ categoryID +"'"+ "limit 10 offset "+ offset;
        Cursor cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setStt(cursor.getInt(0));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<Recipe> searchRecipeByName(String name)
    {
        ArrayList<Recipe> list = new  ArrayList<Recipe>();
        String query = "select * from mon_an where ten like '%"+ name + "%'";
        Cursor cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setStt(cursor.getInt(0));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<Recipe> getRandomRecipe()
    {
        ArrayList<Recipe> list = new  ArrayList<Recipe>();
        ArrayList<Recipe> rdlist = new  ArrayList<Recipe>();
        String query = "select * from mon_an where id_danh_muc_con =30 LIMIT 20";
        Cursor cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setStt(cursor.getInt(0));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        Random rand = new Random();

// Obtain a number between [0 - 49].
        int n = rand.nextInt(18);
        rdlist.add(list.get(n));

        query = "select * from mon_an where id_danh_muc_con =40 LIMIT 20";
        cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setStt(cursor.getInt(0));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        rand = new Random();

        n = rand.nextInt(18);
        rdlist.add(list.get(n));

        query = "select * from mon_an where id_danh_muc_con =34 LIMIT 20";
        cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setStt(cursor.getInt(0));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        rand = new Random();

        n = rand.nextInt(18);
        rdlist.add(list.get(n));

        query = "select * from mon_an where id_danh_muc_con =34 LIMIT 20";
        cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setStt(cursor.getInt(0));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        rand = new Random();

        n = rand.nextInt(20);
        rdlist.add(list.get(n));

        query = "select * from mon_an where id_danh_muc_con =44 LIMIT 20";
        cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setStt(cursor.getInt(0));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        rand = new Random();

        n = rand.nextInt(20);
        rdlist.add(list.get(n));

        query = "select * from mon_an where id_danh_muc_con =42 LIMIT 18";
        cursor = mDb.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setName(cursor.getString(4));
                recipe.setCategory(cursor.getInt(2));
                recipe.setContent(cursor.getString(5));
                recipe.setHtmlContent(cursor.getString(6));
                recipe.setLinkImage(cursor.getString(3));
                recipe.setStt(cursor.getInt(0));
                list.add(recipe);
            } while (cursor.moveToNext());
        }
        rand = new Random();

        n = rand.nextInt(15);
        rdlist.add(list.get(n));

        return rdlist;
    }
}