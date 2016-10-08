package com.lyj.myapplication.util;

import android.os.Handler;

/**
 * Created by lyj on 2016/10/8.
 */

public class ThreadUtils {
    //子线程执行task
    public static void runInThread(Runnable task){
        new Thread(task).start();
    }

    public static Handler handler = new Handler();

    //UI线程执行task
    public static void runInUIThread(Runnable task){
        handler.post(task);
    }
}
