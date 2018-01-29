package com.assignment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.toString();
    private EditText mNameEt, mAgeEt, mPhoneEt, mCityEt;
    private Button mSaveBtn, mShowBtn;
    private String name, phoneNumber, city, age;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        // init layout file(.xml)
        setContentView(R.layout.activity_main);
        context = this;
        // init all views
        inItViews();

        // Set onclick listener on save button
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchValues();  // fetch all values from ui
                validateAndSave(); // validate all values and if values are current the save in Share preference
            }
        });

        // Set onclick listener on show button
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prefRecords;
                prefRecords = "Name : " + SharedPrefManager.getPrefVal(context, Constants.PREF_NAME);
                prefRecords = prefRecords + System.getProperty("line.separator") + "Age : " + SharedPrefManager.getIntPrefVal(context, Constants.PREF_AGE);
                prefRecords = prefRecords + System.getProperty("line.separator") + "Phone : " + SharedPrefManager.getPrefVal(context, Constants.PREF_PHONE);
                prefRecords = prefRecords + System.getProperty("line.separator") + "City : " + SharedPrefManager.getPrefVal(context, Constants.PREF_CITY);
                // Show saved records using Toast
                Toast.makeText(MainActivity.this, prefRecords, Toast.LENGTH_LONG).show();
            }
        });

    }

    // init all views
    private void inItViews() {
        mNameEt = (EditText) findViewById(R.id.edit_text_name);
        mAgeEt = (EditText) findViewById(R.id.edit_text_age);
        mPhoneEt = (EditText) findViewById(R.id.edit_text_Phone);
        mCityEt = (EditText) findViewById(R.id.edit_text_city);
        mSaveBtn = (Button) findViewById(R.id.button_save);
        mShowBtn = (Button) findViewById(R.id.button_show);
    }

    // fetch all values
    private void fetchValues() {
        name = mNameEt.getText().toString();
        age = mAgeEt.getText().toString();
        phoneNumber = mPhoneEt.getText().toString();
        city = mCityEt.getText().toString();
    }

    // this method will check all fields are having values or not
    private void validateAndSave() {
        if (validate(name, mNameEt, "Please enter Name"))
            if (validate(age, mAgeEt, "Please Enter Age"))
                if (validate(phoneNumber, mPhoneEt, "Please Enter Phone Number"))
                    if (validate(city, mCityEt, "Please Enter City")) {
                        // set all vaues in shared preference
                        SharedPrefManager.setStringPrefVal(this, Constants.PREF_NAME, name);
                        SharedPrefManager.setIntPrefVal(this, Constants.PREF_AGE, Integer.valueOf(age));
                        SharedPrefManager.setStringPrefVal(this, Constants.PREF_PHONE, phoneNumber);
                        SharedPrefManager.setStringPrefVal(this, Constants.PREF_CITY, city);
                        Toast.makeText(context, "Records saved", Toast.LENGTH_SHORT).show();
                        refreshAllFields();
        }
    }

    // this method will check whether the fields are having values or not if not return false
    private boolean validate(String value, EditText view, String error) {
        if (value.equalsIgnoreCase("")) {
            view.setError(error);
            return false;
        } else {
            return true;
        }
    }
    // this method will refresh all fields, set all fields to empty
    private void refreshAllFields(){
        mNameEt.setText("");
        mAgeEt.setText("");
        mPhoneEt.setText("");
        mCityEt.setText("");
    }
}
