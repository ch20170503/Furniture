package com.hc.devieradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.furniture.R;


public class MyViewHolder extends RecyclerView.ViewHolder {
    public  TextView loop_list_item_content;
    public  ImageView loop_list_play_img3;
    public  TextView loop_list_text1;
    public  TextView loop_list_text2;

    public MyViewHolder(View itemView) {
        super(itemView);
        loop_list_item_content = (TextView) itemView.findViewById(R.id.loop_list_item_content);
        loop_list_play_img3 = (ImageView) itemView.findViewById(R.id.loop_list_play_img3);
        loop_list_text1 = (TextView) itemView.findViewById(R.id.loop_list_text1);
        loop_list_text2 = (TextView) itemView.findViewById(R.id.loop_list_text2);

    }
}
