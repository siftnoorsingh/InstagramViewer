package com.project.gagan.instagram_gagan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gagan on 29-Sep-15.
 *
 * This is the Login tab for user login
 */
public class LoginTab extends Fragment {
    //Fragment tab for login
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_tab,container,false);

        return v;
    }
}
