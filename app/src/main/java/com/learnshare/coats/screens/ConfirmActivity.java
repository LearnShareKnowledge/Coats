package com.learnshare.coats.screens;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.learnshare.coats.AppConstants;
import com.learnshare.coats.R;

public class ConfirmActivity extends Activity {

    private Button btnConfirm ;

    private ImageView ivJacket ;

    private Bitmap mBitmap ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm);

        Intent intent = getIntent();

        mBitmap = (Bitmap) intent.getParcelableExtra(AppConstants.IMAGE);

        ivJacket = (ImageView) findViewById(R.id.ivJacket);

        ivJacket.setImageBitmap(mBitmap);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // whats app
                // email

            }
        });
    }
}
