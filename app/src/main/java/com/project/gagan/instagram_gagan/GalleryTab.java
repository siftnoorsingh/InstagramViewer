package com.project.gagan.instagram_gagan;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sift on 10/10/2015.
 */
public class GalleryTab extends Fragment{

    protected GridView gridView;
    protected GalleryAdapter galleryAdapter;

    //private static final int RESULT_OK = 100;
    //private int PICK_IMAGE_REQUEST = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.gallery_tab, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView);
        galleryAdapter = new GalleryAdapter(rootView.getContext(), R.layout.grid_cell_layout, getData());
        gridView.setAdapter(galleryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(rootView.getContext(), "Image at " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private ArrayList<Bitmap> getData(){
        return null;
    };

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
