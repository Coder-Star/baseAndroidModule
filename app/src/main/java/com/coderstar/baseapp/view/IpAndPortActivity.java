package com.coderstar.baseapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.coderstar.basemodule.activity.ToolBarActivity;
import com.coderstar.basemodule.util.MethodsUtils;
import com.coderstar.basemodule.view.edittext.ip.IPEditText;
import com.coderstar.basemodule.view.edittext.ip.IPView;
import com.coderstar.baseapp.R;

import butterknife.BindView;

public class IpAndPortActivity extends ToolBarActivity {
    @BindView (R.id.ip_ET)
    IPView ip_ET;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_ip_and_port;
    }

    @Override
    protected void initView () {

    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {

    }

    public void onClick (View view) {
        if(view.getId () == R.id.read_btn){
            byte[] temp = ip_ET.getBytesWithIP ();
            Log.d (TAG," = " + MethodsUtils.METHODS_UTILS.byteToHexString (temp));
            Log.d (TAG," = " + ip_ET.getAbsEditText ().toString ());
        }else if(view.getId () == R.id.set_btn){
            ip_ET.setGatewayText (new String[]{"1","2","3","4"});
        }
    }
}
