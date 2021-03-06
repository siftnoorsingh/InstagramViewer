package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.parse.ParseUser;

/*
Created by Gaganjot, Sift

This is the main activity. User lands on this page after
valid username and password. It shows all the required tabs :
User Feed, Discover, Camera, Activity and Profile.
 */



public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView givenName;
    Button cameraBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        // Set up the username
        ParseUser user = ParseUser.getCurrentUser();
        givenName= (TextView)toolbar.findViewById(R.id.name);
        givenName.setText((String) user.get("name"));


        // Set up the Tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_dashboard));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_search));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_camera));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_follow));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_profile));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Set the camera tab and handle the on click event
        cameraBtn = (Button)findViewById(R.id.camera_btn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });


        // Set up the view pager for the slide features between different tabs
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
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

            case R.id.action_settings:// Setting option to set for log out button
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;

            case R.id.logout: // Set up for log out from the menu
                // Call the Parse log out method
                ParseUser.logOut();
                // Start and intent for the dispatch activity
                Intent intent = new Intent(MainActivity.this, SessionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
