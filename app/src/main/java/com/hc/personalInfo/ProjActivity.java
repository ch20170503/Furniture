package com.hc.personalInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.bean.OrganizeOne;
import com.hc.furniture.R;

public class ProjActivity extends AppCompatActivity {
    private OrganizeOne organizeOne;
    private TextView pro_xiangmumingcheng, pro_dianhua, pro_dianziyouxiang, pro_dizhi, pro_riqi, pro_beizhu;
    private ImageView backproj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proj);
        Intent intent = getIntent();
        organizeOne = (OrganizeOne) intent.getSerializableExtra("ORGANIZEONE");
        setData();
    }


    //设置数据源
    private void setData() {
        pro_xiangmumingcheng = findViewById(R.id.pro_xiangmumingcheng);
        pro_dianhua = findViewById(R.id.pro_dianhua);
        pro_dianziyouxiang = findViewById(R.id.pro_dianziyouxiang);
        pro_dizhi = findViewById(R.id.pro_dizhi);
        pro_riqi = findViewById(R.id.pro_riqi);
        pro_beizhu = findViewById(R.id.pro_beizhu);
        pro_xiangmumingcheng.setText(organizeOne.getsFullName());
        pro_dianhua.setText(organizeOne.getsTelePhone());
        pro_dianziyouxiang.setText(organizeOne.getsEmail());
        pro_dizhi.setText(organizeOne.getsAddress());
        pro_riqi.setText(organizeOne.getsCreatedTime());
        pro_beizhu.setText(organizeOne.getsRemark());
        backproj = findViewById(R.id.backproj);
        backproj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
