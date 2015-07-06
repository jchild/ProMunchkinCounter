package com.example.jchild.promunchkincounter;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Rules extends ActionBarActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.RuleList);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rules, menu);
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

    public void onBackPressed(){
        finish();
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Conflicts Between Cards and Rules");
        listDataHeader.add("Card Management");
        listDataHeader.add("Character Creations");
        listDataHeader.add("Starting and Finishing the Game");
        listDataHeader.add("When You May Take Actions");

        // Adding child data
        List<String> rule1 = new ArrayList<String>();
        rule1.add("\tThe Rulesheet gives the general rules. Many cards add special rules, so in most cases when the rulesheet disagrees with a car, follow the card. However, ignore any card effect that might seem to contradict one of the rules listed below unless the card explicitly says it supersedes that rule!" +
                "\n \n 1. Nothing can reduce a player below Level 1, altough card effects might reduce a player's or a monster's combat strength below 1. \n\n 2. You go up a level after combat only if you kill a monster. \n 3. You cannot collect rewards for defeating a monster(e.g., Treasure, levels) in the middle of a combat. You must finish the fight before gaining any rewards." +
                "\n \n 4. You must kill a monster to reach Level 10.\n\n\t Any other disputes should be settled by loud arguments, with the owner of the game having the last word.");


        List<String> rule2 = new ArrayList<String>();
        rule2.add("\tKeep separate face-up discard piles for the two decks. You may not look through the discards unless you play a card that allows you to!" +
                "\n\n\tWhen a deck runs out, reshuffle its discards. If a deck runs out and there are no discards, nobody can draw any of that kind of card!" +
                "\n\n\tIn Play: These are the cards on the table in front of you, showing your Race and Class (if any) and the Items you are carrying. Continuing Courses and some other cards also stay on the table after you play them. Cards in play are public information and must be visible to the other players." +
                "\n\n\tYour Hand: Cards in your hand ar not in play. They don't help you, but they can't be taken away except by cards that specifically affect \"your hand\". At the end of your turn, you may have no more than five cards in your hand." +
                "\n\n\tCards in play must not be returned to your hand - they must be discarded or traded if you want to get rid of them.");

        List<String> rule3 = new ArrayList<String>();
        rule3.add("\tEveryone Starts as a Level 1 human with no class. (Heh, heh.) Munchkin characters are exclusively male or female, and your character's sex is he same as your own unless you declare otherwise." +
                "\n\n\tLook at your initial eight cards. if you have any Race or Class cards, you may (if you like) play one of each type by placing it in front of you. If you have any usable Items, you may play them by placing them in front of you. If you have any doubt about whether you should play a card, you could read below, or you could just charge ahead and do it.");

        listDataChild.put(listDataHeader.get(0), rule1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), rule2);
        listDataChild.put(listDataHeader.get(2), rule3);
    }
}
