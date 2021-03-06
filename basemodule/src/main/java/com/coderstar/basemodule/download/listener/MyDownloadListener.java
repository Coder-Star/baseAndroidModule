package com.coderstar.basemodule.download.listener;

/**
 * Created by coderstar on 2018-08-21 17:31.
 * 功能描述：
 * 下载相关的接口
 */

public interface MyDownloadListener {
    void onStart();

    void onProgress(int currentLength);

    void onFinish(String localPath);

    void onFailure(String errorInfo);
}
