package com.hc.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hc.bean.UserInfoList.ListData;
import com.hc.bean.UserInfoList.UserUpdataList;
import com.hc.furniture.R;
import com.hc.http.HostHttp;
import com.hc.util.GsonUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class UserUpdataActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UserUpdataActivity";
    private ImageView backuserupdata, updatauser;
    private EditText user_updata_name, user_updata_niname, user_updata_zhenname, user_updata_email, user_updata_phone;
private ListData listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_updata);
        Intent intent = getIntent();
        listData = (ListData) intent.getSerializableExtra("USERUPDATA");

        backuserupdata = findViewById(R.id.backuserupdata);
        updatauser = findViewById(R.id.updatauser);
        backuserupdata.setOnClickListener(this);
        updatauser.setOnClickListener(this);
        user_updata_name = findViewById(R.id.user_updata_name);
        user_updata_niname = findViewById(R.id.user_updata_niname);
        user_updata_zhenname = findViewById(R.id.user_updata_zhenname);
        user_updata_email = findViewById(R.id.user_updata_email);
        user_updata_phone = findViewById(R.id.user_updata_phone);
        user_updata_phone.setInputType(InputType.TYPE_CLASS_PHONE);//电话
        user_updata_email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        setData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backuserupdata:
                finish();
                break;
            case R.id.updatauser:
                updata();
                break;
        }
    }

    //设置用户信息
    private void setData(){
        user_updata_name.setText(listData.getsUserName());
                user_updata_niname.setText(listData.getsNickName());
        user_updata_zhenname.setText(listData.getsRealName());
                user_updata_email.setText(listData.getsEmail());
        user_updata_phone.setText(listData.getsPhoneNumber());
    }
    //修改
    private void updata() {
        boolean b = GsonUtil.iSphone(user_updata_phone.getText().toString());
        if (b == true){
            boolean b1 = GsonUtil.isEmail(user_updata_email.getText().toString());
            if (b1 == true){
                UserUpdataList userMany = new UserUpdataList();
                userMany.setId(listData.getsId());
                userMany.setCreatedTime(listData.getsCreatedTime());
                userMany.setEmail(user_updata_email.getText().toString());
                userMany.setLevel(2);
                userMany.setLocked(false);
                userMany.setLockoutEnabled(listData.getsLockoutEnabled());
                userMany.setNickName(user_updata_niname.getText().toString());
                userMany.setLoginCount(0);
                userMany.setOrganize_Id("3b709177-ca65-43a1-b99a-a8f100f33bb0");
                userMany.setOrganizeName(listData.getsOrganizeName());
                userMany.setUserName(listData.getsUserName());
                userMany.setRealName(user_updata_zhenname.getText().toString());
                userMany.setPermissionList("1,2,3");
                userMany.setPhoneNumber(user_updata_phone.getText().toString());
                final List<UserUpdataList> lists = new ArrayList<UserUpdataList>();
                lists.add(userMany);
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        boolean b = HostHttp.conhttp(GsonUtil.urlT+"/Account/EditData",null,null,null,lists);
                        if (b){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    GsonUtil.showToasts(UserUpdataActivity.this,"修改成功",1 * 1000);
                                    finish();
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    GsonUtil.showToasts(UserUpdataActivity.this,"修改失败",1 * 1000);
                                }
                            });
                        }
                    }
                }).start();
            }else{
                GsonUtil.showToasts(UserUpdataActivity.this,"请正确输入邮箱或者电话",1 * 1000);
            }
        }else{
            GsonUtil.showToasts(UserUpdataActivity.this,"请正确输入邮箱或者电话",1 * 1000);
        }



    }
}
