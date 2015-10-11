package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Created by Sift on 10/10/2015.
 */
public class CapturedImageTab extends Fragment{

    private Preview mPreview;
    Camera mCamera;
    int mNumberOfCameras;
    int mCurrentCamera;  // Camera ID currently chosen
    int mCameraCurrentlyLocked;  // Camera ID that's actually acquired
    //static final int REQUEST_IMAGE_CAPTURE = 1;
    //ImageView imageView;
    //Button imageButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.capimage_tab, container, false);
        /*
        //add these two things in the xml file
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageButton= (Button) rootView.findViewById(R.id.imageButton);
        */
        return rootView;
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
