package com.example.jchild.promunchkincounter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jchild.promunchkincounter.DatabaseHandler;
import java.util.ArrayList;


public class playerStats extends ActionBarActivity {

    player thisPlayer;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);
        db = new DatabaseHandler(this);
        Bundle b = getIntent().getExtras();
        thisPlayer = b.getParcelable("thisPlayer");
        updateStats();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            //win
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
        int num = db.getPlayersCount();
        Toast toast = Toast.makeText(context, num + " player(s)", duration);
        toast.show();
    }
}
