package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.List;

public class SearchTab extends Fragment {

    private View view;

  //  public String queryString;

    private static SearchView searchViewDiscover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// search_tab
        view = inflater.inflate(R.layout.search_tab, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridViewSearch);

        gridView.setAdapter(new ImageAdapter(view.getContext()));
        onQuery(view);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ParseUser currentUser = ParseUser.getCurrentUser();
                String CurrentUserName = currentUser.getUsername();

                Toast.makeText(getActivity(), "Image " + position + " " + CurrentUserName,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //  onClickPic(view);

        return view;
    }



    public void onQuery(View v) {

//        //ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> list, ParseException e) {
//               // ParseObject user1 = (ParseObject)list.
//                Log.d(list.get(0).toString(), " Users listed");
//
//                int numberOfUsers = list.size();
//
//                for(int i = 0;i<numberOfUsers;i++) {
//                    ParseFile parseFile = user.getParseFile("thumbnail");
//                }
//
//
//
//
////                int numberOfUsers = list.size();
////
////                ParseUser [] userList = new ParseUser[numberOfUsers];
////
////                for (int i = 0; i < numberOfUsers;i++ ){
////                    userList[i] = list.get(i).getParseUser("username");
////
////
////                }
////
////                for(int i = 0;i<numberOfUsers;i++){
////                    if(userList[i]!=null) {
////                        Log.d(userList[i].getUsername(), " Users listed");
////                    }
////
////                }
//            }
//        });

        searchViewDiscover = (SearchView) v.findViewById(R.id.searchViewDIscover);
        searchViewDiscover.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String queryString = query;

                //Toast.makeText(getActivity(), "Searching: " + queryString, Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getActivity(),SearchResultActivity.class);
                intent.putExtra("Query", query);
                startActivity(intent);

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
