package com.project.gagan.instagram_gagan;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * Created by Sift on 1/10/2015.
 */
public class ProfileTab extends Fragment {

    private View view;


    private UserViewAdapter mUserViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.profile_tab, container, false);
        // Set up the username
        ParseUser user = ParseUser.getCurrentUser();
        TextView usernameView = (TextView) view.findViewById(R.id.user_name);
        usernameView.setText((String) user.get("username"));

        // Set up the user's profile picture


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

        final ImageView photoView = (ImageView) view.findViewById(R.id.user_thumbnail);
        photoView.setBackgroundResource(R.drawable.frame);
        final ParseFile thumbnailFile = user.getParseFile("thumbnail");
        if (thumbnailFile != null) {
            thumbnailFile.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    if (e == null) {

                        int size = 250;
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        if (bmp != null) {

                            float scale = (bmp.getWidth() < bmp.getHeight()) ? (float)bmp.getWidth() / (float)size : (float)bmp.getHeight() / (float)size;
                            int width = (int)(bmp.getWidth() / scale);
                            int height = (int)(bmp.getHeight() / scale);
                             photoView.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                                      width, height, true));




                        }



                    }
                }
            });
        } else { // Clear ParseImageView if an object doesn't have a photo
            photoView.setImageResource(android.R.color.transparent);
        }



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

}
