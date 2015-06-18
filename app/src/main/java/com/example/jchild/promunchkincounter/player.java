package com.example.jchild.promunchkincounter;

/**
 * Created by Jonathan on 18/6/2015.
 */
public class player {
    private int lvl,str,equip;
    private String name;

    public player(){
        lvl = 0;
        str = 0;
        equip = 0;
        name = "error";
    }

    public player(String Name){
        name = Name;
    }

    public void increaselvl(){
        lvl++;
    }

    public void increaseEquip(){
        equip++;
    }

    public int getStr(){
        return lvl + equip;
    }

    public int getlvl(){
        return lvl;
    }
    public int getEquip(){
        return equip;
    }
    public String getName(){
        return name;
    }

}
