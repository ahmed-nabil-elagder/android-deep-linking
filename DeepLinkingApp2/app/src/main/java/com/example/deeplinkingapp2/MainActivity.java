package com.example.deeplinkingapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import helper.Constants;

public class MainActivity extends Activity {

    private TextView parameter1ValueEditText;
    private TextView parameter2ValueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parameter1ValueEditText = findViewById(R.id.parameter1ValueEditText);
        parameter2ValueEditText = findViewById(R.id.parameter2ValueEditText);


        // -------------------------------------------------
        // Deep Linking - IN-COMING
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        // check if activity opened from deep linking call
        if (action.equals(Constants.DEEP_LINKING_DESTINATION_ACTION) &&
            type != null &&
            type.equals(Constants.DEEP_LINKING_DATA_TYPE)) {

            // receive parameters
            String parameter1 = intent.getStringExtra(Constants.PARAMETER1_NAME);
            String parameter2 = intent.getStringExtra(Constants.PARAMETER2_NAME);

            // use parameters
            parameter1ValueEditText.setText(parameter1);
            parameter2ValueEditText.setText(parameter2);

        }
        // -------------------------------------------------
    }
}
