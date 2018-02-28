package ch.csbe.mail.snaked;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import ch.csbe.mail.snaked.views.SnakeView;

public class Main extends AppCompatActivity {

    SnakeView snakeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        Point p = new Point();

        getWindowManager().getDefaultDisplay().getSize(p);

        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(p.x-16, p.y-86);
        layoutParams.setMargins(8, 150, 8, 8);


        LinearLayout sc = findViewById(R.id.linearlayout);

        snakeView = new SnakeView(sc.getContext());

        snakeView.setLayoutParams(layoutParams);


        sc.addView(snakeView);


        //startGame();


    }

    public void startGame(){
        //ScrollView view =  findViewById(R.id.canvas);

        //initView(new SnakeView(view.getContext()));
        initView(new SnakeView(this));
        snakeView.startGame();
    }

    private void initView(SnakeView sv){
        snakeView = sv;
        setContentView(snakeView);
    }
}
