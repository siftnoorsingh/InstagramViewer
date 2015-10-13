package com.project.gagan.instagram_gagan;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Sift on 10/10/2015.
 */
public class GalleryTab extends Fragment{

    protected GridView gridView;
    protected GalleryAdapter galleryAdapter;
    View rootView;

    //private static final int RESULT_OK = 100;
    //private int PICK_IMAGE_REQUEST = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.gallery_tab, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView);
        galleryAdapter = new GalleryAdapter(rootView.getContext(), R.layout.grid_cell_layout, getData());
        gridView.setAdapter(galleryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, galleryAdapter.getData().get(position));
                    Bitmap image = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                    String filename = "bitmap.png";
                    FileOutputStream stream = rootView.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                    image.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    stream.close();
                    image.recycle();

                    Intent intent = new Intent(rootView.getContext(), EditImage.class);
                    intent.putExtra("image", filename);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(rootView.getContext(), "Unable to open the image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    private ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<String>();
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};

        Cursor cursor = rootView.getContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null);

        Toast.makeText(rootView.getContext(), "Images selected " + cursor.getCount(), Toast.LENGTH_SHORT).show();

        if(cursor.moveToFirst()){
            do{
                String _data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                data.add(_data);
            }while(cursor.moveToNext());
        }

        return data;
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
