package com.example.moham.gamewish.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.moham.gamewish.R;
import com.example.moham.gamewish.model.Game;

import java.util.ArrayList;

/**
 * Created by Mozeeb on 17/03/2018.
 */

public class WishAdaptor extends ArrayAdapter<Game> {

    private LayoutInflater myInflater;
    private ArrayList<Game> games;
    private int viewId;

    public WishAdaptor(Context context, int textViewId, ArrayList<Game> games) {
        super(context, textViewId,games);
        this.games = games;
        myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewId = textViewId;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        convertView = myInflater.inflate(viewId,null);
        Game game = games.get(position);
        //If the object is not empty
        if(game != null) {
            TextView name = (TextView) convertView.findViewById(R.id.textName);
            TextView dev = (TextView) convertView.findViewById(R.id.textDev);
            TextView genre = (TextView) convertView.findViewById(R.id.textGenre);
            //If Name input is not empty
            if(name != null){
                name.setText(game.getName());
            }
            //If Developer input is not empty
            if(dev != null){
                dev.setText(game.getDev());
            }
            //If Genre input is not empty
            if(genre != null){
                genre.setText(game.getGenre());
            }
        }
        return convertView;
    }

}