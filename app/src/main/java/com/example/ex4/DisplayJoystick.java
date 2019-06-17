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

                if (xPercent > 0.999)
                    xPercent = 1;
                if (yPercent > 0.999)
                    yPercent = 1;
                if (xPercent < -0.999)
                    xPercent = -1;
                if (yPercent < -0.999)
                    yPercent = -1;

                Log.d("Joystick", "X percent: " + xPercent + " Y percent: " + yPercent);

                TcpClient.getInstance().sendMessage("set /controls/flight/elevator " + yPercent);
                TcpClient.getInstance().sendMessage("set /controls/flight/aileron " + xPercent);
        }
    }
