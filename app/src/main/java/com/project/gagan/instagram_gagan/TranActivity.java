package com.project.gagan.instagram_gagan;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class TranActivity extends Activity {
    private String value;

    private SearchUserAdapter searchUserAdapter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.profile_tab);
        setContentView(R.layout.profile_tab);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("userObjectId");
            //Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
        }

        // Initialize the subclass of ParseQueryAdapter
        searchUserAdapter = new SearchUserAdapter(TranActivity.this, value);

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) TranActivity.this.findViewById(R.id.list);
        listView.setAdapter(searchUserAdapter);
        searchUserAdapter.loadObjects();


        //   SearchUserFragment searchUserFragment = new SearchUserFragment();


//
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout2);
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_dashboard));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_search));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_camera));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_follow));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_profile));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        final PagerAdapter adapter = new PagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
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
//


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tran, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
