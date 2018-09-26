package com.hc.frament;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hc.furniture.R;
import com.hc.set.AlertSetActivity;
import com.hc.set.ProjtActivity;
import com.hc.set.ZiDInfoActivity;
import com.maiml.library.BaseItemLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetFragment extends Fragment {
    private BaseItemLayout layout;


    public SetFragment() {
        // Required empty public constructor
    }
private RelativeLayout set_rl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_set, container, false);
        layout = view.findViewById(R.id.layout_set);
        setData();
        return view;
    }

    private void setData(){
        //存放Text显示(基础信息)
        String Uset = "项目信息设置";
        String Proj = "警报信息设置";
        String Org = "字典信息设置";
        final List<String> valueList = new ArrayList<>();
        valueList.add(Uset);
        valueList.add(Proj);
        valueList.add(Org);
        //存放图片
        List<Integer> resIdList = new ArrayList<>();
        resIdList.add(R.drawable.shape);
        resIdList.add(R.drawable.file);
        resIdList.add(R.drawable.computer);
        layout.setValueList(valueList) // 文字 list
                .setResIdList(resIdList) //设置图片
                .create();
        layout.setOnBaseItemClick(new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getContext(), ProjtActivity.class);
                    /*    Bundle bundle = new Bundle();
                        UserMany userMany = resultInfo.getsDataInfo().getsUserMany().get(0);
                        bundle.putSerializable("USERMANY", userMany);
                        intent.putExtras(bundle);*/
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getContext(), AlertSetActivity.class);
                    /*    Bundle bundle = new Bundle();
                        UserMany userMany = resultInfo.getsDataInfo().getsUserMany().get(0);
                        bundle.putSerializable("USERMANY", userMany);
                        intent.putExtras(bundle);*/
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getContext(), ZiDInfoActivity.class);
                    /*    Bundle bundle = new Bundle();
                        UserMany userMany = resultInfo.getsDataInfo().getsUserMany().get(0);
                        bundle.putSerializable("USERMANY", userMany);
                        intent.putExtras(bundle);*/
                        startActivity(intent2);
                        break;

                }
            }
        });
    }

}
