package com.softwaremakeinindia.iotcexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.softwaremakeinindia.iotc.Iotc;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] devicesToSubscribe = {"device"};
        Iotc.connect(MainActivity.this, "key", devicesToSubscribe, new Iotc.OnConnect() {
            private String TAG;

            @Override
            public void onMessageReceive(String deviceId, String msg) {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onMessageReceive: message"+ msg);
            }
        });
    }
}
