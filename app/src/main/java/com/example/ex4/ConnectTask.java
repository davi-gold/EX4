package com.example.ex4;


import android.os.AsyncTask;
import android.util.Log;

public class ConnectTask extends AsyncTask<String, String, TcpClient> {

    TcpClient mTcpClient;
    public ConnectTask(TcpClient client){
        mTcpClient = client;
    }

    @Override
    protected TcpClient doInBackground(String... message) {

        mTcpClient.run();

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        //response received from server
        Log.d("test", "response " + values[0]);
        //process server response here....

    }
}
