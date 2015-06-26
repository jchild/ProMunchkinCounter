package com.example.jchild.promunchkincounter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class playerStats extends ActionBarActivity {

    player thisPlayer;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);
        Intent i = getIntent();
        Bundle b = getIntent().getExtras();
        thisPlayer = b.getParcelable("player");
        TextView lvl =(TextView) findViewById(R.id.lvlNum);
        TextView str = (TextView) findViewById(R.id.equipNum);
        TextView name = (TextView) findViewById(R.id.pName);

        lvl.setText( thisPlayer.getlvl());
        str.setText(thisPlayer.getEquip());
        name.setText(thisPlayer.getName());
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

    public void addLvl(View view){
        thisPlayer.increaselvl();
        db.updateContact(thisPlayer);

    }
    public void addEquip(View view){
        thisPlayer.increaseEquip();
        db.updateContact(thisPlayer);
    }
    public void minusLvl(View view){
        thisPlayer.decreaselvl();
        db.updateContact(thisPlayer);
    }
    public void minusEquip (View view){
        thisPlayer.decreaseEquip();
        db.updateContact(thisPlayer);
    }
}
