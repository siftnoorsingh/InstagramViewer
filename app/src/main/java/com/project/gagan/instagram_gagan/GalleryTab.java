package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.content.ContentResolver;

import java.io.IOException;

/**
 * Created by Sift on 10/10/2015.
 */
public class GalleryTab extends Fragment{

    protected Cursor mCursor;
    protected int columnIndex;
    protected GridView mGridView;
    protected GalleryAdapter mAdapter;
    //private static final int RESULT_OK = 100;
    //private int PICK_IMAGE_REQUEST = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.gallery_tab, container, false);
        //Intent intent = new Intent();
        // Show only images, no videos or anything else
        //intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        //startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        // Get all the images on phone

        String[] projection = {
                MediaStore.Images.Thumbnails._ID,
                MediaStore.Images.Thumbnails.IMAGE_ID
        };

        mCursor = getContentResolver().query(
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Images.Thumbnails.IMAGE_ID + " DESC"
        );

        columnIndex = mCursor.getColumnIndexOrThrow(projection[0]);

        // Get the GridView layout
        mGridView = (GridView) rootView.findViewById(R.id.gridView);
        mAdapter = new GalleryAdapter(this);
        mGridView.setAdapter(mAdapter);
        return rootView;
    }



    //@Override
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data,View rootView) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            // Log.d(TAG, String.valueOf(bitmap));
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);

        }
    }*/

}
