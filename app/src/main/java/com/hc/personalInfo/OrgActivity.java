package com.hc.personalInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.bean.OrganizeOne;
import com.hc.bean.UserMany;
import com.hc.furniture.R;

public class OrgActivity extends AppCompatActivity {
private OrganizeOne organizeOne;
private TextView org_jigoumingcheng,org_bieming,org_dianhua,org_dianziyouxiang,org_dizhi,org_riqi;
    private ImageView backorg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org);
        Intent intent = getIntent();
        organizeOne = (OrganizeOne) intent.getSerializableExtra("ORGANIZEONE2");
        setData();
    }
    //设置数据源
    private void setData() {
        org_jigoumingcheng = findViewById(R.id.org_jigoumingcheng);
        org_bieming = findViewById(R.id.org_bieming);
        org_dianhua = findViewById(R.id.org_dianhua);
        org_dianziyouxiang = findViewById(R.id.org_dianziyouxiang);
        org_dizhi = findViewById(R.id.org_dizhi);
        org_riqi = findViewById(R.id.org_riqi);
        org_jigoumingcheng.setText(organizeOne.getsFullName());
        org_dianhua.setText(organizeOne.getsTelePhone());
        org_dianziyouxiang.setText(organizeOne.getsEmail());
        org_dizhi.setText(organizeOne.getsAddress());
        org_riqi.setText(organizeOne.getsCreatedTime());
        org_bieming.setText(organizeOne.getsRemark());
        backorg = findViewById(R.id.backorg);
        backorg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
