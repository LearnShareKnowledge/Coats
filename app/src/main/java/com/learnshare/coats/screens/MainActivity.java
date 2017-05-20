package com.learnshare.coats.screens;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
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

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkAndValidateFields();
        }
    };

    private void checkAndValidateFields()
    {
        String s1 = etEmail.getText().toString();
        String s2 = etName.getText().toString();
        String s3 = etPhone.getText().toString();

        if(s1.equals("")|| s2.equals("") || s3.equals("")){
            btnSubmit.setEnabled(false);
        } else {
            btnSubmit.setEnabled(true);
        }

    }

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

        etPhone.addTextChangedListener(mTextWatcher);

        etEmail = (EditText) findViewById(R.id.etEmail);

        etEmail.addTextChangedListener(mTextWatcher);

        etName = (EditText) findViewById(R.id.etName);

        etName.addTextChangedListener(mTextWatcher);

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


    private void enableSubmitButton()
    {
        btnSubmit.setEnabled(true);
    }
}
