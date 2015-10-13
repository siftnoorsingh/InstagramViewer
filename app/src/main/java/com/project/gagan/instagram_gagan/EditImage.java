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
    Button cropButton;
    Button contButton;
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
                Intent intent = new Intent(EditImage.this, CameraActivity.class);
                startActivity(intent);

            }
        });

        ImageView image = (ImageView) findViewById(R.id.imageView4);
        cropButton= (Button) findViewById(R.id.cropButton);
        contButton= (Button) findViewById(R.id.contButton);
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

        //On button click, continue without cropping
        contButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageCropped(finalBmp);
            }
        });
    }

    //Method to perform crop
    public void performCrop(Bitmap croppedImage){
        int mOutputX =300;
        int mOutputY =300;
        croppedImage = Bitmap.createBitmap(croppedImage,(croppedImage.getWidth()-mOutputX)/2, (croppedImage.getHeight()-mOutputY)/2,mOutputX,mOutputY);
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
