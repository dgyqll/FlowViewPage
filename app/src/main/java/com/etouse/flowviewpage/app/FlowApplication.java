package com.etouse.flowviewpage.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by Administrator on 2017/1/6.
 */

public class FlowApplication extends Application {
    private static FlowApplication instance;
    //全局上下文
    public static Context mContext;
    //全局Handler
    public static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        instance=this;
        caughtException();
    }



    public static boolean isNetConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager)instance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    public static FlowApplication getContext(){
        return instance;
    }

    private void caughtException() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                //1.检查本地异常日志,发送服务器
                //TODO
                //2.捕获异常保存到本地
                File file = new File(Environment.getExternalStorageDirectory(),"err.log");
                PrintStream err;
                try {
                    err = new PrintStream(file);
                    ex.printStackTrace(err);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //3.杀死自己
//                Process.killProcess(Process.myPid());
            }});

    }
}
