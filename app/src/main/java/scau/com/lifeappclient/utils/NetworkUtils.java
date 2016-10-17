package scau.com.lifeappclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import scau.com.lifeappclient.MyApplication;


/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-10-10
 * Time: 19:39
 * 检查手机是否有网络
 */
public class NetworkUtils
{
    private static final String TAG = "NetworkUtils";
    /**
     * 检查手机是否有网络
     * 若有则返回true，没有返回false
     */
    public static boolean isNetworkReachable()
    {
        ConnectivityManager manager=(ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current=manager.getActiveNetworkInfo();
        if(current==null)
        {
            Log.i(TAG, "没有网络");
            return false;
        }
        if(current.getState()==NetworkInfo.State.CONNECTED&&current.getType()==ConnectivityManager.TYPE_WIFI)
        {
            Log.i(TAG,"有wifi网络");
            return true;
        }
        else if(current.getType()==ConnectivityManager.TYPE_MOBILE&&current.getState()==NetworkInfo.State.CONNECTED)
        {
            Log.i(TAG,"有移动网络");
            return  true;
        }
        else if(current.getState()==NetworkInfo.State.CONNECTED)
        {
            Log.i(TAG,"其他网络");
            return true;
        }
        return false;
    }
}
