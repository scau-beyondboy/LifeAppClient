package scau.com.lifeappclient.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import scau.com.lifeappclient.MyApplication;
import scau.com.lifeappclient.R;


/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-10-25
 * Time: 00:23
 */
public class ToaskUtils
{
    private static TextView mTip;
    private static Toast sToast;

    public static void displayToast(String warnning)
    {
        if(sToast==null)
        {
            sToast = Toast.makeText(MyApplication.getInstance(), warnning, Toast.LENGTH_SHORT);
            View view = View.inflate(MyApplication.getInstance(), R.layout.toast_style, null);
            mTip=(TextView)view.findViewById(R.id.tip);
            sToast.setView(view);
            sToast.setGravity(Gravity.CENTER, 0, DisplayUtil.dip2px(MyApplication.getInstance(),-50));
        }
        mTip.setText(warnning);
        sToast.show();
    }
    public static void showToast(String tip){
        Toast.makeText(MyApplication.getInstance(),tip,Toast.LENGTH_SHORT).show();
    }
}
