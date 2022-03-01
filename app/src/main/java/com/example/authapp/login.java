package com.example.authapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class login extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewpager;
    FloatingActionButton fb,google,twitter;
    float v=0;
    public static final String sharedDB="users";
    private EditText email,pass,cpass,name,loginPass,loginEmail;

    DataBaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tabLayout=findViewById(R.id.tab_layout);
        viewpager=findViewById(R.id.view_pager);
        fb=findViewById(R.id.fab_fa);
        google=findViewById(R.id.fab_google);
        twitter=findViewById(R.id.fab_tw);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter=new LoginAdapter(getSupportFragmentManager(), this,tabLayout.getTabCount());
        viewpager.setAdapter(adapter);

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewpager.setCurrentItem(tab.getPosition());
                        Log.i("TAG", "onTabSelected: " + tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        Log.i("TAG", "onTabUnselected: " + tab.getPosition());
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        Log.i("TAG", "onTabReselected: " + tab.getPosition());
                    }
                });
        fb.setTranslationY(300);
        google.setTranslationY(300);
        twitter.setTranslationY(300);
        tabLayout.setTranslationY(300);

        fb.setAlpha(v);
        google.setAlpha(v);
        twitter.setAlpha(v);
        tabLayout.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();

    }
}