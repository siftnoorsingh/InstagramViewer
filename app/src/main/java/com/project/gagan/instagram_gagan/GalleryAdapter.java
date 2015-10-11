package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Sift on 12/10/2015.
 */
class GalleryAdapter extends BaseAdapter {

    private Context mContext;

    public GalleryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Convert DP to PX
    // Source: http://stackoverflow.com/a/8490361
    public int dpToPx(int dps) {
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);

        return pixels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        int imageID = 0;

        // Want the width/height of the items
        // to be 120dp
        int wPixel = dpToPx(120);
        int hPixel = dpToPx(120);

        // Move cursor to current position
        mCursor.moveToPosition(position);
        // Get the current value for the requested column
        imageID = mCursor.getInt(columnIndex);

        if (convertView == null) {
            // If convertView is null then inflate the appropriate layout file
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gallery_tab, null);
        }
        else {

        }

        imageView = (ImageView) convertView.findViewById(R.id.imageView);

        // Set height and width constraints for the image view
        imageView.setLayoutParams(new LinearLayout.LayoutParams(wPixel, hPixel));

        // Set the content of the image based on the provided URI
        imageView.setImageURI(
                Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID)
        );

        // Image should be cropped towards the center
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Set Padding for images
        imageView.setPadding(8, 8, 8, 8);

        // Crop the image to fit within its padding
        imageView.setCropToPadding(true);

        return convertView;
    }
}
