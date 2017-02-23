package com.etouse.flowviewpage.util;


import com.etouse.flowviewpage.app.FlowApplication;

/**
 * Created by zhao on 2016/10/15.
 */
public class Util {

    public static void runOnInThread(Runnable runnable){
        FlowApplication.mHandler.post(runnable);
    }
}
