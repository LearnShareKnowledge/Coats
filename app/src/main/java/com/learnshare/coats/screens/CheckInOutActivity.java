package com.learnshare.coats.screens;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.learnshare.coats.R;

public class CheckInOutActivity extends Activity {

    private Button btnCheckIn , btnCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_in_out);

        btnCheckIn = (Button) findViewById(R.id.btnCheckingIn);

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishThisAndLaunchMainActivity();
            }
        });

        btnCheckOut = (Button) findViewById(R.id.btnCheckingOut);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to implement
                finishThisAndLaunchSearchByActivity();
            }
        });
    }



    private void finishThisAndLaunchMainActivity()
    {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

    private void finishThisAndLaunchSearchByActivity()
    {
        finish();
        startActivity(new Intent(this,SearchByActivity.class));
    }

}
