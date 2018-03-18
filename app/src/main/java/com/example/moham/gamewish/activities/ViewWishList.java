package com.example.moham.gamewish.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.moham.gamewish.R;
import com.example.moham.gamewish.model.Game;
import com.example.moham.gamewish.utils.SQLiteHelper;
import com.example.moham.gamewish.utils.WishAdaptor;

import java.util.ArrayList;

/**
 * Created by Mozeeb on 17/03/2018.
 */

public class ViewWishList extends AppCompatActivity {

    SQLiteHelper myDB;
    ArrayList<Game> gameList;
    ListView gamesListView;
    Game game;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_games_layout);

        myDB = new SQLiteHelper(this);
        gamesListView = (ListView) findViewById(R.id.wishListView);

        populateList();
    }

    private void populateList () {

        gameList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        //if database is empty a toast message will appear
        if (numRows == 0) {
            Toast.makeText(ViewWishList.this, "Database is empty", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                game = new Game(data.getString(1), data.getString(2), data.getString(3));
                gameList.add(game);
            }

            WishAdaptor adaptor = new WishAdaptor(this, R.layout.list_adaptor_view, gameList);
            gamesListView = (ListView) findViewById(R.id.wishListView);
            gamesListView.setAdapter(adaptor);

            gamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Finding the position of the attributes
                    String name = adapterView.getItemAtPosition(i).toString();
                    String dev = adapterView.getItemAtPosition(i).toString();
                    String genre = adapterView.getItemAtPosition(i).toString();

                    //Cursor data = myDB.getItemId(name,dev,genre);

                    //Log.i("Database id", myDB.getItemId(name, dev, genre).toString());

                    Intent updateScreen = new Intent(ViewWishList.this, UpdateWishList.class);
                    //Send attributes to next screen
                    updateScreen.putExtra("name", name);
                    updateScreen.putExtra("dev", dev);
                    updateScreen.putExtra("genre", genre);
                    startActivity(updateScreen);

                    //Toast.makeText(ViewGamesList.this, "No ID associated", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    //Create a menu to switch to activities
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            //Switch from ViewWishList activity to Wish activity
            case R.id.menuAddWish : startActivity (new Intent(this, Wish.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}