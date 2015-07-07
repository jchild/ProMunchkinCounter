package com.example.jchild.promunchkincounter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jonathan on 18/6/2015.
 */
public class player implements Parcelable{
    private int lvl,equip, ID, bonus;
    private String name;
    private boolean elf,warrior;

    public player(){
        ID = 0;
        lvl = 0;
        equip = 0;
        bonus = 0;
        name = "Name";
        elf = false;
        warrior = false;
    }
    public player(int ID, String name, int lvl, int equip, boolean elf, boolean warrior){
        this.ID = ID;
        this.name = name;
        this.lvl = lvl;
        this.equip = equip;
        this.elf = elf;
        this.warrior = warrior;
    }
    private player(Parcel in){
        this.ID = Integer.parseInt(in.readString());
        this.lvl = Integer.parseInt(in.readString());
        this.equip = Integer.parseInt(in.readString());
        this.name = in.readString();
    }
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(String.valueOf(ID));
        out.writeString(String.valueOf(lvl));
        out.writeString(String.valueOf(equip));
        out.writeString(name);
    }
    public static final Parcelable.Creator<player> CREATOR = new Parcelable.Creator<player>() {
        public player createFromParcel(Parcel in) {
            return new player(in);
        }

        public player[] newArray(int size) {
            return new player[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void setName(String name){ this.name = name; }
    public void setID(int ID){this.ID = ID;}
    public void setLvl(String lvl){this.lvl = Integer.parseInt(lvl);}

    public void increaselvl(){lvl++;}
    public void decreaselvl(){lvl--;}

    public void increaseEquip(){equip++;}
    public void decreaseEquip(){equip--;}
    public void increaseBonus(){bonus++;}
    public void decreaseBonus(){bonus--;}

    public void setElf(boolean elf){this.elf = elf;}
    public void setWarrior(boolean warrior){this.warrior = warrior;}
    public Boolean isElf(){return elf;}
    public Boolean isWarrior(){return warrior;}

    public String getStr(){return String.valueOf(lvl + equip +bonus);}

    public String getID(){return String.valueOf(ID); }
    public String getlvl(){return String.valueOf(lvl);}
    public String getEquip(){return String.valueOf(equip);}
    public String getName(){
        return name;
    }
    public String getBonus(){return String.valueOf(bonus);}
    public void zeroBonus(){bonus = 0;}


}
