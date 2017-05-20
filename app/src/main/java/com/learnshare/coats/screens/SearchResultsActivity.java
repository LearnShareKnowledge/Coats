package com.learnshare.coats.screens;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.learnshare.coats.AppConstants;
import com.learnshare.coats.R;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class SearchResultsActivity extends Activity {

    private static final int REQUEST_SHARE = 102;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL = 2000 ;

    private Button btnConfirm ;

    private ImageView ivJacket ;

    private Bitmap mBitmap ;

    private String sName , sEmail , sPhone ;

    private TextView tvName , tvEmail , tvPhone ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm);

        Intent intent = getIntent();

        mBitmap = (Bitmap) intent.getParcelableExtra(AppConstants.IMAGE);

        sName = intent.getStringExtra(AppConstants.NAME);

        sEmail = intent.getStringExtra(AppConstants.EMAIL);

        sPhone = intent.getStringExtra(AppConstants.PHONE);

        tvName = (TextView) findViewById(R.id.tvName);

        tvName.setText(sName);

        tvEmail = (TextView) findViewById(R.id.tvEmail);

        tvEmail.setText(sEmail);

        tvPhone = (TextView) findViewById(R.id.tvPhone);

        tvPhone.setText(sPhone);

        ivJacket = (ImageView) findViewById(R.id.ivJacket);

        ivJacket.setImageBitmap(mBitmap);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check for permission

                int permissionCheck = ContextCompat.checkSelfPermission(SearchResultsActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if(permissionCheck == PERMISSION_GRANTED)
                {
                    btnConfirm.setVisibility(View.INVISIBLE);

                    String message = createMessage(sName);


                    Bitmap screenshot = getScreenshot(findViewById(R.id.rlMain));

                    Intent sendIntent = new Intent();

                    sendIntent.setAction(Intent.ACTION_SEND);

                    sendIntent.putExtra(Intent.EXTRA_TEXT, message);

                    sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Jacket receipt");

                    sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{sEmail});

                    sendIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, sPhone);

                    String pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), screenshot,"title", null);

                    Uri bmpUri = Uri.parse(pathofBmp);

                    sendIntent.putExtra(Intent.EXTRA_STREAM,bmpUri);

                    sendIntent.setType("image/*");

                    startActivityForResult(Intent.createChooser(sendIntent, ("Send receipt via ...")),REQUEST_SHARE);
                }
                else
                {
                    // return and ask for permission

                    ActivityCompat.requestPermissions(SearchResultsActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL);

                }


            }
        });
    }


    private String createMessage(String name)
    {
        String message = "Dear "+ name + ", \nYour jacket is safe with us.\nPlease remember to collect it.\nIf you forget please bring this attached receipt to reclaim. ";

        return message;
    }


    public Bitmap getScreenshot(View parentView) {
        Bitmap bitmap = Bitmap.createBitmap(parentView.getWidth(),
                parentView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        parentView.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SHARE)
        {
                startActivity(new Intent(this,SuccessActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    btnConfirm.setVisibility(View.INVISIBLE);

                    String message = createMessage(sName);


                    Bitmap screenshot = getScreenshot(findViewById(R.id.rlMain));

                    Intent sendIntent = new Intent();

                    sendIntent.setAction(Intent.ACTION_SEND);

                    sendIntent.putExtra(Intent.EXTRA_TEXT, message);

                    sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Jacket receipt");

                    sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{sEmail});

                    sendIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, sPhone);

                    String pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), screenshot,"title", null);

                    Uri bmpUri = Uri.parse(pathofBmp);

                    sendIntent.putExtra(Intent.EXTRA_STREAM,bmpUri);

                    sendIntent.setType("image/*");

                    startActivityForResult(Intent.createChooser(sendIntent, ("Send receipt via ...")),REQUEST_SHARE);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
