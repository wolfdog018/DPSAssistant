package com.example.dpsassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Remainder extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Datahelp dbhelp;

    RelativeLayout AddView;
    LinearLayout midle;

    ImageView imageAdd, imageClose;
    Button ViewBut, addBut;
    TextView eid, ename ,editTime;
    ListView listView;

    //Button button, viewBut;

    ArrayList<String> listItem;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        dbhelp = new Datahelp(this);

   /////////////////// TEXT EDIT /////////////////////////////////

        eid = findViewById(R.id.edit_id);
        ename = findViewById(R.id.edit_sname);

   ////////////////////// ADDING DATA AND VIEW ///////////////////////////////

        addBut = findViewById(R.id.addBut);
        setButton();
        ViewBut = findViewById(R.id.ViewBut);
        viewButton();

  ////////////////////////// LIST VIEW /////////////////////////////////

        listItem = new ArrayList<>();
        listView = findViewById(R.id.listView);
        viewData();

  ///////////////////////////// FOR RelativeLayout VIEW /////////////////////////////////////

        AddView = findViewById(R.id.AddView);

   ////////////////////////// FOR LinearLayout VIEW /////////////////////////////////

        midle = findViewById(R.id.midle);

   /////////////////////// TAB IMG CLOSE AND OPEN ///////////////////////////////////

        imageAdd = findViewById(R.id.imageAdd);
        imageAdd();
        imageClose = findViewById(R.id.imageClose);
        imageClose();

    ///////////////////// TIME EDIT ///////////////////////////////////////

        editTime = findViewById(R.id.edit_time);
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker = new Timepicker();
                timepicker.show(getSupportFragmentManager(), "timepicker");
            }
        });



        // viewButton1();
       // dbhelp = new Datahelp(this);
     //   viewData = findViewById(R.id.viewdata);

/*
       Button buttonViewpage = (Button)findViewById(R.id.viewdata2);
        buttonViewpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewpage();
            }
        });
*/


    }

    ////////////////////// EDIT TEXT OF EDIT TIME ////////////////////////////////////
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        editTime.setText( + hourOfDay + " : " + minute);
    }

    //////////////////////////   setButton  ////////////////////////////////
    public void setButton(){
        addBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinser = dbhelp.insert(eid.getText().toString(), ename.getText().toString(), editTime.getText().toString());

                Calendar calendar = Calendar.getInstance();

                setAlarm(calendar.getTimeInMillis());

            }
        });
    }

    //////////////////////////   viewButton  ////////////////////////////////
    public void viewButton(){
        ViewBut.setOnClickListener(new View.OnClickListener() {
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

    //////////////////////////   viewData  ////////////////////////////////
    private void viewData() {
        Cursor res = dbhelp.getView();

        if(res.getCount() == 0) {
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else {
            while (res.moveToNext()){
                listItem.add(res.getString(1));

            }

            arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            listView.setAdapter(arrayAdapter);
        }
    }

    //////////////////////////   imageAdd  ////////////////////////////////
    public void imageAdd(){
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddView.setVisibility(View.VISIBLE);
                midle.setVisibility(View.INVISIBLE);
            }
        });}

    //////////////////////////   imageClose  ////////////////////////////////
    public void imageClose(){
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddView.setVisibility(View.INVISIBLE);
                midle.setVisibility(View.VISIBLE);
            }
        });}

    /////////////////////////   setAlarm  /////////////////////////////////////

    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MyAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.setRepeating(AlarmManager.RTC, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(this, "Alarm is Set", Toast.LENGTH_SHORT).show();
    }






/*
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
                   // buffer.append("Time : " +res.getString(2)+"\n\n\n\n");

                }
                show("Data",buffer.toString());

            }
        });

    }
*/


/*
    public void openAddBut(){
        Intent intent = new Intent(this, AddingData.class);
        startActivity(intent);
    }

    public void openViewpage(){
        Intent intent = new Intent(this, ViewData.class);
        startActivity(intent);
    }
*/



    public void show(String title, String mssg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(mssg);
        builder.show();
    }


}
