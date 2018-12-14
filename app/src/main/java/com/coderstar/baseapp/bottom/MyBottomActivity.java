package com.coderstar.baseapp.bottom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.coderstar.basemodule.activity.bottom.BaseBottomBarActivity;
import com.coderstar.baseapp.R;
import com.coderstar.baseapp.bottom.fragments.AFragment;
import com.coderstar.baseapp.bottom.fragments.BFragment;
import com.coderstar.baseapp.bottom.fragments.CFragment;

public class MyBottomActivity extends BaseBottomBarActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_my_bottom;
    }

    @Override
    protected void initView () {
        super.initView ();
        //添加页面
        addItem (new AFragment (),R.drawable.permission_ic_camera,"A页面",R.color.colorPrimary);
        addItem (new BFragment (),R.drawable.permission_ic_contacts,"B页面",R.color.colorPrimary);
        addItem (new CFragment (),R.drawable.permission_ic_location,"C页面",R.color.colorPrimary);
        //设置样式
        setNavBarStyle (BottomNavigationBar.MODE_FIXED,BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        initialise (R.id.ll_content);
    }

    @Override
    public void onTabSelected (int position) {
        super.onTabSelected (position);
    }
}
