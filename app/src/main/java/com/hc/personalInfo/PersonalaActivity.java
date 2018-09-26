package com.hc.personalInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.bean.UserMany;
import com.hc.furniture.R;

public class PersonalaActivity extends AppCompatActivity {
    private UserMany userMany;
    //获取控件
    private TextView org_text_name,xingming,nicheng,youxiang,xingbie,chuangjianshijian;
    private ImageView backuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personala);
        Intent intent = getIntent();
        userMany = (UserMany) intent.getSerializableExtra("USERMANY");
        setData();
    }

    private void setData(){
        org_text_name = findViewById(R.id.org_text_name);
        xingming = findViewById(R.id.xingming);
        nicheng = findViewById(R.id.nicheng);
        youxiang = findViewById(R.id.youxiang );
        xingbie = findViewById(R.id.xingbie);
        chuangjianshijian = findViewById(R.id.chuangjianshijian);
        org_text_name.setText(userMany.getsCreatorUserId());
        xingming.setText(userMany.getsRealName());
        nicheng.setText(userMany.getsNickName());
        youxiang.setText(userMany.getsEmail());
        if (userMany.getsSex() == 1){
            xingbie.setText("男");
        }else{
            xingbie.setText("女");
        }
        chuangjianshijian.setText(userMany.getsCreatedTime());
        backuser = findViewById(R.id.backuser);
        backuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
