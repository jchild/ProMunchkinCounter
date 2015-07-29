package com.childstudios.promunchkincounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class Battle extends ActionBarActivity {

    private ArrayList<player> players;
    private DatabaseHandler db;
    private player thisPlayer;
    private player monster;
    private boolean setMon;
    private player help;
    private int win, partySt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.childstudios.promunchkincounter.R.layout.activity_battle);

        Bundle b = getIntent().getExtras();
        thisPlayer = b.getParcelable("thisPlayer");
        db = new DatabaseHandler(this);
        thisPlayer = db.getPlayer(Integer.parseInt(thisPlayer.getID()));
        removeThisPlayer();

        Spinner spinner = (Spinner) findViewById(com.childstudios.promunchkincounter.R.id.playersList);
        spinner.setAdapter(new SpinnerAdapter(this, players));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                help = players.get(position);
                updateStats();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        help = new player();
        monster = new player();
        monsterLevelPop();
        setMon = false;

        updateStats();
    }


    public void monsterLevelPop(){
        if(setMon == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Monster");
            builder.setMessage("Please Input the Level of the Monster");
            View layout = View.inflate(this, com.childstudios.promunchkincounter.R.layout.dialog_monster_lvl, null);
            final EditText savedText = ((EditText) layout.findViewById(com.childstudios.promunchkincounter.R.id.monsterlvl));
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String lvl = savedText.getText().toString().trim();
                    if (!lvl.isEmpty()) {
                        monster.setLvl(lvl);
                        setMon = true;
                        updateStats();
                    }
                }
            });
            builder.setView(layout);
            AlertDialog dialog = builder.create();

            dialog.show();
        }
    }
    public void removeThisPlayer(){
        db.deletePlayer(thisPlayer);
        players = db.getAllPlayers();
        player temp = new player();
        temp.setLvl("-1");
        temp.increaseEquip();
        players.add(0,temp);
    }

    public void endFight(View view){
        if( win <= 0){
            if(win == 0 && thisPlayer.isWarrior() || win == 0 && help.isWarrior()){
                if(help.isElf()){
                    thisPlayer.zeroBonus();
                    thisPlayer.increaselvl();
                    db.addPlayer(thisPlayer);
                    help.increaselvl();
                    db.updatePlayer(help);
                    Intent i = new Intent(this, playerStats.class);
                    i.putExtra("thisPlayer", thisPlayer);
                    startActivity(i);
                    finish();
                }else {
                    thisPlayer.zeroBonus();
                    thisPlayer.increaselvl();
                    db.addPlayer(thisPlayer);
                    Intent i = new Intent(this, playerStats.class);
                    i.putExtra("thisPlayer", thisPlayer);
                    startActivity(i);
                    finish();
                }
            }else{
                thisPlayer.zeroBonus();
                db.addPlayer(thisPlayer);
                Intent i = new Intent(this, playerStats.class);
                i.putExtra("thisPlayer", thisPlayer);
                startActivity(i);
                finish();
            }
        }
        else{
            if(help.isElf()){
                thisPlayer.zeroBonus();
                thisPlayer.increaselvl();
                db.addPlayer(thisPlayer);
                help.increaselvl();
                db.updatePlayer(help);
                Intent i = new Intent(this, playerStats.class);
                i.putExtra("thisPlayer", thisPlayer);
                startActivity(i);
                finish();
            }else {
                thisPlayer.zeroBonus();
                thisPlayer.increaselvl();
                db.addPlayer(thisPlayer);
                Intent i = new Intent(this, playerStats.class);
                i.putExtra("thisPlayer", thisPlayer);
                startActivity(i);
                finish();
            }
        }
    }

    public void updateStats(){
        TextView name = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.p_name);
        TextView mon = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.monster);
        TextView monBonus = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.monsterEquip);
        TextView playBonus = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.playerBonus);
        TextView winning = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.winning);
        TextView party = (TextView) findViewById(com.childstudios.promunchkincounter.R.id.partyStr);

        name.setText(thisPlayer.getName()+"'s Strength: "+ thisPlayer.getStr());
        mon.setText("Monster's Strength: "+ monster.getStr());
        monBonus.setText(monster.getEquip());
        playBonus.setText(thisPlayer.getBonus());


        if(isWinning()){
            if(thisPlayer.isWarrior()||help.isWarrior())
                winning.setText("Winning by: "+ (win + 1 ));
            else
                winning.setText("Winning by: " + win);
        }
        else{
            if(thisPlayer.isWarrior()||help.isWarrior())
                winning.setText("Losing by: "+ (win * (-1)) );
            else
                winning.setText("Losing by: "+ ((win * (-1)) + 1 ) );

        }
        party.setText("Party Strength: "+String.valueOf(partySt));
    }
    public void increaseMon(View view){
        monster.increaseEquip();
        updateStats();
    }
    public void decreaseMon(View view) {
            monster.decreaseEquip();
            updateStats();
    }
    public void increasePlayEquip(View view){
        thisPlayer.increaseBonus();
        updateStats();
    }
    public void decreasePlayEquip(View view){
            thisPlayer.decreaseBonus();
            updateStats();

    }

    public boolean isWinning(){
        int monStr = Integer.parseInt(monster.getStr());
        int playStr = Integer.parseInt(thisPlayer.getStr());
        int helpStr = Integer.parseInt(help.getStr());
        partySt = (playStr+helpStr);
        win = partySt - monStr;

        if(win <= 0 && !thisPlayer.isWarrior() || win <= 0 && !help.isWarrior()){
            return false;
        }
        else if(win < 0 && thisPlayer.isWarrior() || win < 0 && help.isWarrior()){
            return false;
        }
        else{
            return true;
        }

    }

    @Override
    public void onBackPressed() {
        thisPlayer.zeroBonus();
        db.addPlayer(thisPlayer);
        Intent i = new Intent(this, playerStats.class);
        i.putExtra("thisPlayer", thisPlayer);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
