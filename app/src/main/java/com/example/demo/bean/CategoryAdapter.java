package com.example.demo.bean;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demo.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class CategoryAdapter {
    protected static final String TAG = "CategoryAdapter";
    private final Context context;
    Cursor cursor;


    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public CategoryAdapter(Context context) {
        this.context = context;
        mDbHelper = new DataBaseHelper(context);
    }


    public CategoryAdapter createDatabase() throws SQLException
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

    public CategoryAdapter open() throws SQLException
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


    public Category getCategoryByID(int id) throws SQLException
    {
        try
        {
            Category category = new Category();
            String query = "select * from danh_muc_con where id ='"+ id +"'";
            Cursor cursor = mDb.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                category.setTen(cursor.getString(3));
                category.setId(cursor.getInt(1));
                category.setStt(cursor.getInt(0));
            }
            return category;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

}
