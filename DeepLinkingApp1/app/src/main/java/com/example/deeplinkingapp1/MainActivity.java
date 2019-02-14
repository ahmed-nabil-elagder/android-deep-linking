package com.example.deeplinkingapp1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import helper.Constants;

public class MainActivity extends Activity {

    private EditText parameter1EditText;
    private EditText parameter2EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parameter1EditText = findViewById(R.id.parameter1EditText);
        parameter2EditText = findViewById(R.id.parameter2EditText);
    }

    public void openApplication2PassingValues(View v) {

        // -------------------------------------------------
        // Deep Linking - OUT-COMING
        // open/start other Activity/App
        String action = Constants.DEEP_LINKING_DESTINATION_ACTION;
        Intent sendIntent = new Intent(action);

        // set flags
        sendIntent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |		// make another app open in external new task (not embedded internal)
                Intent.FLAG_ACTIVITY_CLEAR_TASK		// force onCreate() in RECEIVER each time
        );

        // set data map (key / value) of parameters
        String parameter1 = parameter1EditText.getText().toString();
        String parameter2 = parameter2EditText.getText().toString();
        sendIntent.putExtra(Constants.PARAMETER1_NAME, parameter1);
        sendIntent.putExtra(Constants.PARAMETER2_NAME, parameter2);

        // set type of data
        sendIntent.setType(Constants.DEEP_LINKING_DATA_TYPE);

        // try to open/start other Activity/App
        try {

            startActivity(sendIntent);

        } catch (Exception e){
            // DESTINATION Application: NOT installed
            // DESTINATION Application: can NOT handle passed parameter

            // -----------------------------------
            // show corresponding info msg into AlertDialog
            String msg = this.getResources().getText(R.string.CanNotDestinationApplicationText).toString();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage(msg);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    this.getText(R.string.Okword),

                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            // -----------------------------------
        }
        // -------------------------------------------------

    }
}
