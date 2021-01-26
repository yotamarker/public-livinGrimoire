package com.yotamarker.lgkotlin1;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LangAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> myResult;

    public LangAdapter(Context context, ArrayList<String> myResult) {
        this.context = context;
        this.myResult = myResult;
    }

    @Override
    public int getCount() {
        return myResult.size();
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
        TextView txt = new TextView(context);
        txt.setText(myResult.get(position));
        txt.setTextColor(Color.WHITE);
        return txt;
    }
}
