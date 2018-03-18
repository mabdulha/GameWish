package com.example.moham.gamewish.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moham.gamewish.R;
import com.example.moham.gamewish.utils.SQLiteHelper;

/**
 * Created by Mozeeb on 17/03/2018.
 */

public class UpdateWishList extends AppCompatActivity {

    private Button delButton, updButton;
    private EditText updName, updDev, updGenre;

    SQLiteHelper myDB;

    //private String selName,selDev,selGenre;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_wish_layout);
        delButton = (Button) findViewById(R.id.delButton);
        updButton = (Button) findViewById(R.id.updButton);
        updName = (EditText) findViewById(R.id.updName);
        updDev = (EditText) findViewById(R.id.updDev);
        updGenre = (EditText) findViewById(R.id.updGenre);
        myDB = new SQLiteHelper(this);

        //Intent receivedIntent = getIntent();

        /*selName = receivedIntent.getStringExtra("NAME");
        selDev = receivedIntent.getStringExtra("DEVELOPER");
        selGenre = receivedIntent.getStringExtra("GENRE");

        updName.setText(selName);
        updDev.setText(selDev);
        updGenre.setText(selGenre);*/
        updateDate();
        deleteData();
    }

    //Update the data that is already in the database
    public void updateDate() {
        updButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updName.getText().toString();
                String dev = updDev.getText().toString();
                String genre = updGenre.getText().toString();

                //If the data is entered into all the fields it will add data to the database otherwise it wouldn't
                if(!name.equals("") && !dev.equals("") && !genre.equals("")){
                    myDB.updateData(name, dev, genre);
                    Toast.makeText(UpdateWishList.this, "Data updated Successfully", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(UpdateWishList.this, "Please enter Data into all fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Delete the data from the database
    public void deleteData() {
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updName.getText().toString();
                int deletedRows = myDB.deleteData(name);
                updName.setText("");
                updDev.setText("");
                updGenre.setText("");
                //Delete will only happen if something exists in the database
                if(deletedRows > 0){
                    Toast.makeText(UpdateWishList.this, "Data has been deleted from Database :)", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(UpdateWishList.this, "Data has not been deleted from Database ;(", Toast.LENGTH_LONG).show();

                }
            }
        });
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
            // Linking the UpdateWishList activity via the menu to the ViewWishList activity
            case R.id.menuList:
                Intent intent = new Intent(UpdateWishList.this, ViewWishList.class);
                startActivity(intent);                break;

            // Linking the UpdateWishList activity via the menu to the Wish activity
            case R.id.menuAddWish:
                Intent intent1 = new Intent(UpdateWishList.this, Wish.class);
                startActivity(intent1);
        }
        // Forwards to the activity that is selected
        return super.onOptionsItemSelected(item);
    }
}