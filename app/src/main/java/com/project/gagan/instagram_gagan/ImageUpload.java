package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Sift, Gaganjot on 13/10/2015.
 *
 * This class uploads the photo on Parse.
 * This photo has already being cropped (from EditImage.java)
 * and the filters (from ImageFilters.java) are applied to it.
 *
 * This class also handles to upload the location where the photo
 * being taken in Geo points and also save it on Parse.
 */
public class ImageUpload extends AppCompatActivity {

    private double longitude=0;
    private double latitude=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");

        // Create the ParseFile
        ParseFile file = new ParseFile(Environment.getExternalStorageState() + System.currentTimeMillis() + ".png", byteArray);

        // Create a New Class called "Photo" in Parse
        ParseObject imgupload = ParseObject.create("Photo");

        /* Call the Parse user instance to save the number of photos
        a current user uploads to show in the user profile
         */
        ParseUser user= ParseUser.getCurrentUser();

        /*
        Pick the current user's location
         */
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                if (location != null) {
                    Log.i("SuperMap", "Location changed : Lat: " + location.getLatitude() + " Lng: " +
                            location.getLongitude());
                }

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}


        };


        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0,
                locationListener);
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


    /*
    If the user's gps is on; then retrieves longitude and latitude
     */
        if(location!=null){
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            Log.d(Double.toString(longitude), Double.toString(latitude));

        }else{
            Toast.makeText(ImageUpload.this,"Turn On GPS",Toast.LENGTH_SHORT).show();
        }


        // saving the location on Parse
        ParseGeoPoint geoPoint = new ParseGeoPoint(latitude , longitude );
        imgupload.put("location", geoPoint );


        // getting the previous value from the postCount field in the user table in Parse
        int count = user.getInt("postCount");
        // Increment the count after the upload
        count= count+1;
        // update the count post value
         user.put("postCount", count);


        // Create a column named "image" and set the string
        imgupload.put("description", "Tiny Pic");

        // Create a column named "ImageFile" and insert the image
        imgupload.put("image", file);

        // Create a column named "user" and insert the user pointer
        imgupload.put("user", ParseUser.getCurrentUser());

        // save on Parse
        imgupload.saveInBackground();
        user.saveInBackground();

        // Show a simple toast message
        Toast.makeText(this, "Image Uploaded", Toast.LENGTH_SHORT).show();


        // After upload, return the user feed
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
