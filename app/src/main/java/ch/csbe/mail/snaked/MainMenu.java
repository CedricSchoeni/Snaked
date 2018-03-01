package ch.csbe.mail.snaked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ch.csbe.mail.snaked.values.Score;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button play = (Button) findViewById(R.id.BtnPlay);
        play.setOnClickListener((view) ->{
            Intent intent = new Intent(MainMenu.this,Main.class);
            startActivity(intent);
        });
        this.setLastScore(Score.score);
    }
    private void setLastScore(int score){
        TextView textView = findViewById(R.id.lastScore);
        textView.setText("Last Score: "+score);
    }

}
