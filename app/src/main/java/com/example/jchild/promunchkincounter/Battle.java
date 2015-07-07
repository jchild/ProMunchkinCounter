package com.example.jchild.promunchkincounter;

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
        setContentView(R.layout.activity_battle);

        Bundle b = getIntent().getExtras();
        thisPlayer = b.getParcelable("thisPlayer");

        db = new DatabaseHandler(this);
        removeThisPlayer();

        Spinner spinner = (Spinner) findViewById(R.id.playersList);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_battle, menu);
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

    public void monsterLevelPop(){
        if(setMon == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Monster");
            builder.setMessage("Please Input the Level of the Monster");
            View layout = View.inflate(this, R.layout.dialog_monster_lvl, null);
            final EditText savedText = ((EditText) layout.findViewById(R.id.monsterlvl));
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
        TextView name = (TextView) findViewById(R.id.p_name);
        TextView mon = (TextView) findViewById(R.id.monster);
        TextView monBonus = (TextView) findViewById(R.id.monsterEquip);
        TextView playBonus = (TextView) findViewById(R.id.playerBonus);
        TextView winning = (TextView) findViewById(R.id.winning);
        TextView party = (TextView) findViewById(R.id.partyStr);

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
        if (Integer.parseInt(monster.getEquip()) != 0) {
            monster.decreaseEquip();
            updateStats();
        }
    }
    public void increasePlayEquip(View view){
        thisPlayer.increaseBonus();
        updateStats();
    }
    public void decreasePlayEquip(View view){
        if(Integer.parseInt(thisPlayer.getBonus())!= 0){
            thisPlayer.decreaseBonus();
            updateStats();
        }

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
        Intent i = new Intent(this, playerStats.class);
        i.putExtra("thisPlayer", thisPlayer);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
