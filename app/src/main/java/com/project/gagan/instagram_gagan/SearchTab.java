package com.project.gagan.instagram_gagan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fenglin on 3/10/15.
 */

public class SearchTab extends Fragment {
    private SearchResultImageAdapter searchResultImageAdapter;
    private View view;
    private String queryString;
    public SearchIDs searchIDs;
    private ArrayList originalIdList;
    private ArrayList fixedIdList = new ArrayList();
    private GridView gridView;

    private static SearchView searchViewDiscover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// search_tab
        view = inflater.inflate(R.layout.search_tab, container, false);
        gridView = (GridView) view.findViewById(R.id.gridViewSearch);
        searchIDs = new SearchIDs();
        gridView.setAdapter(new ImageAdapter(view.getContext()));

        onQuery(view);

        return view;
    }
    
    public void onQuery(View v) {

        searchViewDiscover = (SearchView) v.findViewById(R.id.searchViewDIscover);
        searchViewDiscover.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryString = query;

                GridView gridView2 = (GridView) view.findViewById(R.id.gridViewSearch);

                gridView2.setAdapter(new SearchResultFragmentAdapter(view.getContext(), query));


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Toast.makeText(getActivity(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
