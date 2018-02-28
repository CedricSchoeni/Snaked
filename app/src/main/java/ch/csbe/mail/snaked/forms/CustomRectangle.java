package ch.csbe.mail.snaked.forms;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 * Created by Cedric on 26.02.2018.
 */

public class CustomRectangle implements CustomForm {



    private int xPos;
    private int yPos;

    private int xField;
    private int yField;

    private int color;

    private int width;
    private int height;


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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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


    @Override
    public void drawObject(Canvas c) {
        Paint p = new Paint();
        Color co = new Color();
        p.setColor(color);
        Rect r = new Rect(xPos, yPos, xPos + width, yPos + height);
        c.drawRect(r, p);
    }

    public CustomRectangle(int xPos, int yPos, int xField, int yField, int color, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xField = xField;
        this.yField = yField;
        this.color = color;
        this.width = width;
        this.height = height;
    }
}
