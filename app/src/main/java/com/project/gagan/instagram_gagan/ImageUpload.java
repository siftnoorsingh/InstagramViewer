package com.project.gagan.instagram_gagan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Sift on 13/10/2015.
 */
public class ImageUpload extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");
        // Create the ParseFile
        ParseFile file = new ParseFile("TinyPic.png", byteArray);
        // Upload the image into Parse Cloud
        file.saveInBackground();

        // Create a New Class called "Photo" in Parse
        ParseObject imgupload = new ParseObject("Photo");

        // Create a column named "ImageName" and set the string
        imgupload.put("description", "Display Pic");

        // Create a column named "ImageFile" and insert the image
        imgupload.put("image", file);

        // Create a column named "user" and insert the user pointer
        //imgupload.put("user", asdasdc );

        // Create the class and the columns
        imgupload.saveInBackground();

        // Show a simple toast message
        Toast.makeText(this, "Image Uploaded",
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
