package com.project.gagan.instagram_gagan;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by lenovo on 14-10-2015.
 */
public class PairingBluetooth extends android.app.Activity {
    ListView listView;
    String[] pairs;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pairing_list);

        TextView v = (TextView)findViewById(R.id.name);
         v.setText("Paired Devices");
        listView = (ListView)findViewById(R.id.pair_list);
        Bundle bundle = getIntent().getExtras();
        pairs = bundle.getStringArray("pairs");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, pairs);
        listView.setAdapter(adapter);

    }
}
