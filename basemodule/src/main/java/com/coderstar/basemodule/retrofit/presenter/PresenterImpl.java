package com.coderstar.basemodule.retrofit.presenter;

import android.content.Context;

import com.coderstar.basemodule.retrofit.bean.ActionResult;
import com.coderstar.basemodule.retrofit.bean.ResponseBean;
import com.coderstar.basemodule.retrofit.model.BaseModelImpl;
import com.coderstar.basemodule.retrofit.view.IBaseView;

import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Created by coderstar on 2018-07-13.
 * 功能描述：
 */

public class PresenterImpl extends BasePresenterImp<IBaseView,ResponseBean> implements IBasePresenter{
    private Context context = null;
    private BaseModelImpl baseModel = null;

    /**
     * 构造方法，具体业务的视图接口对象
     *
     * @param view
     */
    public PresenterImpl (IBaseView view,Context context) {
        super (view);
        this.context = context;
        this.baseModel = new BaseModelImpl (context);
    }



    @Override
    public void loadData (String url,String methodName, Map paraMap) {
        baseModel.loadData (url,methodName,paraMap,this);
    }

    @Override
    public void uploadFile (String url, String methodMethod, Map paraMap, MultipartBody.Part[] filePart) {
        baseModel.upload (url,methodMethod,paraMap,filePart,this);
    }


    @Override
    public void loadData (String url, Map paraMap) {
        baseModel.loadData (url,paraMap,this);
    }

    @Override
    public void unSubscribe () {

    }


}
