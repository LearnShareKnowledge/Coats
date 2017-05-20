package com.learnshare.coats.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.learnshare.coats.R;

public class SplashActivity extends Activity {

    private Handler mHandler = new Handler(); ;

    private Runnable mRunnable ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        mRunnable = new Runnable() {
            @Override
            public void run()
            {
                finishThisAndLaunchActivity();
            }
        };

    }

    private void finishThisAndLaunchActivity()
    {
        finish();
        startActivity(new Intent(this,CheckInOutActivity.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mHandler!=null)
        {
            if(mRunnable!=null)
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable,3000);
    }
}
