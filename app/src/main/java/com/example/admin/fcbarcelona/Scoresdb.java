package com.example.admin.fcbarcelona;

import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Class to take in match details and add to a database created in Database Helper
public class Scoresdb extends Activity {

    DatabaseHelper myDb;
    EditText editHome, editAway, editScore, editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnviewUpdate;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoresdb);
        myDb = new DatabaseHelper(this);

        editHome = (EditText)findViewById(R.id.editText_home);
        editAway = (EditText)findViewById(R.id.editText_away);
        editScore = (EditText)findViewById(R.id.editText_score);
        editTextId =(EditText)findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);

        /*
        Each method being called to add data, view data on a new activity in a list,
        update a mistake they may have made, or delete setails for whichever match altogether
        */
        AddData();
        viewAll();
        UpdateData();
        DeleteData();



    }

    //Each button takes into account that the user may not have entered anything
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Scoresdb.this, "Data deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Scoresdb.this, "Data not deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(editTextId.getText().toString(), editHome.getText().toString(),
                                editAway.getText().toString(), editScore.getText().toString());
                        if(isUpdated == true)
                            Toast.makeText(Scoresdb.this, "Data updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Scoresdb.this, "Data not updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v){
                        boolean isInserted = myDb.insertData(editHome.getText().toString(),
                                editAway.getText().toString(),
                                editScore.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(Scoresdb.this, "Data inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Scoresdb.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()) {
                            buffer.append("Matchday :" +res.getString(0)+"\n");
                            buffer.append("Home Team :" +res.getString(1)+"\n");
                            buffer.append("Away Team :" +res.getString(2)+"\n");
                            buffer.append("Score :" +res.getString(3)+"\n\n");
                        }

                        //Show all data
                        Intent showData = new Intent(getApplicationContext(), Printdb.class);
                        showData.putExtra("data", buffer.toString());
                        startActivity(showData);
                    }
                }
        );
    }


    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

