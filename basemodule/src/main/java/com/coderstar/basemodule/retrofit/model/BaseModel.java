package com.coderstar.basemodule.retrofit.model;

import com.coderstar.basemodule.retrofit.helper.RetrofitManager;

/**
 * Created by coderstar on 2018-07-12.
 * 功能描述：
 *  * 业务对象基类
 */

public class BaseModel {
    //retrofit请求数据的管理类
    //retrofit请求数据的管理类
    public RetrofitManager retrofitManager;

    public BaseModel() {
        retrofitManager = RetrofitManager.builder();
    }
}
