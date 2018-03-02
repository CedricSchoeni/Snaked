package ch.csbe.mail.snaked.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.csbe.mail.snaked.Main;
import ch.csbe.mail.snaked.MainMenu;
import ch.csbe.mail.snaked.R;
import ch.csbe.mail.snaked.forms.CustomCircle;
import ch.csbe.mail.snaked.forms.CustomForm;
import ch.csbe.mail.snaked.forms.CustomRectangle;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Cedric on 26.02.2018.
 */

public class SnakeView extends View {

    private int devWidth;
    private int devHeight;

    private int xFields = 20;
    private int yFields;

    private int fieldWidth;
    private int fieldHeight;

    private TextView textView;

    private int score = 0;
    private Main main;
    private Context context;

    private int movementDirection = 1;

    private List<CustomForm> renderList;
    private List<CustomForm> snakeBody;
    private List<CustomForm> fruits;

    Handler handler = new Handler();

    public SnakeView(Context context, TextView textView, Main main) {
        super(context);


        renderList = new ArrayList<CustomForm>();
        snakeBody = new ArrayList<CustomForm>();
        fruits = new ArrayList<CustomForm>();

        this.textView = textView;
        this.main = main;
        this.context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        updateSnake();
        // Draw all parts in the renderList
        for (CustomForm form : renderList){
            form.drawObject(canvas);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //prev in snakeview()



        DisplayMetrics metrics = this.getResources().getDisplayMetrics();


        devWidth = getWidth();
        devHeight =  getHeight();

        Log.e("gridH", Integer.toString(devHeight));
        Log.e("gridW", Integer.toString(devWidth));
        fieldWidth = devWidth / xFields;
        fieldHeight = fieldWidth;
        yFields = devHeight / fieldHeight;

        fieldWidth = devWidth / xFields;
        fieldHeight = devHeight / yFields;

        this.setOnTouchListener(handleTouch);
        this.startGame();
    }

    public void startGame(){

        // start Handler
        handler.post(periodicUpdate);

        // create snake parts
        CustomForm head = new CustomRectangle((fieldWidth * 2), (fieldHeight * 2), 2, 2, Color.LTGRAY, fieldWidth, fieldHeight);
        renderList.add(head);
        snakeBody.add(head);

        CustomForm body1 = new CustomRectangle((fieldWidth * 1), (fieldHeight * 2), 1, 2, Color.GREEN, fieldWidth, fieldHeight);
        renderList.add(body1);
        snakeBody.add(body1);

        CustomForm body2 = new CustomRectangle((fieldWidth * 0), (fieldHeight * 2),0, 2, Color.GREEN, fieldWidth, fieldHeight);
        renderList.add(body2);
        snakeBody.add(body2);
    }

    private int slideStartX;
    private int slideStartY;

    private int slideStopX;
    private int slideStopY;

    private View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //Log.i("TAG", "touched down");
                    slideStartX = (int) event.getX();
                    slideStartY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //Log.i("TAG", "moving: (" + x + ", " + y + ")");
                    break;
                case MotionEvent.ACTION_UP:
                    //Log.i("TAG", "touched up");
                    slideStopX = (int) event.getX();
                    slideStopY = (int) event.getY();
                    int dir = calcSlideDir(slideStartX, slideStartY, slideStopX, slideStopY);
                    movementDirection = (dir >= 0) ? dir : movementDirection;
                    break;
            }
            return true;
        }
    };

    private int calcSlideDir(int xStart, int yStart, int xEnd, int yEnd){
        int dir = -1;

        int xDiff = xStart - xEnd;
        int yDiff = yStart - yEnd;


        if (Math.abs(xDiff) > Math.abs(yDiff)){
            if (xDiff < -50 && movementDirection!=3)
                dir = 1;
            if (xDiff > 50 && movementDirection!=1)
                dir = 3;
        } else {
            if (yDiff > 50 && movementDirection!=2)
                dir = 0;
            if (yDiff < -50 && movementDirection!=0)
                dir = 2;
        }

/*
        Log.e("xDiff", Integer.toString(xDiff));
        Log.e("yDiff", Integer.toString(yDiff));
        Log.e("DIR", Integer.toString(dir));
*/


        return dir;
    }

    private void updateSnake(){
        CustomForm cf = snakeBody.get(0);

        int newHeadX = 0;
        int newHeadY = 0;

        if (movementDirection == 0 || movementDirection == 2){
            int yAdd = (movementDirection == 0) ? -1 : 1;
            newHeadX = snakeBody.get(0).getxField();
            newHeadY = snakeBody.get(0).getyField() + yAdd;


        } else if (movementDirection == 1 || movementDirection == 3){
            int xAdd = (movementDirection == 1) ? 1 : -1;
            newHeadX = snakeBody.get(0).getxField() + xAdd;
            newHeadY = snakeBody.get(0).getyField();
        }



        checkForCollision(newHeadX, newHeadY);

        for (int i = snakeBody.size() - 1; i > 0; i--){
            //Log.e(Integer.toString(i), Integer.toString(snakeBody.get(i).getxField()));

            snakeBody.get(i).setxField(snakeBody.get(i-1).getxField());
            snakeBody.get(i).setyField(snakeBody.get(i-1).getyField());


            snakeBody.get(i).setxPos(snakeBody.get(i).getxField() * fieldWidth);
            snakeBody.get(i).setyPos(snakeBody.get(i).getyField() * fieldHeight);
        }

        snakeBody.get(0).setxField(newHeadX);
        snakeBody.get(0).setyField(newHeadY);
        snakeBody.get(0).setyPos(snakeBody.get(0).getyField() * fieldHeight);
        snakeBody.get(0).setxPos(snakeBody.get(0).getxField() * fieldWidth);




    }


    private void checkForCollision(int x, int y){
        if(x<xFields && x>=0 && y<yFields && y>=0){
            boolean isRoiTheBoi = false;
            for (CustomForm piece : snakeBody){
                if(x==piece.getxField() && y==piece.getyField())
                    isRoiTheBoi = true;
            }


            boolean isRoiSnaked = false;
            CustomForm RoiFruit = null;
            for (CustomForm piece2 : fruits){
                if(x==piece2.getxField() && y==piece2.getyField()){
                    isRoiSnaked = true;
                    RoiFruit=piece2;
                    renderList.remove(piece2);
                }
            }

            if (isRoiSnaked){
                fruits.remove(RoiFruit);
                growSnake();
            }
            if (!isRoiTheBoi) return;
        }
        gameOver();


    }

    private void gameOver(){
        handler.removeCallbacksAndMessages(null);
        main.mainMenu(score);
    }

    private void growSnake(){
        //Log.e("Grew:","");

        textView.setText("Score: " + Integer.toString(++score));
        CustomForm cf = snakeBody.get(snakeBody.size() - 1);
        CustomForm body = new CustomRectangle((fieldWidth * cf.getxField()), (fieldHeight * cf.getyField()),cf.getxField(), cf.getyField(), Color.GREEN, fieldWidth, fieldHeight);
        renderList.add(body);
        snakeBody.add(body);
    }

    private int fruitDelay = 39;
    private int fruitCounter = 0;


    private Runnable periodicUpdate = new Runnable () {
        @Override
        public void run() {
            // scheduled another events to be in x milli-seconds later
            int timer=150;
            boolean fasterOverTime=false;
            int tmp=5*score;
            if(timer-score>=50 && fasterOverTime)
                handler.postDelayed(periodicUpdate, (timer-score*5));
            else
                handler.postDelayed(periodicUpdate, timer);
            //handler.postDelayed(periodicUpdate, timer);
            // below is whatever you want to do
            invalidate();
            if (fruitCounter++ == fruitDelay){
                spawnFruit();
                fruitCounter = 0;
            }
        }
    };

    private void spawnFruit(){
        int x=0,y=0;
        int skip = 150;
        int counter = 0;
        while(true){
            x= (int) (Math.random() * (xFields - 0)) + 0;
            y= (int) (Math.random() * (yFields - 0)) + 0;
            if (++counter > skip){
                return;
            }
            boolean onSnake = false;
            for (CustomForm piece : snakeBody){
                if(x==piece.getxField() && y==piece.getyField())
                    onSnake = true;
            }


            boolean onFruit = false;
            for (CustomForm piece : fruits){
                if(x==piece.getxField() && y==piece.getyField())
                    onFruit = true;
            }
            if (!onSnake && !onFruit) break;

        }

        CustomForm fruit = new CustomRectangle((fieldWidth * x), (fieldHeight * y),x, y, Color.RED, fieldWidth, fieldHeight);
        renderList.add(fruit);
        fruits.add(fruit);
    }



}
