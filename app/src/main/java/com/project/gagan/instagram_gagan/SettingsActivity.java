package com.project.gagan.instagram_gagan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by Gagan on 30-Sep-15.
 */
public class SettingsActivity extends AppCompatActivity {


    private Button bluetoothButton;
    private Toolbar toolbar;
    BluetoothAdapter badapter ;
    int REQUEST_CODE =1;
    Set<BluetoothDevice> paired_devices;
    String blist[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        TextView v = (TextView)findViewById(R.id.name);
        v.setText("Bluetooth");


        // Set up the log out button click event
        bluetoothButton = (Button) findViewById(R.id.list_button);
        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                badapter = BluetoothAdapter.getDefaultAdapter();
                if(badapter==null)
                {
                    Toast.makeText(getBaseContext(),"No Bluetooth Device Found",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(!badapter.isEnabled())
                    {
                        // Start and intent for the dispatch activity
                        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent,REQUEST_CODE);

                    }
                }
                }
        });



    }


public void onActivityResult(int requestcode, int resultcode,Intent data)
{
    if(requestcode==REQUEST_CODE)
    {
        if(resultcode ==RESULT_OK)
        {
            Toast.makeText(getBaseContext(),"Bluetooth Successfully Turned On", Toast.LENGTH_LONG).show();
            paired_devices = badapter.getBondedDevices();

            int bcount = paired_devices.size();
            blist = new String[bcount];
            int i =0;

            for(BluetoothDevice device : paired_devices)
            {
                blist[i]=device.getName().toString();
                i++;
            }
            Bundle bundle = new Bundle();
            bundle.putStringArray("pairs",blist);
            Intent in = new Intent("com.project.gagan.instagram_gagan.PairingBluetooth");
            in.putExtras(bundle);
            startActivity(in) ;
        }
    }
}

}