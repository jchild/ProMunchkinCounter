package com.childstudios.promunchkincounter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class playerStats extends ActionBarActivity {

    player thisPlayer;
    DatabaseHandler db;
    ArrayList<player> players;

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.childstudios.promunchkincounter.R.layout.activity_player_stats);
        db = new DatabaseHandler(this);
        Bundle b = getIntent().getExtras();
        thisPlayer = b.getParcelable("thisPlayer");
        updateStats();

        addWarCheckListener();
        addElfCheckListener();
        WinGame();

        players = db.getAllPlayers();
        players.remove(Integer.parseInt(thisPlayer.getID()));

        // Set up the drawer.
        drawerLayout = (DrawerLayout)findViewById(com.childstudios.promunchkincounter.R.id.drawer_layout);
        list = (ListView) findViewById(com.childstudios.promunchkincounter.R.id.navigation_drawer);
        list.setAdapter(new ListViewAdapter(this, players));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if user clicks on an item, will take object and pass it to next activity
                player getplayer = players.get(position);
                playerScreen(view, getplayer);
            }
        });
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.childstudios.promunchkincounter.R.menu.menu_player_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == com.childstudios.promunchkincounter.R.id.rules){
            Intent i = new Intent(this, Rules.class);
            startActivity(i);
            return true;
        }
        // Activate the navigation drawer toggle
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addElfCheckListener(){

        CheckBox Elf = (CheckBox) findViewById(com.childstudios.promunchkincounter.R.id.elf);
        Elf.setChecked(thisPlayer.isElf());
        Elf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    thisPlayer.setElf(true);
                    db.updatePlayer(thisPlayer);
                } else {
                    thisPlayer.setElf(false);
                    db.updatePlayer(thisPlayer);
                }
            }
        });

    }

    public void addWarCheckListener(){

        CheckBox War = (CheckBox) findViewById(com.childstudios.promunchkincounter.R.id.warrior);
        War.setChecked(thisPlayer.isWarrior());
        War.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    thisPlayer.setWarrior(true);
                    db.updatePlayer(thisPlayer);
                } else {
                    thisPlayer.setWarrior(false);
                    db.updatePlayer(thisPlayer);
                }
            }
        });
    }
    public void updateStats(){
        TextView lvl =(TextView) findViewById(com.childstudios.promunchkincounter.R.id.lvlNum);
        TextView equip = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.equipNum);
        TextView name = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.pName);
        TextView  str = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.pstr);

        lvl.setText( thisPlayer.getlvl());
        equip.setText(thisPlayer.getEquip());
        name.setText(thisPlayer.getName());
        str.setText(thisPlayer.getStr());
        players = db.getAllPlayers();
    }
    public void addLvl(View view){
        if(Integer.parseInt(thisPlayer.getlvl()) != 10) {
            thisPlayer.increaselvl();
            db.updatePlayer(thisPlayer);
        }
        if(Integer.parseInt(thisPlayer.getlvl())==10){
            WinGame();
        }
        updateStats();

    }
    public void addEquip(View view){
        thisPlayer.increaseEquip();
        db.updatePlayer(thisPlayer);
        updateStats();
    }
    public void minusLvl(View view){
        if(Integer.parseInt(thisPlayer.getlvl()) != 0){
            thisPlayer.decreaselvl();
            db.updatePlayer(thisPlayer);
        }

        updateStats();
    }
    public void minusEquip (View view){
        if(Integer.parseInt(thisPlayer.getEquip()) != 0){
            thisPlayer.decreaseEquip();
            db.updatePlayer(thisPlayer);
        }

        updateStats();
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, Game.class);
        startActivity(i);
        finish();
    }

    public void Battle(View view){
        Intent i = new Intent(this, Battle.class);
        i.putExtra("thisPlayer",thisPlayer);
        startActivity(i);
        finish();
    }
    public void test(View view){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "Is db.Elf? "+thisPlayer.isElf(), duration);
        toast.show();
    }
    @Override
    public void onResume(){
        super.onResume();
        db = new DatabaseHandler(this);
        thisPlayer = db.getPlayer(Integer.parseInt(thisPlayer.getID()));
        addWarCheckListener();
        addElfCheckListener();
        WinGame();
    }

    public void WinGame(){

        ArrayList<player> winners = new ArrayList<>();
        ArrayList<player> players = db.getAllPlayers();

        for(int i = 0; i< players.size(); i++){
            if(players.get(i).getlvl().matches("10")){
                winners.add(players.get(i));
            }
        }
        if(winners.size()>0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Congratulations!");
            if (winners.size() == 1) {

                builder.setMessage("Player " + winners.get(0).getName() + " has won!");
            } else if (winners.size() == 2) {
                builder.setMessage("Players " + winners.get(0).getName() + " and " + winners.get(1).getName() + " have won!");
            }
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.endGame();
                    finish();
                }
            });
            AlertDialog dialog = builder.create();

            dialog.show();

        }
    }

    public void playerScreen(View v, player getplayer){
        getplayer= db.getPlayer(Integer.parseInt(getplayer.getID()));
        Intent i = new Intent(this, playerStats.class);
        i.putExtra("thisPlayer", getplayer);
        startActivity(i);
        finish();
    }

    private void setupDrawer() {
        toggle = new ActionBarDrawerToggle(this, drawerLayout, com.childstudios.promunchkincounter.R.string.drawer_open, com.childstudios.promunchkincounter.R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getSupportActionBar().setTitle("Player List");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(getTitle().toString());
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }
}
