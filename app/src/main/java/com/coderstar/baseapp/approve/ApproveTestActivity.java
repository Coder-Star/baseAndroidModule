package com.coderstar.baseapp.approve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.coderstar.basemodule.activity.approve.ApproveActivity;
import com.coderstar.baseapp.R;
import com.coderstar.baseapp.loaddata.fragment.CommonFragment;
import com.coderstar.baseapp.recyclerview.fragment.MyRecyclerFragment;
import com.coderstar.baseapp.recyclerview.fragment.MyTinyRecFragment;

import static com.coderstar.basemodule.util.ConstantUtil.APPROVENODEINSTANCEID;
import static com.coderstar.basemodule.util.ConstantUtil.METHODNAME;
import static com.coderstar.basemodule.util.ConstantUtil.SOURCEID;

public class ApproveTestActivity extends ApproveActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    public void initFragment () {
        MyTinyRecFragment myTinyRecFragment = MyTinyRecFragment.getInstance (this);
        showDetailInfo (myTinyRecFragment);
    }

}
