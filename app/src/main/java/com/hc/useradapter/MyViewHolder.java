package com.hc.useradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc.furniture.R;


public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView proj_ite_content22;
    public TextView proj_item_delete22;
    public LinearLayout proj_item_layout22;

    public MyViewHolder(View itemView) {
        super(itemView);
        proj_ite_content22 = itemView.findViewById(R.id.proj_ite_content22);
        proj_item_delete22 =  itemView.findViewById(R.id.proj_item_delete22);
        proj_item_layout22 = itemView.findViewById(R.id.proj_item_layout22);

    }
}
