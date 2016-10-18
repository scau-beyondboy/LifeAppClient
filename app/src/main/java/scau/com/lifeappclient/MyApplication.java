package scau.com.lifeappclient;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import scau.com.lifeappclient.manager.ThreadManager;


/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-10-06
 * Time: 16:19
 */
public class MyApplication extends Application
{
    private static MyApplication sMyApplication;
    public static ThreadManager sThreadManager=new ThreadManager();
    //多线程安全返回单例
    public static MyApplication getInstance()
    {
        if (sMyApplication == null)
        {
            synchronized (MyApplication.class)
            {
                if (sMyApplication == null)
                {
                    sMyApplication = new MyApplication();
                }
            }
        }
        return sMyApplication;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        sMyApplication=this;
        Fresco.initialize(this);
    }
}
