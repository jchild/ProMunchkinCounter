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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jchild.promunchkincounter.DatabaseHandler;

import java.util.ArrayList;


public class playerStats extends ActionBarActivity {

    player thisPlayer;
    DatabaseHandler db;
    ArrayList<player> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);
        db = new DatabaseHandler(this);
        Bundle b = getIntent().getExtras();
        thisPlayer = b.getParcelable("thisPlayer");
        updateStats();

        addWarCheckListener();
        addElfCheckListener();
        WinGame();

        players = db.getAllPlayers();

        // Set up the drawer.
        ListView list = (ListView) findViewById(R.id.navigation_drawer);
        list.setAdapter(new ListViewAdapter(this, players));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(playerStats.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.rules){
            Intent i = new Intent(this, Rules.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addElfCheckListener(){

        CheckBox Elf = (CheckBox) findViewById(R.id.elf);
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

        CheckBox War = (CheckBox) findViewById(R.id.warrior);
        War.setChecked(thisPlayer.isWarrior());
        War.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()) {
                    thisPlayer.setWarrior(true);
                    db.updatePlayer(thisPlayer);
                }
                else{
                    thisPlayer.setWarrior(false);
                    db.updatePlayer(thisPlayer);
                }
            }
        });
    }
    public void updateStats(){
        TextView lvl =(TextView) findViewById(R.id.lvlNum);
        TextView equip = (TextView) findViewById(R.id.equipNum);
        TextView name = (TextView) findViewById(R.id.pName);
        TextView  str = (TextView) findViewById(R.id.pstr);

        lvl.setText( thisPlayer.getlvl());
        equip.setText(thisPlayer.getEquip());
        name.setText(thisPlayer.getName());
        str.setText(thisPlayer.getStr());
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
}
