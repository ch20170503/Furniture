package com.hc.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hc.bean.host.HostList;
import com.hc.bean.host.ListData;
import com.hc.devieradapter.LampControlView;
import com.hc.devieradapter.MyAdapter;
import com.hc.furniture.R;
import com.hc.http.TimeConHttp;
import com.hc.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class DevierActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backdev;
    private List<ListData> listData = new ArrayList<>();
    private SwipeRefreshLayout loop_list_srfl;
    private RecyclerView loop_list_recyclerview;
    private ProgressDialog progressDialog = null;
    private static final int MESSAGETYPE_01 = 0x0001;
    private boolean isPause;
    private RelativeLayout activity_all_lamp_controller;
    public static PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devier);
        backdev = findViewById(R.id.backdev);
        backdev.setOnClickListener(this);
        loop_list_srfl = findViewById(R.id.loop_list_srfl);
        loop_list_recyclerview = findViewById(R.id.loop_list_recyclerview);
        activity_all_lamp_controller = findViewById(R.id.activity_all_lamp_controller);
        setdata();
        loop_list_srfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //初始化
                listData.clear();
                setdata();
                loop_list_srfl.setRefreshing(false);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backdev:
                finish();
                break;
        }
    }

    private void setdata() {
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
                            MyAdapter adapter = new MyAdapter(DevierActivity.this, listData);
                            loop_list_recyclerview.setLayoutManager(new LinearLayoutManager(DevierActivity.this));
                            loop_list_recyclerview.setAdapter(adapter);
                            LinearLayoutManager l = new LinearLayoutManager(DevierActivity.this);
                            loop_list_recyclerview.setLayoutManager(l);
                            adapter.setOnItemClickListener(new MyAdapter.ItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                                    belowwindows(listData.get(position));
                                }

                                @Override
                                public void onItemLongClick(RecyclerView.ViewHolder holder, int position) {
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

                            GsonUtil.showToasts(DevierActivity.this,"无信息：",1 * 1000);

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


    //底部弹框
    public void belowwindows(final ListData listData) {
        //activity_all_lamp_controller.setBackgroundResource(R.color.colorTextGary);
        setBackgroundAlpha(0.5f);
        LampControlView tempControl;
        //loop_list_recyclerview.setVisibility(View.INVISIBLE);
        //设置布局
        View popView = LayoutInflater.from(DevierActivity.this).inflate(R.layout.activity_onelamppw, null);
        //设置宽高
        mPopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        //设置背景颜色
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x30000000));
        //设置动画
        mPopupWindow.setAnimationStyle(R.style.Animation_Button_Dialog);
        //参数1:根视图，整个Window界面的最顶层View 参数2:显示位置
        mPopupWindow.showAtLocation(DevierActivity.this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        tempControl = popView.findViewById(R.id.temp_control);
        tempControl.setOnTempChangeListener(new LampControlView.OnTempChangeListener() {
            @Override
            public void change(final int temp) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TimeConHttp.HostPost(listData.getsRegPackage()+","+"1"+","+"1"+","+temp+","+"0");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GsonUtil.showToasts(DevierActivity.this,"亮度："+temp,1 * 1000);
                            }
                        });
                    }
                }).start();


            }

        });
        tempControl.setOnSlideListener(new LampControlView.OnSlideListener() {
            @Override
            public void Slide(int temp) {

            }
        });
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //activity_all_lamp_controller.setBackgroundResource(R.color.huise);
                //loop_list_recyclerview.setVisibility(View.VISIBLE);
                setBackgroundAlpha(1.0f);
            }
        });
    }


    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
