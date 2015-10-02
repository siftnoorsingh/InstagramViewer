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

public class SearchTab extends Fragment {


    private static ImageView imgView1;
    private static ImageView imgView2;
    private static ImageView imgView3;
    //change

    private static SearchView searchViewDiscover;

    private static TextView textViewSearchBox;

    private static Button buttonSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_tab, container, false);
    }


}
