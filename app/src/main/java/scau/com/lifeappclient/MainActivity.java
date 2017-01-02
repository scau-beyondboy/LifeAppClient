package scau.com.lifeappclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import scau.com.lifeappclient.activity.LoginActivity;
import scau.com.lifeappclient.utils.ShareUtils;
import scau.com.lifeappclient.utils.SoftInputUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // if(ShareUtils.getUserId()<0){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
    //    }
//        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            }
//        });
    }
}
