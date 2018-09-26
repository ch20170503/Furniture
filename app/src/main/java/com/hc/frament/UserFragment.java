package com.hc.frament;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hc.bean.UserInfoList.ListData;
import com.hc.bean.UserInfoList.UserList;
import com.hc.furniture.R;

import com.hc.home.DevierActivity;
import com.hc.http.HostHttp;
import com.hc.user.UserUpdataActivity;
import com.hc.useradapter.ItemRemoveRecyclerView;
import com.hc.useradapter.MyAdapter;
import com.hc.useradapter.OnItemClickListener;
import com.hc.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    private List<ListData> listData = new ArrayList<>();

    private ItemRemoveRecyclerView proj_re_container22;
    private SwipeRefreshLayout proj_srfl22;
    private ProgressDialog progressDialog = null;
    private static final int MESSAGETYPE_01 = 0x0001;

    public UserFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        proj_srfl22 = view.findViewById(R.id.proj_srfl2);
        proj_re_container22 = view.findViewById(R.id.proj_re_container2);
        setData();
        proj_srfl22.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //初始化
                listData.clear();
                setData();
                proj_srfl22.setRefreshing(false);
            }
        });
        return view;
    }
    //设置数据
    private void setData(){
        progressDialog = ProgressDialog.show(getActivity(), "加载中", "请少稍后");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserList list = GsonUtil.selectUserList();
                if (list != null) {
                    for (int i = 0; i < list.getsData().getsListData().size(); i++) {
                        ListData listData1 = new ListData();
                        listData1 = list.getsData().getsListData().get(i);
                        listData.add(listData1);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final MyAdapter adapter = new MyAdapter(getActivity(), listData);
                            proj_re_container22.setLayoutManager(new LinearLayoutManager(getActivity()));
                            proj_re_container22.setAdapter(adapter);
                            LinearLayoutManager l = new LinearLayoutManager(getActivity());
                            proj_re_container22.setLayoutManager(l);
                            proj_re_container22.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    //点击事件
                                   ListData org = listData.get(position);
                                    Intent intent = new Intent();
                                    intent.setClass(getActivity(), UserUpdataActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("USERUPDATA", org);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                                @Override
                                public void onDeleteClick(final int position) {
                                    //删除
                                    new AlertDialog.Builder(getActivity()).setTitle("删除")
                                            .setMessage("是否删除")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            ListData org = listData.get(position);
                                                            boolean b = deleteproj(org.getsId());
                                                            if(b == true){
                                                                getActivity(). runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        GsonUtil.showToasts(getActivity(),"删除成功",1 * 1000);

                                                                        adapter.removeItem(position);
                                                                    }
                                                                });

                                                            }else{
                                                                getActivity().runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        GsonUtil.showToasts(getActivity(),"删除失败",1 * 1000);
                                                                    }
                                                                });
                                                            }
                                                        }
                                                    }).start();

                                                }
                                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//响应事件
                                        }
                                    }).show();//在按键响应事件中显示此对话框
                                }
                            });
                        }
                    });
                    //发送handler
                    Message msg_listData = new Message();
                    msg_listData.what = MESSAGETYPE_01;
                    handler.sendMessage(msg_listData);
                } else {
                    getActivity(). runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GsonUtil.showToasts(getActivity(),"无信息",1 * 1000);

                        }
                    });
                    //发送handler
                    Message msg_listData = new Message();
                    msg_listData.what = MESSAGETYPE_01;
                    handler.sendMessage(msg_listData);
                    return;
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case MESSAGETYPE_01:
                    //刷新UI，显示数据，并关闭进度条
                    progressDialog.dismiss(); //关闭进度条
                    break;
            }
        }
    };

    //删除
    private boolean deleteproj(final String deleteId) {
        List<String> list = new ArrayList<String>();
        list.add(deleteId);
        boolean b = HostHttp.conhttp(GsonUtil.urlT+"/Account/Delete",null,list,null,null);
        return b;
    }
}
