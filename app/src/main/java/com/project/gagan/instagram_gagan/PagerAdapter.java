package com.project.gagan.instagram_gagan;

import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Sift on 1/10/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    //Constructor for Pager Adapter
    public PagerAdapter(FragmentManager fm,int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs=mNumOfTabs;
    }
    //Switch between tabs of Main Activity
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                HomeTab tab1 = new HomeTab();
                return tab1;
            case 1:
                SearchTab tab2 = new SearchTab();
                return tab2;
            case 2:
                CameraTab tab3 = new CameraTab();
                return tab3;
            case 3:
                FollowTab tab4 = new FollowTab();
                return tab4;
            case 4:
                ProfileTab tab5 = new ProfileTab();
                return tab5;
            default:
                return null;
        }
    }
    //Return tab count
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}