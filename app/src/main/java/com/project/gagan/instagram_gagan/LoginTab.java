package com.project.gagan.instagram_gagan;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gagan on 29-Sep-15.
 */
public class LoginTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_tab,container,false);

        /*Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);*/
        return v;
    }
}
