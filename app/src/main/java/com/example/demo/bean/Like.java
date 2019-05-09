package com.example.demo.bean;

import java.io.Serializable;

public class Like implements Serializable {
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getRecipeSTT() {
        return RecipeSTT;
    }

    public void setRecipeSTT(int recipeSTT) {
        RecipeSTT = recipeSTT;
    }



    private String userid;
    private int RecipeSTT;

    public Like()
    {
        userid ="";
        RecipeSTT=0;

    }

}
