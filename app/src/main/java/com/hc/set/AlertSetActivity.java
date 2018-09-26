package com.hc.set;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hc.bean.ResultInfo;
import com.hc.furniture.Login;
import com.hc.furniture.MainHome;
import com.hc.furniture.R;
import com.hc.http.Loginhttp;
import com.hc.util.GsonUtil;

public class AlertSetActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AlertSetActivity";
    private ImageView backalerts,addhostalert;
private EditText numbshezhi,neirongshezhi;
private Spinner alert_type;
    private Spinner zhouqi;
    private ProgressDialog progressDialog = null;
    private static final int MESSAGETYPE_01 = 0x0001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_set);
        backalerts = findViewById(R.id.backalerts);
        backalerts.setOnClickListener(this);
        addhostalert = findViewById(R.id.addhostalert);
        addhostalert.setOnClickListener(this);

        numbshezhi = findViewById(R.id.numbshezhi);
        neirongshezhi = findViewById(R.id.neirongshezhi);
        alert_type = findViewById(R.id.alert_type);
        zhouqi = findViewById(R.id.zhouqi);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backalerts:
                finish();
                break;
            case R.id.addhostalert:
                getdata();
                break;
        }
    }

    //发送数据
    private void getdata(){
        String numbshezhiS= numbshezhi.getText().toString();
        String neirongshezhiS=  neirongshezhi.getText().toString();
        String alert_typeS= (String) alert_type.getSelectedItem();
        String zhouqiS= (String) zhouqi.getSelectedItem();
        Log.e(TAG,numbshezhiS+"---"+neirongshezhiS+"---"+alert_typeS+"---"+zhouqiS);
        progressDialog = ProgressDialog.show(this, "加载中", "请少稍后");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        new Thread(){
            @Override
            public void run() {
                super.run();
                ResultInfo resultInfo = Loginhttp.LoginPost("leyviewroot","12345678");
                if(resultInfo != null){
                    if (resultInfo.getsResultType() == 3){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AlertSetActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                        finish();  //跳转
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GsonUtil.showToasts(AlertSetActivity.this,"失败",1 * 1000);
                                finish();  //跳转
                            }
                        });
                    }
                    //发送handler
                    Message msg_listData = new Message();
                    msg_listData.what = MESSAGETYPE_01;
                    handler.sendMessage(msg_listData);
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GsonUtil.showToasts(AlertSetActivity.this,"失败",1 * 1000);
                        }
                    });
                    //发送handler
                    Message msg_listData = new Message();
                    msg_listData.what = MESSAGETYPE_01;
                    handler.sendMessage(msg_listData);
                }
            }
        }.start();
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
}
