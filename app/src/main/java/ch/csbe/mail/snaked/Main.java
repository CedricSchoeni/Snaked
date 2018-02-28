package ch.csbe.mail.snaked;

import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ch.csbe.mail.snaked.views.SnakeView;

public class Main extends AppCompatActivity {

    SnakeView snakeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        startGame();


    }

    public void startGame(){
        initView(new SnakeView(this));
        snakeView.startGame();
    }

    private void initView(SnakeView sv){
        snakeView = sv;
        setContentView(snakeView);
    }
}