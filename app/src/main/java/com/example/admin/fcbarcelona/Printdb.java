package com.example.admin.fcbarcelona;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

//This activity simply takes what has been entered into the "Add Data" button and prints it out
public class Printdb extends Activity {

    TextView mydata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printdb);

        String data = getIntent().getStringExtra("data");

        mydata = (TextView) findViewById(R.id.textViewData);

        mydata.setText(data);
    }
}
