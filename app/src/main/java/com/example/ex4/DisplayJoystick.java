package com.example.ex4;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class DisplayJoystick extends AppCompatActivity implements JoystickView.JoystickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JoystickView joystick = new JoystickView(this);
        setContentView(R.layout.joystick);
    }

    @Override
    public void onJoystickMoved(float xPercent, float yPercent) {
                Log.d("Joystick", "X percent: " + xPercent + " Y percent: " + yPercent);
                TcpClient.getInstance().sendMessage("set /controls/flight/elevator " + yPercent);
                TcpClient.getInstance().sendMessage("set /controls/flight/aileron " + xPercent);
        }
    }
