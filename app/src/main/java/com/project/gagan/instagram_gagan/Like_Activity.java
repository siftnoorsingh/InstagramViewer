package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by lenovo on 13-10-2015.
 */
public class Like_Activity extends android.app.Activity {
    private String mPhotoId;
    ListView mLikelist;
    Button mLikeSubmit;
    Photo mPhoto;
    View view;
    ImageButton like;
    ParseQueryAdapter likeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.likes);


        view = findViewById(android.R.id.content);

        TextView t =(TextView)view.findViewById(R.id.name);
        t.setText("Likes :)");

        mPhotoId = getIntent().getStringExtra("photo");
        like = (ImageButton)view.findViewById(R.id.like);

        mLikelist = (ListView) view.findViewById(R.id.like_list);

        mLikeSubmit = (Button) view.findViewById(R.id.like_send);

/*

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);


        boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value", true);
        if (checkBoxValue) {
            mLikeSubmit.setEnabled(true);
        } else {
            mLikeSubmit.setEnabled(false);
        }
*/

        mLikeSubmit.setEnabled(true);

        likeAdapter = new LikeAdapter(view.getContext(), mPhotoId);
        mLikelist.setAdapter(likeAdapter);
        likeAdapter.loadObjects();




        ParseQuery<Photo> photoQuery = ParseQuery.getQuery("Photo");
        photoQuery.whereEqualTo("objectId", mPhotoId);
        photoQuery.getFirstInBackground(new GetCallback<Photo>() {
            @Override
            public void done(Photo object, ParseException error) {
                mPhoto = object;


            }
        });


        mLikeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLikeSubmit.isEnabled()) {
                    likePhoto(mPhoto);
                    mLikeSubmit.setText("Liked Already");
                    mLikeSubmit.setEnabled(false);
                    boolean b = !(mLikeSubmit.isEnabled());
//                    savePreferences("CheckBox_Value", b);
                    likeAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(v.getContext(),
                            "Already Liked the picture", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    private void likePhoto(Photo object) {
        ParseObject parseObject = ParseObject.create("Photo");
        parseObject = object.getParseObject("user");

        ParseUser currentUser = ParseUser.getCurrentUser();
       // ParseObject product = ParseObject.createWithoutData("Photo", mPhoto.getObjectId());

        Activity like = new Activity();
        like.setToUser((ParseUser) parseObject);//TODO
        like.setFromUser(currentUser);
        like.setType("like");
        like.put("photo", object);

            ParseACL acl = new ParseACL(currentUser);
            acl.setPublicReadAccess(true);
        like.setACL(acl);

            //cache PAP
            Toast.makeText(view.getContext(), "Updating Likes", Toast.LENGTH_LONG).show();
            like.saveEventually();
            mLikelist.refreshDrawableState();

        int countval = object.getInt("likeCount");
        countval = countval + 1;
        object.put("likeCount", countval);
        object.saveEventually();


        //reload comments
        String result = "RESULT_NO";
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",result);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
   /* private void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }*/
}

