package com.example.ex4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickConnect(View view) {
        EditText editTextIp = findViewById(R.id.ip_edit_text);
        EditText editTextPort = findViewById(R.id.port_edit_text);

        String ip = editTextIp.getText().toString();
        String port = editTextPort.getText().toString();

        connectToServer(ip, port);
    }

    private void connectToServer(String ip, String port){



    }

}
