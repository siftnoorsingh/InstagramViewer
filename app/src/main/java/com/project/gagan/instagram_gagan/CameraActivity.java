package com.project.gagan.instagram_gagan;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Sift on 7/10/2015.
 */
public class CameraActivity extends AppCompatActivity{

    private Toolbar toolbar;
    ViewPager pagerCam;
    ViewPagerAdapter adapterCam;
    SlidingTabLayout tabs;
    int numOfTabs = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


//        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
//        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        //Set icons for tabs and add tabs to tab layout
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Gallery"));
//        tabLayout.addTab(tabLayout.newTab().setText("Photo"));
//        tabLayout.addTab(tabLayout.newTab().setText("Video"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Create the viewpageradapter to switch between tabs
//        final ViewPager viewPagerCam = (ViewPager) findViewById(R.id.pager);
//        final PagerAdapter adapterCam = new PagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPagerCam.setAdapter(adapterCam);
//
//        //create the tab listener
//        viewPagerCam.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPagerCam.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        /*switch (item.getItemId()) {

            case R.id.action_settings:// Setting option to set for push notifications
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;

            case R.id.logout:
                // Call the Parse log out method
                ParseUser.logOut();
                // Start and intent for the dispatch activity
                Intent intent = new Intent(MainActivity.this, SessionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }*/
        return true;
    }
}

//}
