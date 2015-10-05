package com.project.gagan.instagram_gagan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

/**
 * THIS Activity is NOT used in this project, please do not edit this one.
 * The real Discover function is the SearchTab.
 * - Fenglin
 */
public class DiscoverActivity extends Activity {

    private static ImageView imgView1;
    private static ImageView imgView2;
    private static ImageView imgView3;

    private static SearchView discoversearchView;

    private static TextView textViewSearchBox;

    private static Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onSearch();
        setContentView(R.layout.activity_discover);
    }

    // TODO need to handle the query later
    public void onSearch() {
        imgView1 = (ImageView) findViewById(R.id.imageViewFeed);
        imgView2 = (ImageView) findViewById(R.id.imageView2);
        imgView3 = (ImageView) findViewById(R.id.imageView3);
        //  buttonSearch = (Button) findViewById(R.id.)
        //       Toast.makeText(DiscoverActivity.this, "1321", Toast.LENGTH_LONG).show();

        discoversearchView = (SearchView) findViewById(R.id.searchViewDiscover);
  //      discoversearchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discover, menu);
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
