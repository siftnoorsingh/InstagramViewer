package com.project.gagan.instagram_gagan;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class TranActivity extends Activity {
    private String userObjectId;
    private String userName;

    private SearchUserAdapter searchUserAdapter;
    private ImageButton imageButton;
    private ListView listView;
    private View view;
    private TextView textView;
    private ParseImageView imageView;
    private ParseFile thumbnail;
    private ParseQuery<ParseUser> users;
    private ParseObject toUser;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tran);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userObjectId = extras.getString("userObjectId");
            userName = extras.getString("username");
        }

        users = ParseUser.getQuery();
        users.whereEqualTo("objectId", userObjectId);

        users.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {
                    for (ParseUser p : list) {

                        thumbnail = p.getParseFile("thumbnail");
                        toUser = p;

                        imageView.setParseFile(thumbnail);
                        imageView.loadInBackground();
                        Log.d("111!!!!", thumbnail.getName());
                    }
                } else {

                }
            }

        });


        view = findViewById(android.R.id.content);
        // Initialize the subclass of ParseQueryAdapter
        searchUserAdapter = new SearchUserAdapter(view.getContext(), userObjectId);

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) view.findViewById(R.id.listTran);
        listView.setAdapter(searchUserAdapter);
        searchUserAdapter.loadObjects();

        textView = (TextView) findViewById(R.id.user_name);
        textView.setText(userName);
        imageView = (ParseImageView) findViewById(R.id.user_thumbnail);


        followOnClick(view);


    }

    public void followOnClick(View view) {

        // this flag is to control the follow button so that it does not create multiple entries when click more than once
        flag = false;
        imageButton = (ImageButton) findViewById(R.id.imageButtonFollow);
        imageButton.setImageResource(R.drawable.ic_action_name2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setImageResource(R.drawable.ic_action_name);


                if (flag != true) {
                    final ParseObject newFollowQuery = new ParseObject("Activity");

                    newFollowQuery.put("fromUser", ParseUser.getCurrentUser());

                    //  if(toUser!=null) {
                    newFollowQuery.put("toUser", toUser);
                    // }
                    newFollowQuery.put("type", "follow");
                    newFollowQuery.saveInBackground();
                    flag = true;

                }
            }
        });
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
