package com.project.gagan.instagram_gagan;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;

/**
 * Created by Sift, Gaganjot on 1/10/2015.
 * This is the user profile tab. It shows user's image thumbnail,
 * username, number of posts, number of followers, number of followings
 * and list of posts.
 */
public class ProfileTab extends Fragment {

    private View view;
    private UserProfileAdapter mUserViewAdapter;
    private int counter=0;
    private ListView listView;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private ImageView photoView;
    private TextView userPosts;
    private ParseUser userCurrent;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.profile_tab, container, false);


        // Set up the username
         userCurrent = ParseUser.getCurrentUser();

        TextView usernameView = (TextView) view.findViewById(R.id.user_name);
        usernameView.setText((String) userCurrent.get("username"));

        // Set up the current user's bio
        TextView userBio = (TextView) view.findViewById(R.id.bio);
        userBio.setText((String) userCurrent.get("biography"));

        // Set up the current user's posts count
        int count = userCurrent.getInt("postCount");
        userPosts = (TextView) view.findViewById(R.id.num_posts);


        // Query to get the number of followers
        int queryCountFollowers=0;
        int queryCountFollowings=0;

        ParseQuery queryFollowers = new ParseQuery("Activity");
        queryFollowers.whereEqualTo("type","follow");
        queryFollowers.whereEqualTo("toUser",ParseUser.getCurrentUser());
        try {
            queryCountFollowers = queryFollowers.count();
        }catch (ParseException e){
            Log.d("ZERO ","Values");
        }


        // Query to get the number of followers
        ParseQuery queryFollowings = new ParseQuery("Activity");
        queryFollowings.whereEqualTo("type","follow");
        queryFollowings.whereEqualTo("fromUser",ParseUser.getCurrentUser());
        try {
            queryCountFollowings = queryFollowings.count();
            userPosts.setText(count + " Posts " + queryCountFollowers + " Followers " + queryCountFollowings+" Followings");
        }catch (ParseException e){
            Log.d("ZERO ","Values");
        }



        // Initialize the subclass of ParseQueryAdapter
        mUserViewAdapter = new UserProfileAdapter(getActivity());

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(mUserViewAdapter);
        mUserViewAdapter.loadObjects();



        // Set up the user's profile (thumbnail) picture
        photoView = (ImageView) view.findViewById(R.id.user_thumbnail);
        photoView.setBackgroundResource(R.drawable.frame);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"Take Photo", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Take Photo")) {

                            // create Intent to take a picture and return control to the calling application
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            // start the image capture Intent
                            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });


        final ParseFile thumbnailFile = userCurrent.getParseFile("thumbnail");
        if (thumbnailFile != null) {
            thumbnailFile.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    if (e == null) {

                        int size = 250;
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        if (bmp != null) {

                            float scale = (bmp.getWidth() < bmp.getHeight()) ? (float) bmp.getWidth() / (float) size : (float) bmp.getHeight() / (float) size;
                            int width = (int) (bmp.getWidth() / scale);
                            int height = (int) (bmp.getHeight() / scale);
                            photoView.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                                    width, height, true));


                        }


                    }
                }
            });
        } else { // PlaceHolder ParseImageView if an object doesn't have a photo
            photoView.setImageResource(R.drawable.placeholder_user);

        }





        return view;
    }






    // Upload user's thumbnail through photo camera
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

                // Convert it to byte
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Compress image to lower quality scale 1 - 100
                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] image = stream.toByteArray();


                // Create the ParseFile
                ParseFile file = new ParseFile(Environment.getExternalStorageState() + System.currentTimeMillis() + ".png", image);


                // Create a column named "thumbnail" and insert the image
                userCurrent.put("thumbnail", file);

                // save the thumbnail
                userCurrent.saveInBackground();



                // Show a simple toast message
                Toast.makeText(getActivity(), "Thumbnail Uploaded",
                        Toast.LENGTH_SHORT).show();


                int size = 250;
                float scale = (thumbnail.getWidth() < thumbnail.getHeight()) ? (float) thumbnail.getWidth() / (float) size : (float) thumbnail.getHeight() / (float) size;
                int width = (int) (thumbnail.getWidth() / scale);
                int height = (int) (thumbnail.getHeight() / scale);
                photoView.setImageBitmap(Bitmap.createScaledBitmap(thumbnail,
                        width, height, true));


            }
        }else {
            Log.d("ERROR","   ERROR : Thumbnail didn't upload");
            // Show a simple toast message
            Toast.makeText(getActivity(), "Thumbnail din't upload",
                    Toast.LENGTH_SHORT).show();
        }

    }
}