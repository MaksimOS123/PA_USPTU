package com.example.pa_usptu;

import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

public class ParentNavigationActivity extends AppCompatActivity {
    NavigationLayout navigationLayout;
    RelativeLayout right_drawer;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        String test = getResources().getResourceEntryName(layoutResID);
        Log.d("TEST", test);
        setupMenu();
    }

    public void setupMenu()
    {
        right_drawer=(RelativeLayout) findViewById(R.id.right_drawer);
        navigationLayout=new NavigationLayout(getApplicationContext(),right_drawer);

        right_drawer.addView(navigationLayout);
    }
}