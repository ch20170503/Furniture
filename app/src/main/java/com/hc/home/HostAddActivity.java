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
import com.hc.furniture.MainHome;
import com.hc.furniture.R;
import com.hc.http.HostHttp;
import com.hc.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class HostAddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText proactivity_info_zh22, proactivity_info_zh122, proactivity_info_zh222, proactivity_info_zh322, proactivity_info_zh422, proactivity_info_zh522;
    private ImageView backhostinfo22,savehostinfo22;
    private ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_add);
        Intent intent = this.getIntent();
        listData = (ListData) intent.getSerializableExtra("HOSTADD");
        backhostinfo22 = findViewById(R.id.backhostinfo22);
        savehostinfo22 = findViewById(R.id.savehostinfo22);
        backhostinfo22.setOnClickListener(this);
        savehostinfo22.setOnClickListener(this);
        proactivity_info_zh22 = findViewById(R.id.proactivity_info_zh22);
        proactivity_info_zh122 = findViewById(R.id.proactivity_info_zh122);
        proactivity_info_zh222 = findViewById(R.id.proactivity_info_zh222);
        proactivity_info_zh322 = findViewById(R.id.proactivity_info_zh322);
        proactivity_info_zh422 = findViewById(R.id.proactivity_info_zh422);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backhostinfo22:
                finish();
                break;
            case R.id.savehostinfo22:
                //保存修改
                add();
                break;
        }
    }
    private void add(){
        HostUpdataList hostUpdataList = new HostUpdataList();
        hostUpdataList.setPhoneNum(listData.getsPhoneNum());
        hostUpdataList.setId(null);
        hostUpdataList.setPhoneServerPass(listData.getsPhoneServerPass());
        hostUpdataList.setFullName(proactivity_info_zh22.getText().toString());
        hostUpdataList.setAddress(proactivity_info_zh322.getText().toString());
        hostUpdataList.setDataItemDetail_Id("e3183cfa-3011-4bee-b30e-a8f100b652f5");
        hostUpdataList.setHeartBag(proactivity_info_zh222.getText().toString());
        hostUpdataList.setIsEnergySwitch(listData.getsIsEnergySwitch());
        hostUpdataList.setIsLocked(listData.getsIsLocked());
        hostUpdataList.setOrganize_Id(listData.getsOrganId());
        hostUpdataList.setPhoneOperator(listData.getsPhoneOperator());
        hostUpdataList.setPhoneResidualFlow(listData.getsPhoneResidualFlow());
        hostUpdataList.setRegPackage(proactivity_info_zh122.getText().toString());
        hostUpdataList.setRemark(proactivity_info_zh422.getText().toString());
        final List<HostUpdataList> lists = new ArrayList<>();
        lists.add(hostUpdataList);
        new Thread(new Runnable(){
            @Override
            public void run() {
                boolean b = HostHttp.conhttp(GsonUtil.urlT+"/Host/AddHosts",lists,null,null,null);
                if (b){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GsonUtil.showToasts(HostAddActivity.this,"添加成功",1 * 1000);
                            finish();
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GsonUtil.showToasts(HostAddActivity.this,"添加失败",1 * 1000);
                        }
                    });
                }
            }
        }).start();
    }
}
