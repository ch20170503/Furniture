package com.hc.personalInfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.hc.Adapter.AlertAdapter;
import com.hc.bean.InformationMany;
import com.hc.furniture.R;

import java.util.List;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AlertActivity";
    private List<InformationMany> list;
 private RecyclerView mRecyclerView;
    private  AlertAdapter mAdapter;
    private ImageView backale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        list = (List<InformationMany>) getIntent().getSerializableExtra("LISTDETAIL");
        backale = findViewById(R.id.backale);
        backale.setOnClickListener(this);
        setData();
    }
    private void setData(){
        mRecyclerView =   findViewById(R.id.alert_rec);
        mAdapter = new AlertAdapter(this,list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
