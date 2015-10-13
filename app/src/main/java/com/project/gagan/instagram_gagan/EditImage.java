package com.project.gagan.instagram_gagan;

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
import java.io.FileInputStream;

/**
 * Created by Sift on 12/10/2015.
 */
public class EditImage extends AppCompatActivity{

    private static final double IMAGE_MAX_SIZE = 400;
    //Keep track of cropping intent
    final int PIC_CROP = 2;
    Button cropButton;
    Bitmap bmp = null;
    private ImageButton buttonBack;
    private Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_edit);
        String filename = getIntent().getStringExtra("picture");
        // Decode bitmap image using FileInputStream
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // On button click, go back to camera activity
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

        //On button click, crop the image
        cropButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                performCrop(finalBmp);
            }
        });
    }

    //Method to perform crop
    /*public void performCrop(){

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
    }*/

    //Method to perform crop
    public void performCrop(Bitmap croppedImage){
        int mOutputX =300;
        int mOutputY =300;
        croppedImage = Bitmap.createBitmap(croppedImage,(croppedImage.getWidth()-mOutputX)/2, (croppedImage.getHeight()-mOutputY)/2,mOutputX,mOutputY);
        //Canvas canvas = new Canvas(croppedImage);
        //Bitmap mBitmap=null;

        /*Rect srcRect = new Rect(0, 0, croppedImage.getWidth(), croppedImage.getHeight());
        Rect dstRect = new Rect(0, 0, mOutputX, mOutputY);

        int dx = (srcRect.width() - dstRect.width()) / 2;
        int dy = (srcRect.height() - dstRect.height()) / 2;

        // If the srcRect is too big, use the center part of it.
        srcRect.inset(Math.max(0, dx), Math.max(0, dy));

        // If the dstRect is too big, use the center part of it.
        dstRect.inset(Math.max(0, -dx), Math.max(0, -dy));*/

        // Draw the cropped bitmap in the center
        //canvas.drawBitmap(croppedImage, srcRect, dstRect, null);
        //Bitmap bmp = Bitmap.createBitmap(croppedImage,0,0,srcRect,dstRect);
        imageCropped(croppedImage);
    }

    //Save image in a byteArray and pass on to next activity in an intent
    private void imageCropped(Bitmap bmp){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] byteArray2 = stream.toByteArray();
        Intent intent2 = new Intent(this, BrightenContrastImage.class);
        intent2.putExtra("picture", byteArray2);
        startActivity(intent2);
    }

}
