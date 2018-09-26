package com.hc.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hc.bean.InformationMany;
import com.hc.furniture.R;

import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.ViewHolder>{

    private List<InformationMany> informationManyList;
    private static final String TAG = "AlertAdapter";
    Context context;
    public AlertAdapter(Context context,List<InformationMany> informationManyList) {
        this.context = context;
        this.informationManyList = informationManyList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.alert_item, viewGroup, false);
        AlertAdapter.ViewHolder viewHolder = new AlertAdapter.ViewHolder(view);
        return viewHolder;
    }


    //设置数据
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.alert_item_zhucebao.setText(informationManyList.get(i).getsObjectResult().getsRegPackage());
        viewHolder.alert_item_mingcheng.setText(informationManyList.get(i).getsTypeName());
        switch (informationManyList.get(i).getsTypeIndex()){
            case 0:
                viewHolder.alert_item_leixing.setText("主机断线");
                break;
            case 1:
                viewHolder.alert_item_leixing.setText("主机电压超压");
                break;
            case 2:
                viewHolder.alert_item_leixing.setText("主机电压欠压");
                break;
            case 3:
                viewHolder.alert_item_leixing.setText("主机门被打开");
                break;
            case 4:
                viewHolder.alert_item_leixing.setText("主机功率异常");
                break;
            case 11:
                viewHolder.alert_item_leixing.setText("分控断线");
                break;
            case 12:
                viewHolder.alert_item_leixing.setText("分控功率异常");
                break;
        }



        Log.e(TAG,"informationManyList.get(i).getsObjectResult().getsFullName()-----"+informationManyList.get(i).getsObjectResult().getsFullName());
    }

    @Override
    public int getItemCount() {
        return informationManyList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView alert_item_zhucebao,alert_item_mingcheng,alert_item_leixing;
        ViewHolder(View itemView) {
            super(itemView);
            alert_item_zhucebao =  itemView.findViewById(R.id.alert_item_zhucebao);
            alert_item_mingcheng = itemView.findViewById(R.id.alert_item_mingcheng);
            alert_item_leixing = itemView.findViewById(R.id.alert_item_leixing);
        }
    }



}
