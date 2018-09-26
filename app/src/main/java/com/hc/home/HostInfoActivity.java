package com.hc.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hc.bean.host.HostUpdataList;
import com.hc.bean.host.ListData;
import com.hc.furniture.R;
import com.hc.http.HostHttp;
import com.hc.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class HostInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView backhostinfo,savehostinfo;
    private ListData listData;
    private EditText proactivity_info_zh, proactivity_info_zh1, proactivity_info_zh2, proactivity_info_zh3, proactivity_info_zh4, proactivity_info_zh5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_info);
        backhostinfo = findViewById(R.id.backhostinfo);
        savehostinfo = findViewById(R.id.savehostinfo);
        backhostinfo.setOnClickListener(this);
        savehostinfo.setOnClickListener(this);
        Intent intent = this.getIntent();
        listData = (ListData) intent.getSerializableExtra("PROJINFOACTIVITY");
        proactivity_info_zh = findViewById(R.id.proactivity_info_zh);
        proactivity_info_zh1 = findViewById(R.id.proactivity_info_zh1);
        proactivity_info_zh2 = findViewById(R.id.proactivity_info_zh2);
        proactivity_info_zh3 = findViewById(R.id.proactivity_info_zh3);
        proactivity_info_zh4 = findViewById(R.id.proactivity_info_zh4);
        proactivity_info_zh5 = findViewById(R.id.proactivity_info_zh5);
        setData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backhostinfo:
                finish();
                break;
            case R.id.savehostinfo:
                //保存修改
                updata();
                break;
        }
    }


    //设置
    private void setData() {
        proactivity_info_zh.setText(listData.getsFullName().toString());
        proactivity_info_zh1.setText(listData.getsRegPackage().toString());
        proactivity_info_zh2.setText(listData.getsHeartBag().toString());
        proactivity_info_zh3.setText(listData.getsAddress().toString());
        proactivity_info_zh4.setText(listData.getsRemark().toString());
        proactivity_info_zh5.setText(listData.getsCreatedTime().toString());
    }

    //保存
    private void updata(){
        HostUpdataList hostUpdataList = new HostUpdataList();
        hostUpdataList.setId(listData.getsId());
        hostUpdataList.setPhoneNum(listData.getsPhoneNum());
        hostUpdataList.setPhoneServerPass(listData.getsPhoneServerPass());
        hostUpdataList.setFullName(proactivity_info_zh.getText().toString());
        hostUpdataList.setAddress(proactivity_info_zh3.getText().toString());
        hostUpdataList.setDataItemDetail_Id("e3183cfa-3011-4bee-b30e-a8f100b652f5");
        hostUpdataList.setHeartBag(proactivity_info_zh2.getText().toString());
        hostUpdataList.setIsEnergySwitch(listData.getsIsEnergySwitch());
        hostUpdataList.setIsLocked(listData.getsIsLocked());
        hostUpdataList.setOrganize_Id(listData.getsOrganId());
        hostUpdataList.setPhoneOperator(listData.getsPhoneOperator());
        hostUpdataList.setPhoneResidualFlow(listData.getsPhoneResidualFlow());
        hostUpdataList.setRegPackage(listData.getsRegPackage());
        hostUpdataList.setRemark(proactivity_info_zh3.getText().toString());
        final List<HostUpdataList> lists = new ArrayList<>();
        lists.add(hostUpdataList);
        new Thread(new Runnable(){
            @Override
            public void run() {
                boolean b = HostHttp.conhttp(GsonUtil.urlT+"/Host/EditHosts",lists,null,null,null);
                if (b){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GsonUtil.showToasts(HostInfoActivity.this,"修改成功",1 * 1000);
                            finish();
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GsonUtil.showToasts(HostInfoActivity.this,"当前主机正在使用不能修改",1 * 1000);
                        }
                    });
                }
            }
        }).start();
    }
}
