package com.example.dpsassistant;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Remainder extends AppCompatActivity {

    RelativeLayout addview;

    Datahelp dbhelp;
    Button viewData, Viewpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        dbhelp = new Datahelp(this);
        viewData = findViewById(R.id.viewdata);
        addview = findViewById(R.id.addview);

        Button buttonAdd = (Button)findViewById(R.id.adddata);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBut();
            }
        });

       Button buttonViewpage = (Button)findViewById(R.id.viewdata2);
        buttonViewpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewpage();
            }
        });


        viewButton();


    }

    public void addview(View view){
        addview.setVisibility(View.VISIBLE);
    }


    public void openAddBut(){
        Intent intent = new Intent(this, AddingData.class);
        startActivity(intent);
    }

    public void openViewpage(){
        Intent intent = new Intent(this, ViewData.class);
        startActivity(intent);
    }


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
        });

    }


    public void show(String title, String mssg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(mssg);
        builder.show();
    }

}
