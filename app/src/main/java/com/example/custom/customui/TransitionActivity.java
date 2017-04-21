package com.example.custom.customui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by 29185 on 2017/4/14.
 */

public class TransitionActivity extends Activity {
    ImageView imageInit;
    HttpTransfer httpTransfer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        (imageInit = (ImageView) findViewById(R.id.imageLogo)).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLogin()) {
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    Intent it = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        }, 3000);
    }
}
