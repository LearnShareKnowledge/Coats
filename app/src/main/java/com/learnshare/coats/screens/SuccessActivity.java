package com.learnshare.coats.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.learnshare.coats.R;

public class SuccessActivity extends Activity {

    private Button btnNextCustomer ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_success);

        btnNextCustomer = (Button) findViewById(R.id.btnNext);

        btnNextCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearStackAndLaunchActivity();
            }
        });


    }

    private void clearStackAndLaunchActivity() {

        Intent intent = new Intent(this,CheckInOutActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }
}
