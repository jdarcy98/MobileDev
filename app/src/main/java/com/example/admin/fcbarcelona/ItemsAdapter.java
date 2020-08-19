package com.example.admin.fcbarcelona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//An adapter that adds each string into a respective text view
public class ItemsAdapter extends BaseAdapter {

    //Inflater is needed in order to make the textviews work with the list
    LayoutInflater mInflater;
    String[] players;
    String[] numbers;
    String[] countries;

    public ItemsAdapter(Context c, String[] p, String[] n, String[] f) {
        players = p;
        numbers = n;
        countries = f;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return players.length;
    }

    @Override
    public Object getItem(int i) {
        return players[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);
        TextView numTextView = (TextView) v.findViewById(R.id.numTextView);

        String name = players[i];
        String from = countries[i];
        String num = numbers[i];

        nameTextView.setText(name);
        descriptionTextView.setText(from);
        numTextView.setText(num);

        return v;
    }
}
