package ch.csbe.mail.snaked.forms;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Cedric on 26.02.2018.
 */

public class CustomCircle implements CustomForm {

    private int xPos;
    private int yPos;

    private int xField;
    private int yField;

    private int color;

    private int radius;

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public void setColor(int c) {

    }

    @Override
    public void setxField(int xField) {
        this.xField = xField;
    }

    @Override
    public int getxField() {
        return this.xField;
    }

    @Override
    public void setyField(int yField) {
        this.yField = yField;
    }

    @Override
    public int getyField() {
        return this.yField;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


    @Override
    public void drawObject(Canvas c) {
        Paint p = new Paint();
        p.setColor(color);
        c.drawCircle(xPos + radius/2, yPos + radius/2, radius, p);
    }

    public CustomCircle(int xPos, int yPos, int xField, int yField, int color, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xField = xField;
        this.yField = yField;
        this.color = color;
        this.radius = radius;
    }
}
