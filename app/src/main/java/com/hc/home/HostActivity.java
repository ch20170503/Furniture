package com.hc.home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hc.bean.host.HostList;
import com.hc.bean.host.ListData;
import com.hc.furniture.R;
import com.hc.hostadapter.ItemRemoveRecyclerView;
import com.hc.hostadapter.MyAdapter;
import com.hc.hostadapter.OnItemClickListener;
import com.hc.http.HostHttp;
import com.hc.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class HostActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backhost,addhost;
    private static final String TAG = "HostActivity";
    private List<ListData> listData = new ArrayList<>();

    private ItemRemoveRecyclerView proj_re_container;
    private SwipeRefreshLayout proj_srfl;
    private ProgressDialog progressDialog = null;
    private static final int MESSAGETYPE_01 = 0x0001;
    private boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        backhost = findViewById(R.id.backhost);
        addhost = findViewById(R.id.addhost);
        addhost.setOnClickListener(this);
        backhost.setOnClickListener(this);
        proj_srfl = findViewById(R.id.proj_srfl);
        proj_re_container = findViewById(R.id.proj_re_container);
        selectHost();
        proj_srfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //初始化
                listData.clear();
                selectHost();
                proj_srfl.setRefreshing(false);
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backhost:
                finish();
                break;
            case R.id.addhost:
                ListData org = listData.get(0);
                Intent intent = new Intent();
                intent.setClass(HostActivity.this, HostAddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("HOSTADD", org);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }


    //查询主机信息
    private void selectHost() {
        progressDialog = ProgressDialog.show(this, "加载中", "请少稍后");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HostList list = GsonUtil.selectHostList();
                if (list != null) {
                    for (int i = 0; i < list.getsData().getsListData().size(); i++) {
                        ListData listData1 = new ListData();
                        listData1 = list.getsData().getsListData().get(i);
                        listData.add(listData1);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            final MyAdapter adapter = new MyAdapter(HostActivity.this, listData);
                            proj_re_container.setLayoutManager(new LinearLayoutManager(HostActivity.this));
                            proj_re_container.setAdapter(adapter);
                            LinearLayoutManager l = new LinearLayoutManager(HostActivity.this);
                            proj_re_container.setLayoutManager(l);
                            proj_re_container.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    //点击事件
                                    ListData org = listData.get(position);
                                    Intent intent = new Intent();
                                    intent.setClass(HostActivity.this, HostInfoActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("PROJINFOACTIVITY", org);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                                @Override
                                public void onDeleteClick(final int position) {
                                    //删除
                                    new AlertDialog.Builder(HostActivity.this).setTitle("删除")
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
                                                                runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        GsonUtil.showToasts(HostActivity.this,"删除成功",1 * 1000);
                                                                        adapter.removeItem(position);
                                                                    }
                                                                });

                                                            }else{
                                                                runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        GsonUtil.showToasts(HostActivity.this,"删除失败",1 * 1000);
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GsonUtil.showToasts(HostActivity.this,"无信息",1 * 1000);
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
        boolean b = HostHttp.conhttp(GsonUtil.urlT+"/Host/DeleteHosts",null,list,null,null);
        return b;
    }



    @Override
    protected void onPause() {
        super.onPause();
        isPause = true; //记录页面已经被暂停
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (isPause){ //判断是否暂停
            isPause = false;
            listData.clear();
            selectHost();
        }
    }
}
