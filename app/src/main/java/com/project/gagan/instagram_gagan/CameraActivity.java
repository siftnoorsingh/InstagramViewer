package com.project.gagan.instagram_gagan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Sift on 7/10/2015.
 */
public class CameraActivity extends AppCompatActivity{

    private Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        toolbar2 = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar2);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        //Set text for tabs and add tabs to tab layout
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout2.addTab(tabLayout2.newTab().setText("Gallery"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Photo"));
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);

        //Create the camerapageradapter to switch between tabs
        final ViewPager viewPagerCam = (ViewPager) findViewById(R.id.pager2);
        final CameraPagerAdapter adapterCam = new CameraPagerAdapter
                (getSupportFragmentManager(), tabLayout2.getTabCount());
        viewPagerCam.setAdapter(adapterCam);

        //Create the tab listener
        viewPagerCam.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));
        tabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerCam.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}

//}
