package com.example.authapp;



import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totaltabs;

    public LoginAdapter(FragmentManager fm,Context context,int totaltabs){
        super(fm);
        this.context=context;
        this.totaltabs=totaltabs;
    }

    @Override
    public int getCount(){
        return totaltabs;
    }

    public Fragment getItem(int Position){

        switch (Position){
            case 0:
                loginTabFragment logintab=new loginTabFragment();
                return logintab;

            case 1:
                SignupTabFragment signup=new SignupTabFragment();
                return signup;

            default:
                return null;
        }

    }
}
