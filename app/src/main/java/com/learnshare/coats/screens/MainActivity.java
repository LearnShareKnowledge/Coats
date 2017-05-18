package com.learnshare.coats.screens;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.learnshare.coats.AppConstants;
import com.learnshare.coats.R;

public class MainActivity extends Activity {

    private static final int REQUEST_IMAGE_CAPTURE = 101;

    private Button btnSubmit , btnTakePhoto;

    private EditText etPhone , etEmail , etName ;

    private String sPhone , sEmail , sName ;

    private Bitmap mBitmap ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sEmail = etEmail.getText().toString();

                sPhone = etPhone.getText().toString();

                sName = etName.getText().toString();

                launchActivity(sPhone , sEmail, sName);

            }
        });

        etPhone = (EditText) findViewById(R.id.etPhoneNumber);

        etEmail = (EditText) findViewById(R.id.etEmail);

        etName = (EditText) findViewById(R.id.etName);

        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                launchCamera();
            }
        });
    }

    private void launchCamera()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    private void launchActivity (String sPhone , String sEmail , String sName)
    {
        Intent intent = new Intent(this,ConfirmActivity.class);

        intent.putExtra(AppConstants.EMAIL,sEmail);

        intent.putExtra(AppConstants.NAME,sName);

        intent.putExtra(AppConstants.PHONE,sPhone);

        intent.putExtra(AppConstants.IMAGE , mBitmap);

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE)
        {
            if(resultCode == RESULT_OK)
            {
                Bundle extras = data.getExtras();

                mBitmap = (Bitmap) extras.get("data");

            }
        }
    }
}
