package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Flin on 3/10/15.
 * This class provides pictures for the gridView in SearchTab
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    private int numberOfUsers=10;

//    private ParseUser user = ParseUser.getCurrentUser();
//    private ParseFile parseFile = user.getParseFile("thumbnail");

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return numberOfUsers;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        final ImageView imageView;
        imageView = new ImageView(mContext);





//-----------
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                // ParseObject user1 = (ParseObject)list.

     //           if (!list.isEmpty()) {
                    numberOfUsers = list.size();

                    for (int i = 0; i < numberOfUsers; i++) {

                        ParseObject po = list.get(i);
                        ParseFile parseFile = po.getParseFile("thumbnail");
                        parseFile.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] bytes, ParseException e) {
                                if (e == null) {
                                    int size = 300;
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    bmp.s
                                    if (bmp != null) {
                                        imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, size, size, true));


                                    }
                                }
                            }
                        });

                    }

                }
      //      }
        });
        //-----------




//
//        ParseUser user = ParseUser.getCurrentUser();
//        ParseFile parseFile = user.getParseFile("thumbnail");
//
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


//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//        //    imageView = new ImageView(mContext);
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
//
//            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }

        //    imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


    // references to our images
    private Integer[] mThumbIds = {
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
            R.mipmap.ic_enample, R.mipmap.ic_enample,
    };
}