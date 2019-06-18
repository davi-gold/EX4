package com.example.ex4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class Joystick extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener
{
    private final int ratio = 5;
    private float x, y, r, headR;
    private JoystickListener joystickL;

    public Joystick(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener)
            joystickL = (JoystickListener) context;
    }

    public Joystick(Context context, AttributeSet att, int style)
    {
        super(context, att, style);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener)
            joystickL = (JoystickListener) context;
    }

    public Joystick(Context context, AttributeSet att)
    {
        super(context, att);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if(context instanceof JoystickListener)
            joystickL = (JoystickListener) context;
    }

    private void drawJoystick(float horizontal, float vertical)
    {
        if(getHolder().getSurface().isValid())
        {
            Canvas c = this.getHolder().lockCanvas();
            Paint col = new Paint();
            c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            c.drawARGB(255, 83, 139, 204);
            
            float h = (float) Math.sqrt(Math.pow(horizontal - x, 2) + Math.pow(vertical - y, 2));
            float sin, cos;
            sin = (vertical - y) / h;
            cos = (horizontal - x) / h;

            col.setARGB(255, 211, 211, 211);
            c.drawCircle(x, y, r, col);
                col.setARGB(255, 135, 206, 250);
                c.drawCircle(horizontal - cos * h * (ratio/r),vertical - sin * h * (ratio/r),  (headR * ratio / r), col);

                col.setARGB(255, 35, 167, 71);
                c.drawCircle(horizontal, vertical, (float)((headR - (ratio)/2)/1.5) , col);

            getHolder().unlockCanvasAndPost(c);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        setDimensions();
        drawJoystick(x, y);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public boolean onTouch(View v, MotionEvent e)
    {
        if(v.equals(this))
        {
            if(e.getAction() != e.ACTION_UP)
            {
                float displacement = (float) Math.sqrt((Math.pow(e.getX() - x, 2)) + Math.pow(e.getY() - y, 2));
                if(displacement < r)
                {
                    drawJoystick(e.getX(), e.getY());
                    joystickL.onJoystick((e.getX() - x)/r, (e.getY() - y)/r);
                }
                else
                {
                    float ratio = r / displacement;
                    float cX = x + (e.getX() - x) * ratio;
                    float cY = y + (e.getY() - y) * ratio;
                    drawJoystick(cX, cY);
                    joystickL.onJoystick((cX-x)/r, (cY-y)/r);
                }
            }
            else
                drawJoystick(x, y);
        }
        return true;
    }

    private void setDimensions()
    {
        x = getWidth() / 2;
        y = getHeight() / 2;
        r = Math.min(getWidth(), getHeight()) / 3;
        headR = Math.min(getWidth(), getHeight()) / 5;
    }

    public interface JoystickListener
    {
        void onJoystick(float xP, float yP);
    }
}