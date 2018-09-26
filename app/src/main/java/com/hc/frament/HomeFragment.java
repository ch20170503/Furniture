package com.hc.frament;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.hc.furniture.R;
import com.hc.home.DevierActivity;
import com.hc.home.HostActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

private Button frg_home_host,frg_home_shebei;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        frg_home_host = view.findViewById(R.id.frg_home_host);
        frg_home_shebei = view.findViewById(R.id.frg_home_shebei);
        frg_home_host.setOnClickListener(this);
        frg_home_shebei.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.frg_home_host:
                Intent intent = new Intent(getActivity(), HostActivity.class);
                startActivity(intent);
                break;
            case R.id.frg_home_shebei:
                Intent intent1 = new Intent(getActivity(), DevierActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
