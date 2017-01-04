package scau.com.lifeappclient;

import android.content.Intent;
import android.os.Bundle;

import net.neevek.android.lib.paginize.PageActivity;

import scau.com.lifeappclient.activity.LoginActivity;
import scau.com.lifeappclient.page.TagPage;
import scau.com.lifeappclient.utils.ShareUtils;

public class MainActivity extends PageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        if(ShareUtils.getUserId()<=0){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }else {
            new TagPage(this).show(true);
        }
//        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });
//        new SimpleTabPage(this).show(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.getBooleanExtra("showTagPage",false)){
            new TagPage(this).show(true);
        }
    }
}
