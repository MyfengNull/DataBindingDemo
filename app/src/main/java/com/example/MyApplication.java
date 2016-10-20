package com.example;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yangshirong on 2016/10/20 20:02.
 * 邮箱 ysr200808@163.com
 */

public class MyApplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
