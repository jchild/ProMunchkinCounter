package com.example.jchild.promunchkincounter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jonathan on 18/6/2015.
 */
public class player implements Parcelable{
    private int lvl,equip;
    private String name;

    public player(){
        lvl = 0;
        equip = 0;
        name = "error";
    }
    private player(Parcel in){
        lvl = Integer.parseInt(in.readString());
        equip = Integer.parseInt(in.readString());
        name = in.readString();
    }
    public void writeToParcel(Parcel out, int flags) {
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

    public void setName(String name){
        this.name = name;
    }

    public void increaselvl(){
        lvl++;
    }
    public void decreaselvl(){
        lvl--;
    }

    public void increaseEquip(){
        equip++;
    }
    public void decreaseEquip(){
        equip--;
    }

    public String getStr(){
        return String.valueOf(lvl + equip);
    }

    public String getlvl(){
        return String.valueOf(lvl);
    }
    public String getEquip(){
        return String.valueOf(equip);
    }
    public String getName(){
        return name;
    }

}
