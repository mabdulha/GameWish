package com.example.moham.gamewish.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moham.gamewish.R;
import com.example.moham.gamewish.model.Game;
import com.example.moham.gamewish.utils.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class Wish extends AppCompatActivity {

    EditText edtName, edtDev, edtGenre;
    Button btnAdd;
    SQLiteHelper myDB;
    public static List<Game> games    = new ArrayList<Game>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish);

        edtName = (EditText) findViewById(R.id.edtName);
        edtDev = (EditText) findViewById(R.id.edtDev);
        edtGenre = (EditText) findViewById(R.id.edtGenre);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        myDB = new SQLiteHelper(this);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String dev = edtDev.getText().toString();
                String genre = edtGenre.getText().toString();

                if(name.length() != 0 && dev.length() !=0 && genre.length() !=0){
                    AddData(name,dev,genre);
                    edtName.setText("");
                    edtDev.setText("");
                    edtGenre.setText("");
                }
                else {
                    Toast.makeText(Wish.this, "Please enter Data into all fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void AddData(String name, String dev, String genre){

        boolean insertData = myDB.addData(name,dev,genre);
        //If data is inserted with no null values it will will be added to the database
        if(insertData == true) {
            Toast.makeText(Wish.this, "Data added successfully :)", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(Wish.this, "Something went wrong :(", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            //Switch from Wish activity to ViewWishList activity
            case R.id.menuList:
                Intent intent = new Intent(Wish.this, ViewWishList.class);
                startActivity(intent);                break;
        }
        return super.onOptionsItemSelected(item);
    }
}