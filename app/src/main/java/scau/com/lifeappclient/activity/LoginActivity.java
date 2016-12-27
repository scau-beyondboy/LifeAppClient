package scau.com.lifeappclient.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.anim.SlidePageAnimator;
import net.neevek.android.lib.paginize.annotation.InjectPageAnimator;

import scau.com.lifeappclient.page.ClubPage;
import scau.com.lifeappclient.page.ClubWebSitePage;
import scau.com.lifeappclient.page.LoginPage;
import scau.com.lifeappclient.page.NoticePage;
import scau.com.lifeappclient.page.PersonInfoPage;
import scau.com.lifeappclient.page.ScauMapPage;
import scau.com.lifeappclient.page.ScauTelPage;

/**
 * Created by beyondboy on 2016/10/16.
 */
@InjectPageAnimator(SlidePageAnimator.class)
public class LoginActivity extends PageActivity {
    public static final int REQUEST_CALL_PHONE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
        if(savedInstanceState==null){
            //new LoginPage(this).show(false);
            new ClubPage(this).show(false);
//            new ClubWebSitePage(this).onShown("http://su.scau.edu.cn/").show(false);
//            new ScauMapPage(this).show(false);
//            new ScauTelPage(this).show(false);
          //  new NoticePage(this).show(false);
//            new PersonInfoPage(this).show(true);
        }
    }
    private void requestPermission(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(this.checkSelfPermission(Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
//                this.requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL_PHONE);
            if(this.shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){

            }else{
                this.requestPermissions(new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL_PHONE);
            }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
