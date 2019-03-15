package com.example.dpsassistant;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Remainder extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Datahelp dbhelp;

    RelativeLayout addview;
    LinearLayout midle;
    ImageView imageAdd, imageClose;
    Button viewData, Viewpage;


    TextView eid, ename ,editTime;
    Button button, viewBut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);


        dbhelp = new Datahelp(this);
        eid = findViewById(R.id.edit_id);
        ename = findViewById(R.id.edit_sname);
        button = findViewById(R.id.button);
        viewBut = findViewById(R.id.viewbut);
        setButton();
        viewButton1();


        dbhelp = new Datahelp(this);
        viewData = findViewById(R.id.viewdata);
        addview = findViewById(R.id.addview);
        imageAdd = findViewById(R.id.imageAdd);
        imageClose = findViewById(R.id.imageClose);
        midle = findViewById(R.id.midle);
        editTime = findViewById(R.id.edit_time);


       /* Button buttonAdd = (Button)findViewById(R.id.adddata);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBut();
            }
        });
        */

       Button buttonViewpage = (Button)findViewById(R.id.viewdata2);
        buttonViewpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewpage();
            }
        });


        viewButton();
        imageAdd();
        imageClose();


        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker = new Timepicker();
                timepicker.show(getSupportFragmentManager(), "timepicker");
            }
        });


    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        editTime.setText( + hourOfDay + " : " + minute);
    }


    public void setButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinser = dbhelp.insert(eid.getText().toString(), ename.getText().toString(), editTime.getText().toString());

              //  if(isinser = true){ Toast.makeText(AddingData.this,"OK",Toast.LENGTH_LONG).show(); }
               // else {Toast.makeText(AddingData.this,"Not Ok",Toast.LENGTH_LONG).show();}
            }
        });
    }


    public void viewButton1(){
        viewBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbhelp.getView();
                if (res.getCount() == 0){
                    show("Error","Nothing");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID : " +res.getString(0)+"\n");
                    buffer.append("Name : " +res.getString(1)+"\n");
                    buffer.append("Time : " +res.getString(2)+"\n\n\n\n");

                }
                show("Data",buffer.toString());

            }
        });

    }






    public void openAddBut(){
        Intent intent = new Intent(this, AddingData.class);
        startActivity(intent);
    }

    public void openViewpage(){
        Intent intent = new Intent(this, ViewData.class);
        startActivity(intent);
    }

    public void imageAdd(){
        imageAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addview.setVisibility(View.VISIBLE);
            midle.setVisibility(View.INVISIBLE);
        }
    });}

    public void imageClose(){
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addview.setVisibility(View.INVISIBLE);
                midle.setVisibility(View.VISIBLE);
            }
        });}


    public void viewButton(){
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbhelp.getView();
                if (res.getCount() == 0){
                    show("Error","Nothing");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID : " +res.getString(0)+"\n");
                    buffer.append("Name : " +res.getString(1)+"\n\n\n\n");
                }
                show("Data",buffer.toString());

            }
        });}


    public void show(String title, String mssg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(mssg);
        builder.show();
    }


}
