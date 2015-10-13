package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
 * Created by lenovo on 12-10-2015.
 */
public class Comment_Activity extends android.app.Activity{

    private String mPhotoId;
    ListView mCommentList;
    EditText mCommentInput;
    ImageButton mCommentSubmit;
    Photo mPhoto;
    View view;
    ParseQueryAdapter commentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);
        view = findViewById(android.R.id.content);

        TextView t =(TextView)view.findViewById(R.id.name);
        t.setText("User Comments");
        mPhotoId = getIntent().getStringExtra("photo");
        mCommentInput = (EditText) view.findViewById(R.id.comment_input);
        mCommentSubmit = (ImageButton) view.findViewById(R.id.comment_send);
       // mPhotoView = (ParseImageView) findViewById(R.id.image);
        mCommentList = (ListView) view.findViewById(R.id.comment_list);

        commentAdapter = new CommentAdapter(view.getContext(),mPhotoId);
        //commentAdapter = new ParseQueryAdapter<ParseObject>(this, "Activity");
        //commentAdapter.setTextKey("content");
        mCommentList.setAdapter(commentAdapter);
        commentAdapter.loadObjects();


       ParseQuery<Photo> photoQuery = ParseQuery.getQuery("Photo");
        photoQuery.whereEqualTo("objectId", mPhotoId);
        photoQuery.getFirstInBackground(new GetCallback<Photo>() {
            @Override
            public void done(Photo object, ParseException error) {
                //Log.e("Details", "Error" + object);
                mPhoto = object;
                //mPhotoView.setParseFile(photo.getImage());
                //mPhotoView.loadInBackground();


            }
        });


        mCommentSubmit.setEnabled(true);
        mCommentInput.setEnabled(true);
        mCommentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitComment();
            }
        });
    }

    private void submitComment() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseObject product = ParseObject.createWithoutData("Photo", mPhoto.getObjectId());

        String commentText = mCommentInput.getText().toString().trim();
        if(commentText.length() > 0 && mPhoto != null){ //photo.objectForKey("user")
            //Log.d("inside submit","commenting");
            Activity comment = new Activity();
            comment.setContent(commentText);
            comment.setToUser(mPhoto.getUser());//TODO
            comment.setFromUser(currentUser);
            comment.setType("comment");
            comment.put("photo", product);

            ParseACL acl = new ParseACL(currentUser);
            acl.setPublicReadAccess(true);
            comment.setACL(acl);

            //cache PAP
            Toast.makeText(view.getContext(),"Comment Uploaded",Toast.LENGTH_LONG).show();
            comment.saveEventually();
            mCommentList.refreshDrawableState();

            String result = "RESULT_NO";
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", result);
            setResult(RESULT_OK, returnIntent);
            finish();

        }
        mCommentInput.setText("");
        //reload comments
    }
}
