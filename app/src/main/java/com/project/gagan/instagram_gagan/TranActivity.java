package com.project.gagan.instagram_gagan;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseImageView;

public class TranActivity extends Activity {
    private String userObjectId;
    private String userName;

    private SearchUserAdapter searchUserAdapter;

    private ListView listView;
    private View view;
    private TextView textView;
    private ParseImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.profile_tab);
        setContentView(R.layout.activity_tran);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userObjectId = extras.getString("userObjectId");
            userName = extras.getString("username");

            //Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
        }
        view = findViewById(android.R.id.content);
        // Initialize the subclass of ParseQueryAdapter
        searchUserAdapter = new SearchUserAdapter(view.getContext(), userObjectId);

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) view.findViewById(R.id.listTran);
        listView.setAdapter(searchUserAdapter);
        searchUserAdapter.loadObjects();

        textView = (TextView)findViewById(R.id.user_name);
        textView.setText(userName);
        imageView = (ParseImageView)findViewById(R.id.icon_thumb);


//        ImageAdapter img = new ImageAdapter(view.getContext(),new SearchIDs());
//        // Initialize ListView and set initial view to mainAdapter
//        listView = (ListView) view.findViewById(R.id.listTran);
//        listView.setAdapter(img);
//        img.loadObjects();


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
