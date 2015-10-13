package com.project.gagan.instagram_gagan;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by Sift on 1/10/2015.
 * Implemented by Fenglin
 * This class contains two One-Column-GridView. The top one is handled by FollowTabAdapter, showing following's recent activities;
 * The bottom GridView is handled by FollowActivityAdapter, it shows a list of activities related to the current user.
 */
public class FollowTab extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.follow_tab, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridView_ActivityFeed);
        GridView gridView2 = (GridView) view.findViewById(R.id.gridView_ActivityFeed2);
        gridView.setAdapter(new FollowTabAdapter(view.getContext()));
        gridView2.setAdapter(new FollowActivityAdapter(view.getContext()));

        return view;

    }

}
