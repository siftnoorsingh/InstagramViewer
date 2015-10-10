package com.project.gagan.instagram_gagan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Sift on 10/10/2015.
 */
public class CameraPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public CameraPagerAdapter(FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GalleryTab tab1 = new GalleryTab();
                return tab1;
            case 1:
                CapturedImageTab tab2 = new CapturedImageTab();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
