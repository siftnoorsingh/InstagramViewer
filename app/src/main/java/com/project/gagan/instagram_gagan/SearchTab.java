package com.project.gagan.instagram_gagan;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchTab extends Fragment {


    private static ImageView imgView1;
    private static ImageView imgView2;
    private static ImageView imgView3;
    //change

    private View view;

    private static SearchView searchViewDiscover;

    private static TextView textViewSearchBox;

    private static Button buttonSearch;

    private static Button buttonToDisc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.search_tab, container, false);
        onQuery(view);
        return view;
    }

    public void onQuery(View v) {
        searchViewDiscover = (SearchView) v.findViewById(R.id.searchViewDIscover);
        searchViewDiscover.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getActivity(), newText, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
