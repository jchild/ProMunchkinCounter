package com.childstudios.promunchkincounter;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        setContentView(com.childstudios.promunchkincounter.R.layout.activity_rules);

        // get the listview
        expListView = (ExpandableListView) findViewById(com.childstudios.promunchkincounter.R.id.RuleList);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.childstudios.promunchkincounter.R.menu.menu_rules, menu);
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
        listDataHeader.add("One-Shot Treasures");
        listDataHeader.add("Other Treasures");
        listDataHeader.add("Combat");
        listDataHeader.add("Monsters");
        listDataHeader.add("Monster Enhancers");
        listDataHeader.add("Fighting Multiple Monsters");
        listDataHeader.add("Asking For Help");
        listDataHeader.add("Interfering With Combat");
        listDataHeader.add("Rewards");
        listDataHeader.add("Running Away");
        listDataHeader.add("Death");
        listDataHeader.add("Curses");



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

        String rule10 = "\tA Treasure card that says \"Usable once only\" is often called a \"one-shot\" Treasure. Most of these are used during combat to strengthen the munchkins or the monsters, and may be played from your hand or from the table. Some have other effects, however, so read the card carefully! Discard these cards as soon as the combat is over or their effect is resolved.";

        String rule11 = "\tOther Treasure cards (like Go Up a Level cards) are not Items. Most of these cards say when they can be played, and whether they stay in play or are discarded. A couple of speciic examples:" +
                "\n\n\tGo Up a Level cards may be played on yourself or any other player at any time, even during combat. Discard them once they are played. Exception: You cannot play a Go Up a Level card to give a player the winning level!" +
                "\n\tHireling may be played at any time, on any turn. You cannot give a Hireling an Item to carry while you are in combat, however.";

        String rule12 = "\tTo fight a monster, compare its combat strength to yours. Combat strength is the total of Level plus all modiiers - positive or negative - given by Items and other cards. If the monster's combat strength is equal to yours, or greater, you lose the combat and must Run Away (see p. 5). If your combat strength totals more than the monster's - note that monsters win ties! - you kill it and go up a level (two levels for some big monsters). You'll also get the number of Treasures shown on its card." +
                "\n\n\tSometimes a card will let you get rid of the monster without killing it. This is still \"winning,\" but you don't get a level. Unless the ability says otherwise, you don't get the Treasures, either." +
                "\n\n\tSome monster cards have special powers that affect combat - a bonus against a Race or Class, for instance. Be sure to check these!" +
                "\n\n\tYou and the other players may play one-shot Treasures or use Class or Race abilities to help or harm you in your combat. Some Door cards may also be played into a combat, such as Monster Enhancers." +
                "\n\n\tRemember: while you are in combat, you cannot sell, equip, unequip, or trade Items, or play Treasures from your hand, unless these rules or the card says otherwise." +
                "\n\n\tIf you kill a monster (or monsters!), discard the monster(s) and any other cards played, and claim your rewards. But note: someone may play a hostile card on you, or use a special power, just as you think you have won. When you kill a monster, you must wait a reasonable time, defined as about 2.6 seconds, for anyone else to speak up. After that, you have really killed the monster, and you really get the level(s) and Treasures, though they can still whine and argue.";

        String rule14 = "\tCertain cards, called Monster Enhancers, raise or lower the combat strength of individual monsters. (Penalties to monsters are still considered Enhancers.) They also affect the number of Treasures the monsters are worth. Monster Enhancers may be played by any player during any combat." +
                "\n\n\tAll Enhancers on a single monster add together. If there are multiple monsters in a combat, the person who plays each Enhancer must choose which monster it applies to." +
                "\n\n\tException: Anything that enhances a monster also enhances its Mate . . . if Ancient, Enraged, and Mate are played on a single monster, in any order, you are facing an Ancient Enraged monster and its Ancient Enraged Mate. Good luck . . .";

        String rule13 = "\tIf drawn face-up, during the Kick Open The Door phase, they immediately attack the person who drew them." +
                "\n\n\tIf acquired any other way, they go into your hand and may be played during your own turn to Look For Trouble, or played to join another player's fight with the Wandering Monster card." +
                "\n\n\tEach Monster card is a single monster, even if the name on the card is plural.";


        String rule15 = "\tSome cards (notably Wandering Monster) allow your rivals to send other monsters to join the fight. You must defeat their combined combat strengths. Any special abilities, such as forcing you to ight with your Level only, apply to the entire fight. If you have the right cards, you can eliminate one monster from the combat and fight the other(s) normally, but you cannot choose to ight one and Run Away from the other(s). If you eliminate one monster, but then run from the other(s), you don't get any levels or Treasure!" +
                "\n\nUndead Monsters" +
                "\n\tSeveral monsters in this set are tagged Undead. You may play any Undead monster from your hand into combat to help any other Undead, without using a Wandering Monster card. If you have a card that can be used to make a monster Undead, you may play it with a non-Undead monster to use this rule.";

        String rule16 = "\tIf you cannot win a combat on your own, you may ask any other player to help you. If he refuses, you may ask another player, and so on, until they all turn you down or someone helps. Only one player can help you, adding his combat strength to yours. Anyone can play cards to affect your combat, however!" +
                "\n\n\tYou'll probably have to bribe someone to help. You may offer your helper any Item(s) you are currently carrying, or any number of the Treasure cards the monster has. If you offer him part of the monster's Treasure, you must agree whether he picks irst, or you pick irst, or whatever. You may also offer to play any cards from your hand that you legally could, such as Go Up a Level cards, on your helper." +
                "\n\n\tThe special abilities or vulnerabilities of the monster also apply to your helper, and vice versa. For instance, if a Warrior helps you, you will win if your combined total ties that of the monster, and he can Berserk and discard cards to add to his combat strength (but only once per combat, not once per monster). If you are facing the Wannabe Vampire and a Cleric helps you, he can chase it away automatically. But if you are facing the Drooling Slime and an Elf helps you, the monster's combat strength is increased by 4 (unless you, too, are an Elf and the monster's combat strength has already been increased)." +
                "\n\n\tIf someone successfully helps you kill the monster, discard it, draw Treasures (see Rewards), and follow any special instructions on the monster card. You level up for each slain monster. Your helper does not go up any levels . . . unless he's an Elf, in which case he gains one level for each monster slain. You draw the Treasure cards, even if it was your helper's special ability that defeated the monster, and distribute them according to the agreement you reached.";

        String rule17 = "\tYou can interfere with others' combats in several ways, including:" +
                "\n\n\tUse a one-shot card. You could help another player by using a one-shot to strengthen his side. Of course, you can \"accidentally\" strengthen the monster with it, instead . . ." +
                "\n\n\tPlay a Monster Enhancer. These cards (usually) make a monster stronger . . . and give it more Treasure. You can play these either during your own combats or during someone else's combat." +
                "\n\n\tAdd a monster from your hand to join the combat, either with a Wandering Monster card or by using the special Undead rule." +
                "\n\n\tBackstab a player in combat, if you're a Thief." +
                "\n\n\tCurse them, if you have a Curse card.";

        String rule18 = "\tWhen you kill a monster, you get one level per monster, unless the Monster card says something else, and you get all its Treasure! Each monster has a Treasure number on the bottom of its card. Draw that many Treasures, modiied by any Monster Enhancers played on it. Draw face-down if you killed the monster alone. Draw face-up, so the whole party can see what you got, if someone helped you." +
                "\n\n\tIf you defeat a monster by nonlethal means, you do not get a level and you may or may not get the Treasure, depending on the method." +
                "\n\n\tTreasure cards can be played as soon as you get them, even if you are the helper.";

        String rule19 = "\tIf nobody will help you...or if somebody tries to help, and your fellow party members interfere so the two of you still cannot win... you must Run Away. You don't get any levels or Treasure. You don't even get to Loot the Room. And you don't always escape unharmed..." +
                "\n\n\tRoll the die. You escape on a 5 or more. Some Class an Race abilities and some Treasures make it easier or harder to Run Away from all monsters. And some monsters give you a bonus or penalty to your roll for that monster only." +
                "\n\n\tIf you fail to Run Away from a monster, it does Bad Stuff to you, as described on its card. This may vary from losing an Item, to lonsing one or more levels, to Death." +
                "\n\n\tIf you are fleeing from multiple monsters, you roll separately to escape each one, in any order you choose, and Bad Stuff from each one that catches you as soon as it catches you." +
                "\n\n\tIf two players are cooperating and still can't defeat the monster(s), they must both Run Away. They roll separately, and each player chooses in what order to Run Away. The monster(s) CAN catch them both." +
                "\n\n\tOnce you have resolved all Run Away rolls, discard the monster(s).";

        String rule20 = "\tIf you die, you lose all your stuff. You keep your Class(es), Race(s), and Level (and any Curses that were affecting you when you died) - your new character will look just like your old one. If you have Half-Breed or Super Munchkin, keep those as well. Once you have died, you don't have to Run Away from any remaining monsters." +
                "\n\nLooting The Body: " +
                "\n\tLay out your hand beside the cards you had in play (making sure not to include the cards mentioned above). If you have an Item carried by a Hireling or attached to a Cheat! card, separate those cards. Starting with the player with the highest Level, everyone else chooses one card . . . in case of ties in Level, roll a die. If your corpse runs out of cards, tough. Once everyone gets one card, discard the rest. Looted cards go into players' hands. " +
                "\n\n\tDead characters cannot receive cards for any reason, not even Charity, and cannot level up or win the game. " +
                "\n\n\tWhen the next player begins his turn, your new character appears and can help others in combat with his Level and Class or Race abilities . . . but you have no cards, unless you receive Charity or gifts from other players." +
                "\n\n\tOn your next turn, start by drawing four face-down cards from each deck and playing any legal cards you want to, just as when you started the game. Then take your turn normally.";

        String rule21 = "\tIf drawn face-up during the Kick Open The Door phase, Curse cards apply to the person who drew them." +
                "\n\n\tIf acquired some other way, such as by Looting The Room, Curse cards go into your hand and may be played on any player at any time. ANY time, do you hear me? Reducing someone's abilities just as he thinks he has killed a monster is a lot of fun." +
                "\n\n\tIf a Curse can apply to more than one Item, the victim decides which Item is lost or Cursed." +
                "\n\n\tIf a Curse applies to something you don't have, ignore it. For instance, if you draw Lose Your Armor and you have no Armor, nothing happens; discard the card. (Some Curses have alternate effects, though, so read the card!)" +
                "\n\n\tThere will be times when it will help you to play a Curse or Monster on yourself, or to \"help\" another player in a way that costs him Treasure. This is very munchkinly. Do it." +
                "\n\n\tUsually, a Curse affects its victim immediately (if it can) and is then discarded. However, some Curses give a penalty later in the game or have a continuing effect. Keep these cards until you get rid of the Curse or the penalty takes effect. (Curse cards you keep as a reminder may not be discarded to power Class or Race abilities. Nice try!)" +
                "\n\n\tNote: If someone plays a \"your next combat\" Curse on you while you are in combat, it counts in that combat! The same is true for a \"your next turn\" Curse played during your turn.";

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
        listDataChild.put(listDataHeader.get(10), rule10);
        listDataChild.put(listDataHeader.get(11), rule11);
        listDataChild.put(listDataHeader.get(12), rule12);
        listDataChild.put(listDataHeader.get(13), rule13);
        listDataChild.put(listDataHeader.get(14), rule14);
        listDataChild.put(listDataHeader.get(15), rule15);
        listDataChild.put(listDataHeader.get(16), rule16);
        listDataChild.put(listDataHeader.get(17), rule17);
        listDataChild.put(listDataHeader.get(18), rule18);
        listDataChild.put(listDataHeader.get(19), rule19);
        listDataChild.put(listDataHeader.get(20), rule20);
        listDataChild.put(listDataHeader.get(21), rule21);

    }
}
