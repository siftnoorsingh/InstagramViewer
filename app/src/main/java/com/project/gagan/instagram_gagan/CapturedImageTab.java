package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


/**
 * Created by Sift on 10/10/2015.
 */
public class CapturedImageTab extends Fragment{

    Camera mCamera;
    CameraPreview cameraPreview;
    FrameLayout cameraFrame;
    ImageButton captureBtn;
    View rootView;

    Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            //Add the shutter audio here
        }
    };

    Camera.PictureCallback rawCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            //Get and process the raw image here
        }
    };

    Camera.PictureCallback jpegCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmapPicture = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap correctBmp = Bitmap.createBitmap(bitmapPicture, 0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight(), null, true);
            if (correctBmp.getWidth() > correctBmp.getHeight()) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                correctBmp = Bitmap.createBitmap(correctBmp , 0, 0, correctBmp.getWidth(), correctBmp.getHeight(), matrix, true);
            }
            imageCaptured(correctBmp);
        }
    };
    //static final int REQUEST_IMAGE_CAPTURE = 1;
    //ImageView imageView;
    //Button imageButton;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.capimage_tab, container, false);
        try{
            mCamera = Camera.open();
            //Code for flash option
            //Camera.Parameters params = new Camera.getParameters();
            //params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            // ... set other parameters
            //mCamera.setParameters(params);
            setupCamera(mCamera);
        }catch (Exception e){
            Toast.makeText(rootView.getContext(), "Unable to connect to camera", Toast.LENGTH_SHORT).show();
        }

        /*
        //add these two things in the xml file
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageButton= (Button) rootView.findViewById(R.id.imageButton);
        */
        return rootView;
    }

    private void setupCamera(final Camera camera){
        camera.setDisplayOrientation(90);
        cameraFrame = (FrameLayout) rootView.findViewById(R.id.camera_frame);
        cameraPreview = new CameraPreview(rootView.getContext(), mCamera);
        cameraFrame.addView(cameraPreview);

        captureBtn = (ImageButton) rootView.findViewById(R.id.imageButton2);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(shutterCallback, rawCallback, jpegCallback);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        // Because the Camera object is a shared resource, it's very
        // important to release it when the activity is paused.
        if (mCamera != null) {
            //cameraPreview.setCamera(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private void imageCaptured(Bitmap image){
        //Bitmap bmp = BitmapFactory.decode;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        Intent intent = new Intent(rootView.getContext(), EditImage.class);
        intent.putExtra("picture", byteArray);
        startActivity(intent);
        Toast.makeText(rootView.getContext(), "Image Captured", Toast.LENGTH_SHORT).show();
    }

    /*//Add this method to the onCLick function in the xml file for imageButton
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Image captured and saved to fileUri specified in the Intent
            //Toast.makeText(this, "Image saved to:\n" +
            //         data.getData(), Toast.LENGTH_LONG).show();
            // This is another way where you can get the data about the image as well to make other changes to it.
              Bundle extras = data.getExtras();
              Bitmap photo = (Bitmap) extras.get("data");
              imageView.setImageBitmap();
        } else if (resultCode == RESULT_CANCELED) {
            // User cancelled the image capture
        }
    }*/
}
