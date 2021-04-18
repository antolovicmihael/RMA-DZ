package mihaelantolovic.ferit.lv4;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GuessNumber extends AppCompatActivity implements Button.OnClickListener, TextView.OnEditorActionListener {
    private Button btnGuess;
    private EditText userInput;
    private TextView gameDetail;
    private Random random;
    private int number;
    private int num_guesses;
    private String playername;
    private AlertDialog.Builder alert;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private Intent intent;
    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        db = new DataBase(this, null, null, 1);
        initializeUI();
    }


    public void initializeUI(){
        this.btnGuess   = (Button) findViewById(R.id.btnGuess);
        this.userInput  = (EditText) findViewById(R.id.edUserGuess);
        this.gameDetail = (TextView) findViewById(R.id.tvGameDetailTitle);
        this.random = new Random();
        alert = new AlertDialog.Builder(this);

        userInput.setOnEditorActionListener(this);
        btnGuess.setOnClickListener(this);

        configureDialog();
        alert.create().show();
        newGame();
    }

    @Override
    public void onClick(View view) {
        int guess = Integer.parseInt(userInput.getText().toString());
        validate(guess);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)){
            btnGuess.performClick();
        }
        return false;
    }

    public void newGame(){
        number = random.nextInt(100) + 1;
        gameDetail.setText(R.string.game_detail_title);
        num_guesses = 1;
    }

    public void validate(int num){
        if(number > num){
            gameDetail.setText(R.string.num_lower);
        } else if (number < num){
            gameDetail.setText(R.string.num_higher);
        } else{
            Toast.makeText(this, "Congratulations ! You found the number in " + num_guesses + " tries", Toast.LENGTH_SHORT).show();
            storeNumTries();
            savetoDB();
            newGame();
        }

        num_guesses++;
        userInput.setText("");
    }

    public void configureDialog(){
        final String reg = "^[a-zA-Z0-9]*$";
        alert.setTitle("Who is playing?");
        final EditText edPlayerName = new EditText(this);

        edPlayerName.setText("Enter player name");
        alert.setView(edPlayerName);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                playername = edPlayerName.getText().toString();
                if(playername.matches(reg) && !playername.isEmpty()){
                    storePlayername();
                } else {
                    Toast.makeText(GuessNumber.this, "Invalid username", Toast.LENGTH_LONG).show();
                    intent = new Intent(GuessNumber.this, GuessNumber.class);
                    startActivity(intent);
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                intent = new Intent(GuessNumber.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void savetoDB(){
        prefs = getSharedPreferences("players", MODE_PRIVATE);
        String name = prefs.getString("playername", "DEFAULT");
        int tries = prefs.getInt("tries", 0);
        db.addHandler(new Player(name, tries));
    }

    public void storePlayername(){
        editor = getSharedPreferences("players", MODE_PRIVATE).edit();
        editor.putString("playername", playername);
        editor.apply();
    }

    public void storeNumTries(){
        editor = getSharedPreferences("players", MODE_PRIVATE).edit();
        editor.putInt("tries", num_guesses);
        editor.apply();
    }
}
