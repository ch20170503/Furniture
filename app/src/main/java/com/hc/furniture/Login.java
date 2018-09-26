package com.hc.furniture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hc.bean.ResultInfo;
import com.hc.http.Loginhttp;
import com.hc.util.GsonUtil;

import java.util.Calendar;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";

    //用户名，密码
    private EditText userName,userPwd;
    private Button LoginButton;
    private String userNameValue,passwordValue;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setEditText();
    }
    private void setEditText(){
        //获取控件ID
        userName = findViewById(R.id.login_etName);
        userPwd = findViewById(R.id.login_etPwd);
        LoginButton = findViewById(R.id.login_button);
        LoginButton.setOnClickListener(new GsonUtil.NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                userNameValue = userName.getText().toString();
                passwordValue = userPwd.getText().toString();
                boolean b = GsonUtil.isNetworkAvailable(getApplicationContext());
                if (b ==true){
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            ResultInfo resultInfo = Loginhttp.LoginPost(userNameValue,passwordValue);
                            if(resultInfo != null){
                                if (resultInfo.getsResultType() == 3){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Intent intent = new Intent();
                                    intent.setClass(Login.this, MainHome.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("RESULTINFO", resultInfo);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();  //跳转
                                }else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            GsonUtil.showToasts(Login.this,"登录失败",1 * 1000);
                                        }
                                    });
                                }
                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        GsonUtil.showToasts(Login.this,"登录失败",1 * 1000);
                                    }
                                });
                            }
                        }
                    }.start();

                }else{
                    GsonUtil.showToasts(Login.this,"请打开检查网络",1 * 1000);

                }

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 实现再按一次退出
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                GsonUtil.showToasts(Login.this,"再按一次退出",1 * 1000);
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
