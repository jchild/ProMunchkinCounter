package com.example.jchild.promunchkincounter;

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
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class Game extends ActionBarActivity{

    ArrayList<player> players;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        db = new DatabaseHandler(this);

        //if there is no savedInstanceState, will create array for the player lists
        if (savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            players = new ArrayList<>();

            //sets up the list view
            ListView list = (ListView) findViewById(R.id.listView);
            list.setAdapter(new ListViewAdapter(this, players));

            //listener for the list view, checking if users selects item on the list view
            list.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //if user clicks on an item, will take object and pass it to next activity
                        player getplayer = players.get(position);
                        playerScreen(view, getplayer);
                    }
                });
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                    builder.setTitle("Are you sure?");
                    builder.setMessage("Do you want to delete player: " + players.get(position).getName());

                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.deletePlayer(players.get(position));
                            players.remove(players.get(position));
                            onResume();

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();

                    dialog.show();
                    return false;
                }
            });
        } else {
                players = savedInstanceState.getParcelableArrayList("key");
        }

    }

    //handles the calls to next activity
    //when user selects an object from the list view
    //will get the object and pass it to next activity
    public void playerScreen(View v, player getplayer){
        getplayer= db.getPlayer(Integer.parseInt(getplayer.getID()));
        Intent i = new Intent(this, playerStats.class);
        i.putExtra("thisPlayer", getplayer);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //check if user selects on menu; handles each item on the menu.
        if (id == R.id.add_user) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add New Player");
            builder.setMessage("Please Input the Players Name");
            View layout= View.inflate(this, R.layout.dialog_new_player,null);
            final EditText savedText = ((EditText)layout.findViewById(R.id.playerName));

            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(!savedText.getText().equals("")) {
                        String name = savedText.getText().toString().trim();
                        addNewPlayerData(name);
                    }
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
        if(id == R.id.rules){
            Intent i = new Intent(this, Rules.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //handles adding of new player to array and database
    private void addNewPlayerData(String name){
        player newPlayer = new player();
        newPlayer.setName(name);
        newPlayer.setID(players.size());
        players.add(newPlayer);
        db.addPlayer(newPlayer);

        //toast to let user know of adding of additional player was successful
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, newPlayer.getName() + " added to the game.", duration);
        toast.show();

    }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList("key", players);
        super.onSaveInstanceState(savedInstanceState);

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
    }


    //if activity is resumed will not build new arrays, but will repopulate array with
    //updated information from database.
    @Override
    protected void onResume(){
        super.onResume();
        players = db.getAllPlayers();
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(new ListViewAdapter(this, players));
        list.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                player getplayer = players.get(position);
                playerScreen(view, getplayer);
            }
        });
    }

    //handles if user presses the back button on the android phone.
    //will end the game and clear database.
    @Override
    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to end the game?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.endGame();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();

        dialog.show();
    }

}
