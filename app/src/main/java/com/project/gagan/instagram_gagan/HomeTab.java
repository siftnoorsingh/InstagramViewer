package com.project.gagan.instagram_gagan;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeTab extends Fragment {

    public static List<ParseObject> feed;
    private ParseQueryAdapter<ParseObject> mainAdapter;
    private HomeRecyclerViewAdapter feed_adapter;
    String bestProvider;
    private HomeGeoAdapter feed_geo_adapter;
    private final String LOG_TAG = HomeRecyclerViewAdapter.class.getSimpleName();
    View view;
    private ListView list_creator;

    private  ParseGeoPoint myPoint;

    //Fragment tab for home
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_tab, container, false);
        feed = new ArrayList<>();

        mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(),"_User");
        mainAdapter.setTextKey("username");
        mainAdapter.setImageKey("thumbnail");
        mainAdapter.setImageKey("pic");
        feed_adapter = new HomeRecyclerViewAdapter(getActivity());


        list_creator = (ListView)view.findViewById(R.id.listview);
        list_creator.setAdapter(feed_adapter);
        feed_adapter.loadObjects();
        myPoint = geoPointFromLocation(view);

        feed_geo_adapter = new HomeGeoAdapter(getActivity(),myPoint);



        Button toggleButton = (Button) view.findViewById(R.id.toggleButton2);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list_creator.getAdapter() == feed_adapter) {

                    list_creator.setAdapter(feed_geo_adapter);
                    feed_geo_adapter.loadObjects();
                    Toast.makeText(v.getContext(),"Sorting based on Location",Toast.LENGTH_SHORT).show();
                } else {
                    list_creator.setAdapter(feed_adapter);
                    feed_adapter.loadObjects();
                    Toast.makeText(v.getContext(),"Sorting based on Date/Time",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;

    }

    public ParseGeoPoint geoPointFromLocation(final View v) {
        double longitude = 0;
        double latitude = 0;

        LocationManager lm = (LocationManager)v.getContext().getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.

                if (location != null) {
                    Log.i("SuperMap", "Location changed : Lat: " + location.getLatitude() + " Lng: " +
                            location.getLongitude());
                }

//                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}


        };


        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0,
                locationListener);
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.d(Double.toString(longitude), Double.toString(latitude));

        }
        return new ParseGeoPoint(latitude, longitude);
    }

}
