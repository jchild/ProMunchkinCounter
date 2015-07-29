package com.childstudios.promunchkincounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonathan on 7/7/2015.
 */
public class SpinnerAdapter extends BaseAdapter{
    private Context context;
    LayoutInflater inflater;
    ArrayList<player> players;

    public SpinnerAdapter(Context context, ArrayList<player> players){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.players = players;
    }
    @Override
    public int getCount(){
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(com.childstudios.promunchkincounter.R.layout.player_list_layout, null);
        }

        convertView = inflater.inflate(com.childstudios.promunchkincounter.R.layout.spinner_layout, null);
        TextView name = (TextView) convertView.findViewById(com.childstudios.promunchkincounter.R.id.name);
        TextView str = (TextView) convertView.findViewById(com.childstudios.promunchkincounter.R.id.str);

        name.setText(players.get(position).getName());
        if(Integer.parseInt(players.get(position).getlvl()) == -1)
            str.setText("Strength");
        else
            str.setText("Str: "+players.get(position).getStr());

        return convertView;
    }


}
