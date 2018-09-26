package com.hc.set;

import android.app.ProgressDialog;
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
import com.hc.furniture.R;
import com.hc.http.Loginhttp;
import com.hc.util.GsonUtil;

public class ZiDInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ZiDInfoActivity";
    private ImageView backZI, addhostzi;
    private Spinner zidianshezhi, zidianleixing, shifouchaxun;
    private EditText zhidianneirong;
    private ProgressDialog progressDialog = null;
    private static final int MESSAGETYPE_01 = 0x0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_dinfo);
        backZI = findViewById(R.id.backZI);
        backZI.setOnClickListener(this);
        addhostzi = findViewById(R.id.addhostzi);
        addhostzi.setOnClickListener(this);

        zidianshezhi = findViewById(R.id.zidianshezhi);
        zidianleixing = findViewById(R.id.zidianleixing);
        shifouchaxun = findViewById(R.id.shifouchaxun);


        zhidianneirong = findViewById(R.id.zhidianneirong);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backZI:
                finish();
                break;
            case R.id.addhostzi:
                getdata();
                break;
        }
    }
    //发送数据
    private void getdata(){
        String xianmushu1= zhidianneirong.getText().toString();
        String xiangmuchakanz1= (String) zidianshezhi.getSelectedItem();
        String xiangmuzhixinganz1= (String) zidianleixing.getSelectedItem();
        String xiangmuquanxian1= (String) shifouchaxun.getSelectedItem();
        Log.e(TAG,xianmushu1+"---"+xiangmuchakanz1+"---"+xiangmuzhixinganz1+"---"+xiangmuquanxian1);
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
                                Toast.makeText(ZiDInfoActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                        finish();  //跳转
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GsonUtil.showToasts(ZiDInfoActivity.this,"失败",1 * 1000);
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
                            GsonUtil.showToasts(ZiDInfoActivity.this,"失败",1 * 1000);
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
