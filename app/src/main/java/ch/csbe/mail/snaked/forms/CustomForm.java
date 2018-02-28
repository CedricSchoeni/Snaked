package ch.csbe.mail.snaked.forms;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Cedric on 26.02.2018.
 */
public interface CustomForm {

    void drawObject(Canvas c);


    int getxPos();
    void setxPos(int xPos);
    int getyPos();
    void setyPos(int yPos);
    int getColor();
    void setColor(int c);
    void setxField(int xField);
    int getxField();
    void setyField(int yField);
    int getyField();


}
