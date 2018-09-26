package com.hc.devieradapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hc.bean.host.ListData;
import com.hc.furniture.R;
import com.hc.hostadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class MyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;
    //创建Bean
    private List<ListData> organList = new ArrayList<>();

    private boolean b = false;
    private ListData organ;


    public MyAdapter(Context context, List<ListData> easyBeen) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        organList = easyBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_item_layout_der, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        ListData organ = organList.get(position);
        viewHolder.loop_list_item_content.setText(organ.getsRegPackage());
        viewHolder.loop_list_play_img3.setImageResource(R.drawable.greed);
        String[] timeString = organList.get(position).getsCreatedTime().split("T");
        viewHolder.loop_list_text1.setText(timeString[0]);
        String time2 = timeString[1];
        String tmmm;
        if( time2.indexOf(".") != -1){
            tmmm = time2.substring(0, time2.indexOf("."));
        }else{
            tmmm = time2;
        }
        viewHolder.loop_list_text2.setText(tmmm);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(holder,holder.getAdapterPosition());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mItemClickListener.onItemLongClick(holder,holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return organList != null ? organList.size() : 0;
    }

    public void removeItem(int position) {
            Log.e(TAG, "这是选择数据的下标" + position);
            //删除RecyclerView列表对应item
            organList.remove(position);
            notifyDataSetChanged();
    }

    /**
     * 点击事件和长按事件
     */
    public interface ItemClickListener{
        void onItemClick(RecyclerView.ViewHolder holder, int position);
        void onItemLongClick(RecyclerView.ViewHolder holder, int position);
    }

    private ItemClickListener mItemClickListener;
    public void setOnItemClickListener(ItemClickListener listener){
        this.mItemClickListener=listener;
    }
}
