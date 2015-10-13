package com.project.gagan.instagram_gagan;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Sift on 1/10/2015.
 */
public class CameraTab extends Fragment {
    //Fragment tab for camera
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout in the fragment
        return inflater.inflate(R.layout.camera_tab, container, false);
    }

}
