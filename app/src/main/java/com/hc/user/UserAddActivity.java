package com.hc.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hc.bean.UserInfoList.ListData;
import com.hc.bean.UserInfoList.UserAdd;
import com.hc.furniture.R;
import com.hc.home.HostInfoActivity;
import com.hc.http.HostHttp;
import com.hc.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class UserAddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username_add, userpwd_user, userpwd_age;
    private ListData listData;
    private ImageView backuser, adduser;
    private List<UserAdd> userAdds = new ArrayList<UserAdd>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
        adduser = findViewById(R.id.adduser);
        backuser = findViewById(R.id.backuser);
        adduser.setOnClickListener(this);
        backuser.setOnClickListener(this);
        username_add = findViewById(R.id.username_add);
        userpwd_user = findViewById(R.id.userpwd_user);
        userpwd_age = findViewById(R.id.userpwd_age);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backuser:
                finish();
                break;
            case R.id.adduser:
                adduser();
                break;
        }
    }

    //添加用户
    private void adduser(){
        if ( userpwd_age.getText().toString().equals( userpwd_user.getText().toString())){
            UserAdd userAdd = new UserAdd();
            userAdd.setUserName( username_add.getText().toString());
            userAdd.setPassword(  userpwd_user.getText().toString());
            userAdd.setIsLocked(true);
            userAdd.setLockoutEnabled(true);
            userAdd.setPermissionList("1,2");
            userAdd.setLevel(2);
            userAdd.setOrganize_Id("3b709177-ca65-43a1-b99a-a8f100f33bb0");
            userAdds.add(userAdd);
            new Thread(new Runnable(){
                @Override
                public void run() {
                    boolean b =  HostHttp.conhttp(GsonUtil.urlT+"/Account/UserAdds",null,null,userAdds,null);
                    if (b){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GsonUtil.showToasts(UserAddActivity.this,"添加成功",1 * 1000);
                                finish();
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GsonUtil.showToasts(UserAddActivity.this,"添加失败",1 * 1000);
                            }
                        });
                    }
                }
            }).start();
        }else{
            GsonUtil.showToasts(UserAddActivity.this,"两次密码不相同",1 * 1000);
        }

    }
}
