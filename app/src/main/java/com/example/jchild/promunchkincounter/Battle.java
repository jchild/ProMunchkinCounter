package com.example.jchild.promunchkincounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class Battle extends ActionBarActivity {

    private ArrayList<player> players;
    private DatabaseHandler db;
    private player thisPlayer;
    private player monster;
    private boolean setMon;
    private player help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        db = new DatabaseHandler(this);
        players = db.getAllPlayers();
        Bundle b = getIntent().getExtras();
        thisPlayer = b.getParcelable("thisPlayer");
        help = new player();
        monster = new player();
        monsterLevelPop();
        removeThisPlayer();
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
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
                    monster.setLvl(lvl);
                    setMon = true;
                    updateStats();
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
        }
    }
    public void removeThisPlayer(){
        players.remove(thisPlayer);
    }

    public void endFight(View view){
        if( Integer.parseInt(monster.getStr()) >= Integer.parseInt(thisPlayer.getStr())){
            if(Integer.parseInt(monster.getStr()) == Integer.parseInt(thisPlayer.getStr())&& thisPlayer.isWarrior()){
                if(help.isElf()){
                    thisPlayer.zeroBonus();
                    thisPlayer.increaselvl();
                    db.updatePlayer(thisPlayer);
                    help.increaselvl();
                    db.updatePlayer(help);
                    Intent i = new Intent(this, playerStats.class);
                    i.putExtra("thisPlayer", thisPlayer);
                    startActivity(i);
                    finish();
                }else {
                    thisPlayer.zeroBonus();
                    thisPlayer.increaselvl();
                    db.updatePlayer(thisPlayer);
                    Intent i = new Intent(this, playerStats.class);
                    i.putExtra("thisPlayer", thisPlayer);
                    startActivity(i);
                    finish();
                }
            }else{
                finish();
            }
        }
        else{
            if(help.isElf()){
                thisPlayer.zeroBonus();
                thisPlayer.increaselvl();
                db.updatePlayer(thisPlayer);
                help.increaselvl();
                db.updatePlayer(help);
                Intent i = new Intent(this, playerStats.class);
                i.putExtra("thisPlayer", thisPlayer);
                startActivity(i);
                finish();
            }else {
                thisPlayer.zeroBonus();
                thisPlayer.increaselvl();
                db.updatePlayer(thisPlayer);
                Intent i = new Intent(this, playerStats.class);
                i.putExtra("thisPlayer", thisPlayer);
                startActivity(i);
                finish();
            }
        }
    }

    public void updateStats(){
        //TODO
        TextView name = (TextView) findViewById(R.id.p_name);
        TextView mon = (TextView) findViewById(R.id.monster);
        TextView monBonus = (TextView) findViewById(R.id.monsterEquip);
        TextView playBonus = (TextView) findViewById(R.id.playerBonus);

        name.setText(thisPlayer.getName()+"'s Strength: "+ thisPlayer.getStr());
        mon.setText("Monster's Strength: "+ monster.getStr());
        monBonus.setText(monster.getEquip());
        playBonus.setText(thisPlayer.getBonus());
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
