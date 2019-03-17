package com.example.dpsassistant;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    Datahelp dbhelp;
    ArrayList<String> listItem;
    ArrayAdapter arrayAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        dbhelp = new Datahelp(this);
        listItem = new ArrayList<>();

        listView = findViewById(R.id.listView);
        viewData();

    }


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



    }

