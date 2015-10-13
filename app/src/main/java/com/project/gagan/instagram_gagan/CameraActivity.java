package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by Sift on 7/10/2015.
 */
public class CameraActivity extends AppCompatActivity{

    private Toolbar toolbar2;
    ViewPager pager2;
    ViewPagerAdapter adapterCam;
    SlidingTabLayout tabs;
    int numOfTabs = 2;
    private TextView givenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        toolbar2 = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar2);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        // Set up the username
        ParseUser user = ParseUser.getCurrentUser();
        givenName= (TextView)toolbar2.findViewById(R.id.name);
        givenName.setText((String) user.get("name"));

        //Set icons for tabs and add tabs to tab layout
        TabLayout tabLayout2 = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout2.addTab(tabLayout2.newTab().setText("Gallery"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Photo"));
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);

        //Create the viewpageradapter to switch between tabs
        final ViewPager viewPagerCam = (ViewPager) findViewById(R.id.pager2);
        final CameraPagerAdapter adapterCam = new CameraPagerAdapter
                (getSupportFragmentManager(), tabLayout2.getTabCount());
        viewPagerCam.setAdapter(adapterCam);

        //create the tab listener
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_settings:// Setting option to set for push notifications
                startActivity(new Intent(CameraActivity.this, SettingsActivity.class));
                return true;

            case R.id.logout:
                // Call the Parse log out method
                ParseUser.logOut();
                // Start and intent for the dispatch activity
                Intent intent = new Intent(CameraActivity.this, SessionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}

//}
