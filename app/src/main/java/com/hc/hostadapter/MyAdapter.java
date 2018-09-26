package com.hc.hostadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hc.bean.host.ListData;
import com.hc.furniture.R;

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

        return new MyViewHolder(mInflater.inflate(R.layout.recyclerview_item_layout_proj, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        ListData organ = organList.get(position);
        viewHolder.proj_ite_content.setText(organ.getsFullName());
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

}
