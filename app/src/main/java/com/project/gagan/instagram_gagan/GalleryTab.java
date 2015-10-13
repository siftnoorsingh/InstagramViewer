package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
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

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Sift on 10/10/2015.
 */
public class GalleryTab extends Fragment{

    protected GridView gridView;
    protected GalleryAdapter galleryAdapter;
    View rootView;
    static final int PHOTO_WIDTH = 400;
    static final int PHOTO_HEIGHT = 400;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.gallery_tab, container, false);

        gridView = (GridView) rootView.findViewById(R.id.gridView);
        galleryAdapter = new GalleryAdapter(rootView.getContext(), R.layout.grid_cell_layout, getData());
        gridView.setAdapter(galleryAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
                try {
                    Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, galleryAdapter.getData().get(position));
                    Bitmap image = MediaStore.Images.Media.getBitmap(rootView.getContext().getContentResolver(), uri);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 50, stream);
                    byte[] byteArray2 = stream.toByteArray();
                    //Scale the file to improve performance and reduce memory consumption
                    Bitmap bitmapPicture = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);

                    int wid = bitmapPicture.getWidth();
                    int hgt = bitmapPicture.getHeight();

                    Bitmap correctBmp = Bitmap.createBitmap(wid, hgt, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(correctBmp);

                    canvas.drawBitmap(bitmapPicture, 0f, 0f, null);
                    Matrix matrix = new Matrix();
                    //Scale the Bitmap image to the size of imageview window
                    Bitmap scaledBmp = Bitmap.createScaledBitmap(correctBmp, PHOTO_WIDTH, PHOTO_HEIGHT, true);
                    correctBmp = Bitmap.createBitmap(scaledBmp , 0, 0, scaledBmp.getWidth(), scaledBmp.getHeight(), matrix, true);
                    String filename = "bitmap.png";
                    FileOutputStream stream2 = rootView.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                    correctBmp.compress(Bitmap.CompressFormat.PNG, 50, stream2);

                    stream.close();
                    image.recycle();
                    //Get the captured image's filename and uri and pass it to cropping activity
                    Intent intent = new Intent(rootView.getContext(), EditImage.class);
                    intent.putExtra("picture", filename);
                    intent.putExtra("uri", uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(rootView.getContext(), "Unable to open the image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
    //Get all the images from the gallery in an array list
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

}
