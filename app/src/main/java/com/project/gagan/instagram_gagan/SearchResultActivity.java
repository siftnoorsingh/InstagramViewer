package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

/**
 * Created by Fenglin
 */

public class SearchResultActivity extends AppCompatActivity {


    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    private View view;
    private Intent intent = getIntent();
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_result);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            query = extras.getString("Query");
        }

        // create gridView and set Adapter to the gridView
        GridView gridView = (GridView) findViewById(R.id.gridView_searchresult);
        gridView.setAdapter(new SearchResultImageAdapter(getApplicationContext(), query));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_result, menu);

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
