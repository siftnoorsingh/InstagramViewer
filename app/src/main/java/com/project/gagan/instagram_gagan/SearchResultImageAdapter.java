package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fenglin on 7/10/2015.
 */
public class SearchResultImageAdapter extends BaseAdapter {


    private Context mContext;
    private String queryString;
    private int numberOfUsers = 3;

//    private ParseUser user = ParseUser.getCurrentUser();
//    private ParseFile parseFile = user.getParseFile("thumbnail");

    public SearchResultImageAdapter(Context c, String query) {
        mContext = c;
        queryString = query;

    }

    public int getCount() {
        return 1;
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


//        final ArrayList<Photo> photos = new ArrayList<>();
//
////
//        final ParseUser parseUser = new ParseUser();

//-----------
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                // ParseObject user1 = (ParseObject)list.

                //           if (!list.isEmpty()) {
                //    numberOfUsers = list.size();

                // counter to check if the query user is exist or not
                int counter = 0;
                for (int i = 0; i < numberOfUsers; i++) {
                    ParseObject po = list.get(i);
                    if (po.getString("username").equals(queryString)) {
                        counter++;
                        Toast.makeText(mContext, po.getString("username"), Toast.LENGTH_SHORT).show();


                        ParseFile parseFile = po.getParseFile("thumbnail");
                        //  photo.setImage(parseFile);
                        parseFile.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] bytes, ParseException e) {
                                if (e == null) {
                                    int size = 500;
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                                    if (bmp != null) {
                                        float scale = (bmp.getWidth() < bmp.getHeight()) ? (float) bmp.getWidth() / (float) size : (float) bmp.getHeight() / (float) size;
                                        int width = (int) (bmp.getWidth() / scale);
                                        int height = (int) (bmp.getHeight() / scale);
                                        imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, width, height, true));


                                    }
                                }
                            }
                        });
                    }


                }
                if (counter == 0) {
                    Toast.makeText(mContext, "User " + queryString + " does not exist", Toast.LENGTH_SHORT).show();

                }

            }

        });



        return imageView;
    }

}
