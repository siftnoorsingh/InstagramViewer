package com.project.gagan.instagram_gagan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class DiscoverActivity extends Activity {

    private static ImageView imgView1;
    private static ImageView imgView2;
    private static ImageView imgView3;

    private static SearchView searchViewDiscover;

    private static TextView textViewSearchBox;

    private static Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
    }

    // TODO need to handle the query later
    public void onSearchButtonClick() {
        imgView1 = (ImageView) findViewById(R.id.imageView1);
        imgView2 = (ImageView) findViewById(R.id.imageView2);
        imgView3 = (ImageView) findViewById(R.id.imageView3);
        //  buttonSearch = (Button) findViewById(R.id.)

        searchViewDiscover = (SearchView) findViewById(R.id.searchViewDiscover);
        searchViewDiscover.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // calls when the user submit a query
            @Override
            public boolean onQueryTextSubmit(String query) {

                String s = query;

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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
