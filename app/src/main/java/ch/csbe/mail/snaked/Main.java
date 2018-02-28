package ch.csbe.mail.snaked;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import ch.csbe.mail.snaked.views.SnakeView;

import static android.support.v4.content.ContextCompat.startActivity;

public class Main extends AppCompatActivity {

    SnakeView snakeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        /*
        setContentView(R.layout.activity_main);

        Point p = new Point();

        getWindowManager().getDefaultDisplay().getSize(p);

        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(p.x-16, p.y-86);
        layoutParams.setMargins(8, 150, 8, 8);


        LinearLayout sc = findViewById(R.id.linearlayout);

        snakeView = new SnakeView(sc.getContext());

        snakeView.setLayoutParams(layoutParams);


        sc.addView(snakeView);


        //startGame();*/

        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);

        Point p = new Point();

        getWindowManager().getDefaultDisplay().getSize(p);

        android.support.v7.widget.GridLayout sc = findViewById(R.id.gridlayout);

        SnakeView g = new SnakeView(sc.getContext(), textView, this);

        sc.addView(g);

        g.startGame();


    }

    public void mainMenu(){
        startActivity(new Intent(Main.this, MainMenu.class));
    }
}
