package com.project.gagan.instagram_gagan;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Gurbakhshish on 10/11/2015.
 */
public class GalleryAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<String> data;
    private int resource;

    public GalleryAdapter(Context context, int resource, ArrayList data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            row = ((Activity) context).getLayoutInflater().inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.gallery_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.image.setImageBitmap(MediaStore.Images.Thumbnails
                .getThumbnail(context.getContentResolver(),
                        Integer.parseInt(data.get(position)),
                        MediaStore.Images.Thumbnails.MICRO_KIND,
                        null));

        return row;
    }

    public ArrayList<String> getData() {
        return data;
    }

    static class ViewHolder{
        ImageView image;
    }
}
