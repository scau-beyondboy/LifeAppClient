package scau.com.lifeappclient;

import android.os.Bundle;

import net.neevek.android.lib.paginize.PageActivity;

import scau.com.lifeappclient.page.TagPage;

public class MainActivity extends PageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       // if(ShareUtils.getUserId()<0){
//            startActivity(new Intent(MainActivity.this,LoginActivity.class));
    //    }
//        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });
//        new SimpleTabPage(this).show(true);
        new TagPage(this).show(true);
    }
}
