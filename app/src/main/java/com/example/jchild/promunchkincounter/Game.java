package com.example.jchild.promunchkincounter;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class Game extends ActionBarActivity{

    List<player> players;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        players = new ArrayList<player>();

        UpdateList();


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
            final EditText savedText = ((EditText)layout.findViewById(R.id.playerName));

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
        player newPlayer = new player(name);
        players.add(newPlayer);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,newPlayer.getName()+" added to the game.", duration);
        toast.show();
       // UpdateList();


    }

    public void UpdateList(){
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(new ListViewAdapter(this, players));
        list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Game.this, "click" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
