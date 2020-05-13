package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String BROADCAST_ACTION = null;
    Button button;
    TextView txtViewMsg;
    private Receiver localListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            button = findViewById(R.id.button);
            txtViewMsg = findViewById(R.id.txtViewMsg);
        }

        @Override
        protected void onStart() {
            super.onStart();
            localListener = new Receiver();
            IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
            this.registerReceiver(localListener,intentFilter);
        }

        private void registerReceiver(Receiver localListener) {
        }

        @Override
        protected void onStop() {
            super.onStop();
            this.unregisterReceiver(localListener);
        }

        public void onClick(View view){
            if(view.getId() == R.id.button){
                BackgroundServices.startAction(this.getApplicationContext());
            }
        }

        public class Receiver extends BroadcastReceiver {
            @Override
            public void onReceive(Context context, Intent intent) {
                String currentText = txtViewMsg.getText().toString();
                String message = intent.getStringExtra("value");
                currentText += "\nReceived " + message;
                txtViewMsg.setText(currentText);
            }
        }
    }





