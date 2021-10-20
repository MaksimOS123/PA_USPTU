package com.example.pa_usptu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NavigationLayout extends RelativeLayout {
    public NavigationLayout(Context context, RelativeLayout parent)
    {
        super(context);
        initView(context,parent);
    }

    public void initView(final Context context,RelativeLayout parent)
    {
        // надуваем любой xml файл разметки
        View view= LayoutInflater.from(context).inflate(R.layout.view_drawer_layout,parent,true);

    }
}