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
    private static ImageView imgView4;
    private static ImageView imgView5;
    private static ImageView imgView6;
    private static ImageView imgView7;
    private static ImageView imgView8;
    private static ImageView imgView9;
    //change

    private View view;

    private static SearchView searchViewDiscover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.search_tab, container, false);

        onQuery(view);

        onClickPic(view);

        return view;
    }

    public void onClickPic(View v) {
        imgView1 = (ImageView) v.findViewById(R.id.imageViewSearch1);
        imgView2 = (ImageView) v.findViewById(R.id.imageViewSearch2);
        imgView3 = (ImageView) v.findViewById(R.id.imageViewSearch3);
        imgView4 = (ImageView) v.findViewById(R.id.imageViewSearch4);
        imgView5 = (ImageView) v.findViewById(R.id.imageViewSearch5);
        imgView6 = (ImageView) v.findViewById(R.id.imageViewSearch6);
        imgView7 = (ImageView) v.findViewById(R.id.imageViewSearch7);
        imgView8 = (ImageView) v.findViewById(R.id.imageViewSearch8);
        imgView9 = (ImageView) v.findViewById(R.id.imageViewSearch9);

        imgView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 1", Toast.LENGTH_SHORT).show();
            }
        });

        imgView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 2", Toast.LENGTH_SHORT).show();
            }
        });

        imgView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 3", Toast.LENGTH_SHORT).show();
            }
        });
        imgView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 4", Toast.LENGTH_SHORT).show();
            }
        });
        imgView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 5", Toast.LENGTH_SHORT).show();
            }
        });
        imgView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 6", Toast.LENGTH_SHORT).show();
            }
        });
        imgView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 7", Toast.LENGTH_SHORT).show();
            }
        });
        imgView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 8", Toast.LENGTH_SHORT).show();
            }
        });
        imgView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Selected: Image 9", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onQuery(View v) {
        searchViewDiscover = (SearchView) v.findViewById(R.id.searchViewDIscover);
        searchViewDiscover.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String queryString = query;
                Toast.makeText(getActivity(), "Searching: " + queryString, Toast.LENGTH_SHORT).show();
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
