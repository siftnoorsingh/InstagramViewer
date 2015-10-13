package com.project.gagan.instagram_gagan;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Sift on 12/10/2015.
 */
public class EditImage extends AppCompatActivity{

    private static final double IMAGE_MAX_SIZE = 400;
    //keep track of cropping intent
    final int PIC_CROP = 2;
    Button cropButton;
    private Uri picUri;
    Bitmap bmp = null;
    private ImageButton buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_edit);
        //Bundle extras = getIntent().getExtras();
        //byte[] byteArray = extras.getByteArray("picture");
        //bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        String filename = getIntent().getStringExtra("picture");

        //Bitmap bmp = decodeFile(filename);
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        buttonBack = (ImageButton) findViewById(R.id.imageButton_edit_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditImage.this, CameraActivity.class);
                startActivity(intent);

            }
        });

        ImageView image = (ImageView) findViewById(R.id.imageView4);
        cropButton= (Button) findViewById(R.id.cropButton);
        picUri = getIntent().getData();

        image.setImageBitmap(bmp);
        final Bitmap finalBmp = bmp;
        cropButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageCropped(finalBmp);
                performCrop();
            }
        });
    }

    public void performCrop(){

        try {

            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type
            cropIntent.setDataAndType(picUri, "image/*");
            //cropIntent.setType("image/*");
            //cropIntent.putExtra("Bitmap",bmp);
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 200);
            cropIntent.putExtra("outputY", 200);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void imageCropped(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] byteArray2 = stream.toByteArray();
        Intent intent2 = new Intent(this, BrightenContrastImage.class);
        intent2.putExtra("picture", byteArray2);
        startActivity(intent2);
    }

    /*private Bitmap decodeFile(String f){
        Bitmap b = null;

        //Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;

        FileInputStream fis = null;
        try {
            fis = this.openFileInput(f);
            BitmapFactory.decodeStream(fis, null, o);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int scale = 1;
        if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
            scale = (int)Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
                    (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
        }

        //Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        try {
            fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, o2);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return b;
    }*/

}
