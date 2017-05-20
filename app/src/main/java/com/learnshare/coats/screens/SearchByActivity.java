package com.learnshare.coats.screens;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.learnshare.coats.R;

public class SearchByActivity extends Activity {

    private EditText etName , etPhone , etEmail ;

    private Button btnSearch ;

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

        if(!s1.equals("")|| !s2.equals("") || !s3.equals("")){
            btnSearch.setEnabled(true);
        } else {
            btnSearch.setEnabled(false);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_by);

        etEmail = (EditText) findViewById(R.id.etEmail);

        etEmail.addTextChangedListener(mTextWatcher);

        etName = (EditText) findViewById(R.id.etName);

        etName.addTextChangedListener(mTextWatcher);

        etPhone = (EditText) findViewById(R.id.etPhoneNumber);

        etPhone.addTextChangedListener(mTextWatcher);

        btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            // start search results


            }
        });


    }
}
