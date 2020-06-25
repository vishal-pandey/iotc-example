package com.softwaremakeinindia.iotcexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.softwaremakeinindia.iotc.Iotc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList myList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_listview, myList);
        ListView listView = findViewById(R.id.message_list);
        listView.setAdapter(adapter);

        final EditText eText = findViewById(R.id.edittext);
        Button btn = findViewById(R.id.button);
        Button clearBtn = findViewById(R.id.clearBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = eText.getText().toString();
                Iotc.send("device",msg);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myList.clear();
                adapter.notifyDataSetChanged();
            }
        });



        String[] devicesToSubscribe = {"device"};
        Iotc.connect(this, "1128d346-371f-4cd0-b3d8-8e48591907cc", new Iotc.Options() {
            @Override
            public void onConnect(String appName, String[] devices) {
                for (int i = 0; i < devices.length; i++){
                    Iotc.subscribe(devices[i]);
                }
            }

            @Override
            public void onMessageReceive(String deviceId, String msg) {
                myList.add(msg+" from "+deviceId);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
