package com.project.gagan.instagram_gagan;

import android.app.Application;
import com.parse.Parse;

/**
 * Created by Gagan on 29-Sep-15.
 */
public class InstagramApplication extends Application {

    //@Override
    public void onCreate() {
        super.onCreate();
        // Enable local Database
        Parse.enableLocalDatastore(this);
        // Connection with Parse through application ID and client Key
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_id));

    }
}
