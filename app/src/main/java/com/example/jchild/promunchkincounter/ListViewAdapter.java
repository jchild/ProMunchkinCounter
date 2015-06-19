package com.example.jchild.promunchkincounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 18/6/2015.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<player> players;

    public ListViewAdapter(Context context, ArrayList<player> players) {
        this.context = context;
        this.players = players;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.player_list_layout, null);
        }

        TextView name = (TextView)convertView.findViewById(R.id.playerName);
        TextView lvl=(TextView)convertView.findViewById(R.id.playerlvl);
        TextView str = (TextView)convertView.findViewById(R.id.playerstr);

        name.setText(players.get(position).getName());
        lvl.setText(players.get(position).getlvl());
        str.setText(players.get(position).getStr());

        return convertView;
    }
}