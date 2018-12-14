package com.coderstar.baseapp;

import com.coderstar.basemodule.activity.BaseApplication;
import com.coderstar.basemodule.retrofit.helper.RetrofitManager;
import com.coderstar.baseapp.xgPush.XgPush;

/**
 * Created by coderstar on 2018-08-11 16:41.
 * 功能描述：
 */

public class App extends BaseApplication{
    @Override
    public void onCreate () {
        super.onCreate ();
        RetrofitManager.RETROFIT_MANAGER.setSelfDefineHttps (true);//启动自定义证书
        RetrofitManager.RETROFIT_MANAGER.init ("https://186.168.6.201/ChpcyServer/");
//        XgPush.XG_PUSH.init (this);
    }
}
