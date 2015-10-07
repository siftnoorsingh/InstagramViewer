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
 */
public class FollowTab extends Fragment {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.follow_tab, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridView_ActivityFeed);
        gridView.setAdapter(new ActivityFeedAdapter(view.getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ParseUser currentUser = ParseUser.getCurrentUser();
                String CurrentUserName = currentUser.getUsername();


                Toast.makeText(getActivity(), "Activity Feed " + position + " uploaded by "+CurrentUserName ,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

}
