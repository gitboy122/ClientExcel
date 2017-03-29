package com.example.sudhanshrana.clientexcel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager=(ViewPager)findViewById(R.id.viewpager);


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            String[] title=new String[]{"MON","TUE","WED","THU","FRI"};


            @Override
            public Fragment getItem(int position) {
                return Monday.newInstance(position);

            }

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });

        t1=(TabLayout)findViewById(R.id.tabl);
        t1.setupWithViewPager(viewPager);


    }
    public static String POSITION = "POSITION";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, t1.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }
}