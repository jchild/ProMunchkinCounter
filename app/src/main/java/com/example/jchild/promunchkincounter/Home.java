package com.example.jchild.promunchkincounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseHandler db = new DatabaseHandler(this);

        if(db.getPlayersCount() == 0){
            setContentView(R.layout.activity_home);
        }

        else{
            setContentView(R.layout.activity_home_cont);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHandler db = new DatabaseHandler(this);

        if(db.getPlayersCount() == 0){
            setContentView(R.layout.activity_home);
        }

        else{
            setContentView(R.layout.activity_home_cont);
        }
    }

    public void newGame(View view){
        DatabaseHandler db = new DatabaseHandler(this);
        if(db.getPlayersCount()!=0){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Game in progress");
            builder.setMessage("By selecting new game the data for the current game in progress will be lost. Do you still want to continue?");

            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DatabaseHandler db = new DatabaseHandler(Home.this);
                    db.endGame();
                    Intent intent = new Intent(Home.this, Game.class);
                    startActivity(intent);

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

        }
        else {
            Intent intent = new Intent(this, Game.class);
            startActivity(intent);
        }
    }
    public void cont(View view){
        Intent intent = new Intent(this, Game.class);
        startActivity(intent);
    }

    public void rules(View view){
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    public void exit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {finish();}
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
