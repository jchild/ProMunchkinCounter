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
    HashMap<String, String> listDataChild;

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
        listDataChild = new HashMap<String, String>();

        // Rules Title
        listDataHeader.add("Conflicts Between Cards and Rules");
        listDataHeader.add("Card Management");
        listDataHeader.add("Character Creations");
        listDataHeader.add("Starting and Finishing the Game");
        listDataHeader.add("When You May Take Actions");
        listDataHeader.add("Turn Phases");
        listDataHeader.add("Character Stats");
        listDataHeader.add("Super Munchkin and Half-Breed");
        listDataHeader.add("Treasures");
        listDataHeader.add("Items");

        // Rules
        String rule0 = ("\tThe Rule sheet gives the general rules. Many cards add special rules, so in most cases when the rulesheet disagrees with a car, follow the card. However, ignore any card effect that might seem to contradict one of the rules listed below unless the card explicitly says it supersedes that rule!" +
                "\n \n 1. Nothing can reduce a player below Level 1, although card effects might reduce a player's or a monster's combat strength below 1. \n\n 2. You go up a level after combat only if you kill a monster. \n 3. You cannot collect rewards for defeating a monster(e.g., Treasure, levels) in the middle of a combat. You must finish the fight before gaining any rewards." +
                "\n \n 4. You must kill a monster to reach Level 10.\n\n\t Any other disputes should be settled by loud arguments, with the owner of the game having the last word.");


        String rule1 = ("\tKeep separate face-up discard piles for the two decks. You may not look through the discards unless you play a card that allows you to!" +
                "\n\n\tWhen a deck runs out, reshuffle its discards. If a deck runs out and there are no discards, nobody can draw any of that kind of card!" +
                "\n\n\tIn Play: These are the cards on the table in front of you, showing your Race and Class (if any) and the Items you are carrying. Continuing Courses and some other cards also stay on the table after you play them. Cards in play are public information and must be visible to the other players." +
                "\n\n\tYour Hand: Cards in your hand ar not in play. They don't help you, but they can't be taken away except by cards that specifically affect \"your hand\". At the end of your turn, you may have no more than five cards in your hand." +
                "\n\n\tCards in play must not be returned to your hand - they must be discarded or traded if you want to get rid of them.");

        String rule2 = ("\tEveryone Starts as a Level 1 human with no class. (Heh, heh.) Munchkin characters are exclusively male or female, and your character's sex is he same as your own unless you declare otherwise." +
                "\n\n\tLook at your initial eight cards. if you have any Race or Class cards, you may (if you like) play one of each type by placing it in front of you. If you have any usable Items, you may play them by placing them in front of you. If you have any doubt about whether you should play a card, you could read below, or you could just charge ahead and do it.");

        String rule3 = ("\tDecide who goes first by rolling the dice and arguing about the results and the meaning of this sentence and whether the fact that a word seems to be missing any effect." +
                "\n\n\tPlay proceeds in turns, each with several phases (see phases). When the first player finishes his turn, the player to his left takes a turn, and so on." +
                "\n\n\tThe first player to reach Level 10 wins... but you must reach Level 10 by killing a  monster, unless a card specifically  allows you to win another way.");

        String rule4 = ("\tYou may perform these actions at any time:" +
                "\n\t-Discard a Class or Race." +
                "\n\t-Play a Go Up a Level or Hireling." +
                "\n\t-Play a Curse." +
                "\n\n\tYou May perform these actions at any time, as long as you are not in combat:" +
                "\n\t-Trade an Item with another player (the other player may not be in combat, either)." +
                "\n\t-Change which Items you have equipped." +
                "\n\t-Play a card that you have just received (some cards may be played even during combat)." +
                "\n\n\tYou may perform these actions on your own turn:" +
                "\n\t-Play a new class or Race card (at any time)." +
                "\n\t-Sell Items for levels (except when you are in combat)." +
                "\n\t-Play an Item (most Items cannot be played during combat, but some one-shot Items can;[See One-Shot Treasures]).");

        String rule5="\tYour turn begins as soon as the previous player's turn ends. When your cards are arranged the way you want, go to phase 1." +
                "\n\n\t(1)Kick Open The Door: Draw one card from the Door deck and turn it face up." +
                "\n\tIf it's a monster, you must fight it. See Combat, p. 3. If the card is a curse -see Curses- it applies to you immediately (if it can) and is discarded (unless it has a persistent effect or you keep the card as a reminder of an upcoming effect)." +
                "\n\tIf you draw any other card, you may either put it in your hand or play it immediately." +
                "\n\n\t(2)Look For Trouble/Loot The Room: If you fought a monster in phase 1, skip this phase and go to phase 3." +
                "\n\tIf you did NOT draw a monster when you first opened the door, you have two choices: either LOOK FOR TROUBLE or LOOT THE ROOM." +
                "\n\n\tLook For Trouble: Play a monster from your hand and fight it, just as if you had found it when you kicked open the door. Don't play a monster you can't handle, unless you're sure you can count on getting help!" +
                "\n\n\tLoot The Room: Draw a second card from the Door deck, face down, and place it in your hand." +
                "\n\n\t(3)Charity: If you have more than five cards in your hand, you must play enough cards to get you to ive or below. If you cannot, or do not want to, you must give the excess cards to the player with the lowest Level. If players are tied for lowest, divide the cards as evenly as possible, but it's up to you who gets the bigger set(s) of leftovers. If YOU are the lowest or tied for lowest, just discard the excess." +
                "\n\n\tAs soon as you are finished with Charity, the next player's turn begins.";

        String rule6 = "\tEach character is basically a collection of weapons, armor, and magic items, with three stats: Level, Race, and Class. For instance, you might describe your character as \"an 8th-level elf wizard with Boots of Butt-Kicking, a Staff of Napalm, and the Kneepads of Allure.\"" +
                "\n\n\tLevel: This is a measure of how generally buff and studly you are. When the rules or cards refer to your Level, capitalized, they mean this number. You gain a level when you kill a monster, or when a card says that you do. You can also sell Items to buy levels (see Items)." +
                "\n\tYou lose a level when a card says you do. Your Level can never go below 1. However, your combat strength can be negative, if you get hit by a Curse or suffer some other kind of penalty." +
                "\n\n\tClass: Characters may be Warriors, Wizards, Thieves, or Clerics. If you have no Class card in front of you, you have no class. Yeah, I know, we did that one already." +
                "\n\tEach Class has special abilities, shown on the cards. You gain the abilities of a Class the moment you play its card in front of you, and lose them as soon as you discard that card. Some Class abilities are powered by discards. You may discard any card, in play or in your hand, to power a special ability." +
                "\n\tSee the Class cards for when abilities can be used. Note that a Thief cannot steal while he or the target is fighting -and as soon as a monster is revealed, the fight is on! You can discard a Class card at any time, even in combat: \"I don't wanna be a wizard anymore.\" When you discard a Class card, you become classless until you play another Class card." +
                "\n\tYou may not belong to more than one class at once unless you play the Super Munchkin card." +
                "\n\n\tRace: Characters may be Humans, Elves, Dwarves, or Halflings. If you have no Race card in front of you, you are human" +
                "\n\tHumans have no special abilities. The rules for Classes, above, also apply to Races." +
                "\n\tYou may not belong to more than one race at once unless you play the Half-Breed card.";

        String rule7 = "\tThese cards may be played whenever it is legal to play a Class or Race, as long as you have an appropriate card (Class for Super Munchkin, Race for Half-Breed) to attach it to. You cannot have more than one of the same Class or Race card in play at once." +
                "\n\n\tIf you play Super Munchkin with one Class, you get all the advantages of being that Class (the ability to equip Class-only Items, monsters with penalties against that Class suffer those penalties) and none of the disadvantages (you can equip Items forbidden to that Class, and monsters do not get bonuses because of your Class). If the Class has an ability that has a cost, however, you must still pay it - you aren't that Super! (All of the above is also true for Half-Breed, just for Races.)";

        String rule8 = "\tTreasure cards include permanent and \"one-shot\" cards. Any Treasure card may be played to the table as soon as you get it, or at any time on your own turn except during combat (unless the rules below or the card itself says otherwise).";

        String rule9 = "\tMost Treasures are Items. Items have a Gold Piece value. (\"No Value\" is equivalent to zero Gold Pieces, and these cards are also Items.)" +
                "\n\n\tAll Items you have in play are considered \"carried.\" Items that are actually giving you a bonus are \"equipped.\" You should indicate Items that are not equipped by turning the cards sideways. You may not alter the status of your Items during a combat or while running away." +
                "\n\n\tAnyone can carry any Item (except for extra Big items; see below), but you may equip only one Headgear, one suit of Armor, one pair of Footgear, and two \"1 Hand\" Items (or one \"2 Hands\" Item) . . . unless you have a card that lets you ignore these limits, such as Hireling or Cheat!, or unless one of the cards says otherwise. If you are carrying two Headgear cards, for instance, you can equip only one of them at a time." +
                "\n\n\tLikewise, some Items have restrictions: for instance, the Mace of Sharpness can only be wielded by a Cleric. Its bonus only counts for someone who is, at the moment, a Cleric." +
                "\n\n\tYou cannot discard Item cards \"just because.\" You may sell Items for a level, trade Items with other players, or give an Item to another player who wants it. You may discard Items to power certain Class and Race abilities. And a Curse or a monster's Bad Stuff (see p. 5) may force you to get rid of something!" +
                "\n\n\tBig Items: You may carry any number of Small items, but only one Big one. (Any item not marked Big is considered Small.) You may not discard one Big item to play another; you must sell the first Item, trade it, lose it to a Curse or Bad Stuff, or discard it to power a Class or Race ability." +
                "\n\n\tIf something lets you have more than one Big item (for instance, the Dwarf race) and you lose that ability, you must either correct the problem immediately or get rid of all but one Big item. If it's your turn and you're not in combat, you can sell the excess Big items (as long as you have at least 1,000 Gold Pieces of Items to sell). Otherwise, you must give them to the lowest-Level player(s) who can carry them! If any Big items are still left over, discard them." +
                "\n\n\tTrading: You may trade Items (but no other cards) with other players. You may only trade Items from the table - not from your hand. You may trade at any time except when you or your trading partner are in combat - in fact, the best time to trade is when it's not your turn. Any Item you receive in a trade must remain in play. You may also give Items away without a trade, to bribe other players -\"I'll give you my Flaming Armor if you won't help Bob fight that dragon!\" You may show your hand to others. Like we could stop you." +
                "\n\n\tSelling Items for Levels: At any point during your turn except during combat or Running Away, you may discard Items worth a total of at least 1,000 Gold Pieces and immediately go up one level. (\"No Value\" cards are the same as zero Gold Pieces.) If you discard (for instance) 1,100 Gold Pieces worth, you don't get change. But if you can manage 2,000 worth, you can go up two levels at once, and so on. You may sell Items from your hand as well as those you are carrying. You may not sell Items to go to Level 10.";

        listDataChild.put(listDataHeader.get(0), rule0); // Header, Child data
        listDataChild.put(listDataHeader.get(1), rule1);
        listDataChild.put(listDataHeader.get(2), rule2);
        listDataChild.put(listDataHeader.get(3), rule3);
        listDataChild.put(listDataHeader.get(4), rule4);
        listDataChild.put(listDataHeader.get(5), rule5);
        listDataChild.put(listDataHeader.get(6), rule6);
        listDataChild.put(listDataHeader.get(7), rule7);
        listDataChild.put(listDataHeader.get(8), rule8);
        listDataChild.put(listDataHeader.get(9), rule9);
    }
}
