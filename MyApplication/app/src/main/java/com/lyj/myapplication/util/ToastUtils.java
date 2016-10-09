package com.lyj.myapplication.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lyj on 2016/10/9.
 */

public class ToastUtils {
    public static void ShowToast(final Context context, final String text){
        ThreadUtils.runInUIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
