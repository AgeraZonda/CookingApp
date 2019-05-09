package com.example.demo.bean;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo.DetailRecipe;
import com.example.demo.R;

import java.util.List;

/**
 * Created by Aws on 11/03/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Recipe> mData ;
    RequestOptions option;
    String userid;


    public RecyclerViewAdapter(Context mContext, List<Recipe> mData,String userid ) {
        this.mContext = mContext;
        this.mData = mData;
        this.userid = userid;


        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.layout_item,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DetailRecipe.class);
                i.putExtra("recipe_stt",mData.get(viewHolder.getAdapterPosition()).getStt());
                i.putExtra("recipe_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("recipe_category",mData.get(viewHolder.getAdapterPosition()).getCategory());
                i.putExtra("recipe_img",mData.get(viewHolder.getAdapterPosition()).getLinkImage());
                i.putExtra("recipe_content",mData.get(viewHolder.getAdapterPosition()).getContent());
                i.putExtra("recipe_Html_content",mData.get(viewHolder.getAdapterPosition()).getHtmlContent());
                i.putExtra("user_id", userid);

                mContext.startActivity(i);

            }
        });




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        int a = mData.get(position).getCategory();
        CategoryAdapter categoryAdapter = new CategoryAdapter(mContext);
        categoryAdapter.createDatabase();
        categoryAdapter.open();
        Category categoryTemp = categoryAdapter.getCategoryByID(mData.get(position).getCategory());
        holder.tv_category.setText(categoryTemp.getTen());

        // Load Image from the internet and set it into Imageview using Glide

        Glide.with(mContext).load(mData.get(position).getLinkImage()).apply(option).into(holder.img_thumbnail);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name ;
        TextView tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container;





        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }

}