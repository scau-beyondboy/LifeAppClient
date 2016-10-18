package scau.com.lifeappclient.activity;

import android.os.Bundle;

import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.anim.SlidePageAnimator;
import net.neevek.android.lib.paginize.annotation.InjectPageAnimator;

import scau.com.lifeappclient.page.ClubPage;
import scau.com.lifeappclient.page.ClubWebSitePage;
import scau.com.lifeappclient.page.LoginPage;

/**
 * Created by beyondboy on 2016/10/16.
 */
@InjectPageAnimator(SlidePageAnimator.class)
public class LoginActivity extends PageActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            //new LoginPage(this).show(false);
            new ClubPage(this).show(false);
//            new ClubWebSitePage(this).onShown("http://su.scau.edu.cn/").show(false);
        }
    }
}
