package com.example.ex4;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static java.lang.Thread.sleep;

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

        Intent intent = new Intent(this, DisplayJoystick.class);
        startActivity(intent);
    }

    public void connectToServer(String ip, String port) {
        int myPort = Integer.parseInt(port);
        TcpClient tcp_client = TcpClient.getInstance();
        tcp_client.initialize(ip, myPort);
        ConnectTask con = new ConnectTask(tcp_client);
        con.execute();
    }
}