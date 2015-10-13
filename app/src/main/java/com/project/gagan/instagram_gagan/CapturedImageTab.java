package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;


/**
 * Created by Sift on 10/10/2015.
 */
public class CapturedImageTab extends Fragment{

    Camera mCamera;
    CameraPreview cameraPreview;
    FrameLayout cameraFrame;
    ImageButton captureBtn;
    static final int PHOTO_WIDTH = 400;
    static final int PHOTO_HEIGHT = 400;
    View rootView;
    private Uri uri;

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
            //Bitmap correctBmp = Bitmap.createBitmap(bitmapPicture, 0, 0, bitmapPicture.getWidth(), bitmapPicture.getHeight(), null, true);
            int wid = bitmapPicture.getWidth();
            int hgt = bitmapPicture.getHeight();

            Bitmap correctBmp = Bitmap.createBitmap(wid, hgt, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(correctBmp);

            canvas.drawBitmap(bitmapPicture, 0f, 0f, null);
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap scaledBmp = Bitmap.createScaledBitmap(correctBmp, PHOTO_WIDTH, PHOTO_HEIGHT, true);
            correctBmp = Bitmap.createBitmap(scaledBmp , 0, 0, scaledBmp.getWidth(), scaledBmp.getHeight(), matrix, true);
            imageCaptured(correctBmp);
        }
    };


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.capimage_tab, container, false);
        try{
            mCamera = Camera.open();
            //Code for flash option
            Camera.Parameters params = mCamera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            // ... set other parameters
            mCamera.setParameters(params);
            setupCamera(mCamera);
        }catch (Exception e){
            Toast.makeText(rootView.getContext(), "Unable to connect to camera", Toast.LENGTH_SHORT).show();
        }

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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Because the Camera object is a shared resource, it's very
        // important to release it when the activity is paused.
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private void imageCaptured(Bitmap image){

        try {
            String filename = "bitmap.png";
            FileOutputStream stream = rootView.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            //ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 50, stream);
            //Intent intent = new Intent(rootView.getContext(), BrightenContrastImage.class);
            //byte[] byteArray2 = stream.toByteArray();

            stream.close();
            image.recycle();

            //intent.putExtra("picture", byteArray2);

            Intent intent = new Intent(rootView.getContext(), EditImage.class);
            intent.putExtra("picture", filename);
            intent.putExtra("uri", uri);


            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(rootView.getContext(), "Unable to open the image", Toast.LENGTH_SHORT).show();
        }
    }


}
