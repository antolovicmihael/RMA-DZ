package mihaelantolovic.ferit.lv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private Button btnPlay;
    private Button btnRules;
    private Button btnScores;
    private Intent intent;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
    }

    public void initializeUI(){
        this.btnPlay    = (Button) findViewById(R.id.btnPlayGame);
        this.btnRules   = (Button) findViewById(R.id.btnRules);
        this.btnScores  = (Button) findViewById(R.id.btnHighScores);

        btnPlay.setOnClickListener(this);
        btnRules.setOnClickListener(this);
        btnScores.setOnClickListener(this);

        editor = getSharedPreferences("players", MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnPlayGame:
                intent = new Intent(this, GuessNumber.class);
                startActivity(intent);
                break;

            case R.id.btnRules:
                intent = new Intent(this, RulesActivity.class);
                startActivity(intent);
                break;

            case R.id.btnHighScores:
                intent = new Intent(this, HighScore.class);
                startActivity(intent);
                break;
        }
    }
}