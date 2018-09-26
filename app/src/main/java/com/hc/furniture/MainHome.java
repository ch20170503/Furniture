package com.hc.furniture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hc.bean.InformationMany;
import com.hc.bean.OrganizeOne;
import com.hc.bean.ResultInfo;
import com.hc.bean.UserMany;
import com.hc.frament.HomeFragment;
import com.hc.frament.SetFragment;
import com.hc.frament.UserFragment;
import com.hc.personalInfo.AlertActivity;
import com.hc.personalInfo.OrgActivity;
import com.hc.personalInfo.PersonalaActivity;
import com.hc.personalInfo.ProjActivity;
import com.hc.user.UserAddActivity;
import com.hc.util.GsonUtil;
import com.maiml.library.BaseItemLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainHome extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener, TabLayout.BaseOnTabSelectedListener, View.OnClickListener {
    private ResultInfo resultInfo;

    //菜单容器
    private RelativeLayout mMenuLinear;
    //主容器
    private RelativeLayout mMainLinear;
    //滑动容器
    private SlidingPaneLayout mSlidingPaneLayout;
    private static final String TAG = "MainActivity";
    /* 底部导航的控件容器 */
    private TabLayout mTabContainer;

    /* 用于保存fragment的对象 */
    private Fragment[] frags;
    /* 记录选择的位置 */
    private int currPosition;

    /**
     * 存储底部导航数据源
     */
    private Menu mNavMenu;

    private long mExitTime;
    //创建Img
    private ImageView img_laytwo,addusers;


    private OnMenuController[] onMenuControllers;
    /* 创建Toolbar*/

    private TextView toole_text,set_close;
    private static final String P = "com.hancong.frament.HomeFragment";
    private static final String S = "com.hancong.frament.SetFragment";



    private BaseItemLayout layout;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        Intent intent = getIntent();
        resultInfo = (ResultInfo) intent.getSerializableExtra("RESULTINFO");
        //mainhometext = findViewById(R.id.mainhometext);
       // mainhometext.setText(resultInfo.getsDataInfo().getsUserName().toString());
        mSlidingPaneLayout = findViewById(R.id.sil_main);
        addusers = findViewById(R.id.addusers);
        addusers.setOnClickListener(this);
        toole_text = findViewById(R.id.toole_text);
        //添加滑动监听
        mSlidingPaneLayout.setPanelSlideListener(this);
        mMenuLinear = findViewById(R.id.li_one);
        mMainLinear =  findViewById(R.id.li_two);
        //获取屏幕宽度
        int widthPixels = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        //将Menu的容器宽度设置为屏幕宽度的80%
        SlidingPaneLayout.LayoutParams params = new SlidingPaneLayout.LayoutParams(widthPixels, SlidingPaneLayout.LayoutParams.MATCH_PARENT);
        mMenuLinear.setLayoutParams(params);
        mTabContainer = (TabLayout) findViewById(R.id.tab_bottom_container);
        img_laytwo = findViewById(R.id.img_laytwo);
        img_laytwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GsonUtil.showToasts(MainHome.this,"侧滑打开个人信息",1 * 1000);
            }
        });

        // 设置Toolbar 为原先的actionbar


        mTabContainer = (TabLayout) findViewById(R.id.tab_bottom_container);
        // 初始化之前先将所有的tab先清空 避免重叠
        mTabContainer.removeAllTabs();
        // 设置每一个tab的选中的事件
        mTabContainer.addOnTabSelectedListener(this);
        // 创建menu对象
        mNavMenu = new MenuBuilder(this);
        // 将menu文件加载到menu对象
        getMenuInflater().inflate(R.menu.main_menu, mNavMenu);

        // 初始化保存fragment的数组
        frags = new Fragment[mNavMenu.size()];

        // 初始化menu控制器
        onMenuControllers = new OnMenuController[mNavMenu.size()];


        for (int i = 0; i < mNavMenu.size(); i++) {
            // 创建一个tab对象
            TabLayout.Tab tab = mTabContainer.newTab();
            // 获取每一个menu中存储的数据
            MenuItem item = mNavMenu.getItem(i);
            // 设置自定义的item布局
            tab.setCustomView(getTabView(item, i));
            // 添加tab 并记录position 和设置默认选中的fragment
            mTabContainer.addTab(tab, i, i == currPosition);
        }
        setData();
    }
    /**
     * 设置TabLayout 的自定义布局
     *
     * @param item
     * @param position
     * @return
     */
    private View getTabView(MenuItem item, int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_nav_item, null);
        TextView v = (TextView) view.findViewById(R.id.tv_nav_item);
        v.setText(item.getTitle());
        // 设置TextView 上面显示的图片
        Drawable d = item.getIcon();
        d.setBounds(0, 0, 60, 60);
        v.setCompoundDrawables(null, d, null, null);

        return view;
    }

    /**
     * 当 Tab 选中
     *
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // 根据下标查找 fragment
        Fragment frag = frags[currPosition];
        // 获取 tab 中存储的标记 第一次一定是空的
        Object tag = tab.getTag();
        if (tag == null) {
            // 根据 下标获取每一个 fragment 对象
            tag = getItem(tab.getPosition());
            // 给tab 设置 标记
            tab.setTag(tag);

        }
        // 给 当前的位置 赋值
        currPosition = tab.getPosition();

        // 通知菜单更新
        invalidateOptionsMenu();

        // 显示 fragment
        switchFragment(frag, (Fragment) tag);

    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * fragment选中
     *
     * @param from 将要隐藏fragment
     * @param to   将要显示的fragment
     *             <p/>
     *             通过 FragmentTransaction 的show和hide方式 可以控制 fragment的生命周期
     */
    private void switchFragment(Fragment from, Fragment to) {
        // 获取fragment的管理对象
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 获取 fragment 事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (from != null) {
            // 隐藏fragment
            fragmentTransaction.hide(from);
        }
        // 根据 添加 fragment 时设置的标记 查找 fragment
        Fragment fragTo = fragmentManager.findFragmentByTag(to.getClass().getName());

        if (fragTo == null) {
            // 将 fragment显示在 容器控件中 并且设置标记
            fragmentTransaction.add(R.id.fl_main_container, to, to.getClass().getName());
        } else {
            // 当第二次进入 fragTo 不为空 直接显示这个fragment
            fragmentTransaction.show(fragTo);
        }
        // 提交事务
        fragmentTransaction.commit();
        if (to.getClass().getName().equals(P)) {
            toole_text.setText("首页");
            addusers.setVisibility(View.GONE);
        } else if (to.getClass().getName().equals(S)) {
            toole_text.setText("关于");
            addusers.setVisibility(View.GONE);
        } else {
            toole_text.setText("用户");
            addusers.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 根据下标创建 fragment
     *
     * @param position
     * @return
     */
    private Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new UserFragment();
                break;
            case 2:
                fragment = new SetFragment();

            default:
                break;

        }

        // 给数组赋值
        frags[position] = fragment;
        return fragment;
    }


    @Override
    public void onPanelSlide(View view, float v) {
        //修改X Y轴缩放
        mMenuLinear.setScaleX(v / 2 + 0.5F);
        mMenuLinear.setScaleY(v / 2 + 0.5F);
        //修改 主布局 Y轴缩放
        mMainLinear.setScaleY(1 - v / 5);
    }

    @Override
    public void onPanelOpened(View view) {

    }

    @Override
    public void onPanelClosed(View view) {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,UserAddActivity.class);
        startActivity(intent);
    }

    public interface OnMenuController {

        /**
         * 用于加载menu文件
         *
         * @return
         */
        int getMenu();

        /**
         * 获取当前显示的标题
         *
         * @return
         */
        CharSequence getTitle();

        /**
         * 当 menu创建
         *
         * @param menu
         */
        void onMenuCreate(Menu menu);

        /**
         * 当 MenuItem 选中
         *
         * @param item
         */
        void onMenuItemSelected(MenuItem item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mSlidingPaneLayout.isOpen()){
            mSlidingPaneLayout.closePane();
            return true;
        }else{
            // 实现再按一次退出
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (System.currentTimeMillis() - mExitTime > 2000) {
                    GsonUtil.showToasts(MainHome.this,"再按一次退出",1 * 1000);
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }



    private void setData(){
        layout = findViewById(R.id.layout);
        set_close = findViewById(R.id.set_close);
        //存放Text显示(基础信息)
      String Uset = "个人信息";
      String Proj = "项目信息";
      String Org = "机构信息";
      String Alert = "警报信息";
        final List<String> valueList = new ArrayList<>();
        valueList.add(Uset);
        valueList.add(Proj);
        valueList.add(Org);
        valueList.add(Alert);
        //存放图片
        List<Integer> resIdList = new ArrayList<>();
        resIdList.add(R.drawable.sheets);
        resIdList.add(R.drawable.shape);
        resIdList.add(R.drawable.file);
        resIdList.add(R.drawable.computer);
        layout.setValueList(valueList) // 文字 list
                .setResIdList(resIdList) //设置图片
                .create();
        //点击item
        layout.setOnBaseItemClick(new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(int position) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(MainHome.this, PersonalaActivity.class);
                        Bundle bundle = new Bundle();
                        UserMany userMany = resultInfo.getsDataInfo().getsUserMany().get(0);
                        bundle.putSerializable("USERMANY", userMany);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainHome.this, ProjActivity.class);
                        Bundle bundle1 = new Bundle();
                        OrganizeOne organizeOne = resultInfo.getsDataInfo().getsOrganizeOne();
                        bundle1.putSerializable("ORGANIZEONE", organizeOne);
                        intent1.putExtras(bundle1);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainHome.this, OrgActivity.class);
                        Bundle bundle2 = new Bundle();
                        OrganizeOne organizeOne2 = resultInfo.getsDataInfo().getsOrganizeOne();
                        bundle2.putSerializable("ORGANIZEONE2", organizeOne2);
                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                        break;
                    case 3:
                        List<InformationMany> listdetail = new ArrayList<InformationMany>();
                        Intent intent3 = new Intent(MainHome.this, AlertActivity.class);
                         listdetail = resultInfo.getsDataInfo().getsInformationMany();
                        intent3.putExtra("LISTDETAIL", (Serializable) listdetail);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent3);
                        break;
                }
            }
        });
        set_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(MainHome.this, Login.class);
                startActivity(inte);
                finish();
            }
        });

    }



    //判断面板是否关闭
    private void closemSil(){
        //判断面板是否打开
        if (mSlidingPaneLayout.isOpen()){
            //关闭
            mSlidingPaneLayout.closePane();
        }
    }
}
