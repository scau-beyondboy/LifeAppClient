package scau.com.lifeappclient.utils;

import android.content.Context;

import scau.com.lifeappclient.MyApplication;

/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-10-06
 * Time: 19:40
 * 轻量级存储类
 */
public class ShareUtils
{
    public static final String FILE_NAME = "userinfo";
    public static final String USER_ID = "userId";
    public static final String TOKEN="token";
    public static final Context appContext=MyApplication.getInstance();
    public static void putUserId(Long userId)
    {
        appContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit().putLong(USER_ID, userId).apply();
    }
    public static Long getUserId()
    {
        return appContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getLong(USER_ID,0);
    }
    public static void putUserToken(String token){
        appContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).edit().putString(TOKEN, token).apply();
    }
    public static String getUserToken()
    {
        return appContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE).getString(TOKEN,null);
    }
}
