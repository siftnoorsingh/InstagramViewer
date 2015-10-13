package com.project.gagan.instagram_gagan;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;

/**
 * Created by Sift on 1/10/2015.
 */
public class ProfileTab extends Fragment {

    private View view;
    private UserProfileAdapter mUserViewAdapter;
    private int counter=0;
    private ListView listView;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private ImageView photoView;
    private TextView userPosts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.profile_tab, container, false);


        // Set up the username
        ParseUser user = ParseUser.getCurrentUser();

        TextView usernameView = (TextView) view.findViewById(R.id.user_name);
        usernameView.setText((String) user.get("username"));

        // Set up the current user's bio
        TextView userBio = (TextView) view.findViewById(R.id.bio);
        userBio.setText((String) user.get("biography"));

        // Set up the current user's posts count
        int count = user.getInt("postCount");
        userPosts = (TextView) view.findViewById(R.id.num_posts);
        userPosts.setText(count + " Posts");



        /*// Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), "Photo");
        mainAdapter.setImageKey("image");
        mainAdapter.setTextKey("description");*/


        // Initialize the subclass of ParseQueryAdapter
        mUserViewAdapter = new UserProfileAdapter(getActivity());

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(mUserViewAdapter);
        mUserViewAdapter.loadObjects();


        /*// Initialize toggle button
        Button toggleButton = (Button) view.findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listView.getAdapter() == mainAdapter) {
                    listView.setAdapter(mUserViewAdapter);
                    mUserViewAdapter.loadObjects();
                } else {
                    listView.setAdapter(mainAdapter);
                    mainAdapter.loadObjects();
                }
            }

        });*/


        // Set up the user's profile picture
        photoView = (ImageView) view.findViewById(R.id.user_thumbnail);
        photoView.setBackgroundResource(R.drawable.frame);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
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


        final ParseFile thumbnailFile = user.getParseFile("thumbnail");
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
        } else { // Clear ParseImageView if an object doesn't have a photo
            photoView.setImageResource(R.drawable.placeholder_user);

        }


        /*ParseImageView mealImage = (ParseImageView) view.findViewById(R.id.user_thumbnail);
        ParseFile photoFile = user.getParseFile("thumbnail");
        if (photoFile != null) {
            mealImage.setParseFile(photoFile);
            mealImage.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                    photoView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), bmp., 100, 100));
                }
            });
        }*/


        //int countPosts = listView.getAdapter().getCount();
        //

        /*String CountListRowNo= String.valueOf(+listView.getAdapter().getCount());
        userPosts.setText( CountListRowNo+ " Posts");

        Toast.makeText(getActivity(), "Total number of Items are:" + listView.getAdapter().getCount(), Toast.LENGTH_LONG).show();*/


        return view;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.d("hello", " cheese ");
        //Log.d("RC : ", (String.valueOf(requestCode)));
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


                // Call User class in Parse
                ParseUser imgupload = ParseUser.getCurrentUser();
                int count = imgupload.getInt("postCount");

                count= count+1;
                // Create a column named "thumbnail" and insert the image
                imgupload.put("thumbnail", file);
                imgupload.put("postCount", count);

                imgupload.saveInBackground();



                // Show a simple toast message
                Toast.makeText(getActivity(), "Thumbnail Uploaded",
                        Toast.LENGTH_SHORT).show();

                userPosts.setText(count + " Posts");

                int size = 250;
                float scale = (thumbnail.getWidth() < thumbnail.getHeight()) ? (float) thumbnail.getWidth() / (float) size : (float) thumbnail.getHeight() / (float) size;
                int width = (int) (thumbnail.getWidth() / scale);
                int height = (int) (thumbnail.getHeight() / scale);
                photoView.setImageBitmap(Bitmap.createScaledBitmap(thumbnail,
                        width, height, true));

                //photoView.setImageBitmap(thumbnail);
            }
        }else {
            Log.d("ERROR","   ERROR : Thumbnail didn't upload");
            // Show a simple toast message
            Toast.makeText(getActivity(), "Thumbnail din't upload",
                    Toast.LENGTH_SHORT).show();
        }

    }
}