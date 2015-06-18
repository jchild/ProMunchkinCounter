package com.example.jchild.promunchkincounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Game extends FragmentActivity {

    List<String> players;
    List<Integer> level;
    List<Integer> Strength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        players = new ArrayList<String>();
        level = new ArrayList<Integer>();
        Strength = new ArrayList<Integer>();
        setContentView(R.layout.activity_game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_user) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add New Player");
            builder.setMessage("Please Input the Players Name");
            View layout= View.inflate(this, R.layout.dialog_new_player,null);
            final EditText savedText = ((EditText)layout.findViewById(R.id.add_user));

            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO
                    String name = savedText.getText().toString().trim();
                    addNewPlayerData(name);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setView(layout);
            AlertDialog dialog = builder.create();

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addNewPlayerData(String name){

        players.add(name);
        level.add(0);
        Strength.add(0);

    }

}
