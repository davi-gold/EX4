package com.example.ex4;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class DisplayJoystick extends AppCompatActivity implements Joystick.JoystickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joystick);
    }

    @Override
    public void onJoystick(float xP, float yP) {

                // normalization
                if (xP > 0.999)
                    xP = 1;
                if (yP > 0.999)
                    yP = 1;
                if (xP < -0.999)
                    xP = -1;
                if (yP < -0.999)
                    yP = -1;

                TcpClient.getInstance().sendMessage("set /controls/flight/elevator " + yP);
                TcpClient.getInstance().sendMessage("set /controls/flight/aileron " + xP);
        }
    }
