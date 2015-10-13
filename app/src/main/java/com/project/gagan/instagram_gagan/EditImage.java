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
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * Created by Sift on 12/10/2015.
 */
public class EditImage extends AppCompatActivity{

    //keep track of cropping intent
    final int PIC_CROP = 2;
    Button cropButton;
    private Uri picUri;
    Bitmap bmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_edit);
        Bundle extras = getIntent().getExtras();
        //byte[] byteArray = extras.getByteArray("picture");
        //bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        String filename = getIntent().getStringExtra("picture");

        Bitmap bmp = null;
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                //imageCropped(finalBmp);
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
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
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

}
