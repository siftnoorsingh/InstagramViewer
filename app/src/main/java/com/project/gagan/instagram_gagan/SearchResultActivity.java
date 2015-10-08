package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

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

          //  query = getIntent().getStringExtra("Query");
        //   view = this.findViewById(android.R.id.content);
        //      view = inflater.inflate(R.layout.search_tab, container, false);
        GridView gridView = (GridView) findViewById(R.id.gridView_searchresult);
        ParseUser parseUser = new ParseUser();
//        if(query!=null) {
//            parseUser.setUsername(query);
//        }
        gridView.setAdapter(new SearchResultImageAdapter(getApplicationContext(),query));


        //       ParseUser user = ParseUser.getCurrentUser();
        ParseFile parseFile = parseUser.getParseFile("thumbnail");

//        if (parseFile != null) {
//            parseFile.getDataInBackground(new GetDataCallback() {
//                @Override
//                public void done(byte[] bytes, ParseException e) {
//                    if (e == null) {
//                        int size = 250;
//                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//
//                        if (bmp != null) {
//                            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, size, size, true));
//
//                        }
//                    }
//                }
//            });
//
//        }


        //setContentView(R.layout.activity_search_result);
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
