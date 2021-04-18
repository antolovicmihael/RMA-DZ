package com.example.fan_app_calisthenics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // For list view
    int[] images = {R.drawable.lazar_novovic, R.drawable.chris_heria, R.drawable.michael_vasquez};
    String[] name = {"Lazar Novović", "Chris Heria", "Michael Vasquez"};
    String[] description = {"Born in Serbia in 1991, Lazar Novovic has been building and maintaining his body through calisthenics for years.\n" +
            "\n", "Born Christian D. Heria on the 21st December 1991 in Miami, Florida USA of Filipino ancestry.\n" +
            "\n", "Michael Vazquez is a sponsored athlete, fitness model, breakdancer, and online coach based in Inglewood, California.\n" +
            "\n"};
    String[] quotes = {"The more you sweat in training, the less you bleed in combat.","The pain of discipline is " +
            "nothing like the pain of disappointment.", "Continuous effort -- not strength or intelligence -- is the key to unlocking our potential."};
    ListView lView;
    ListAdapter lAdapter;

    // For activities
    private Button nav1,nav2,nav3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For List View
        lView = findViewById(R.id.androidList);
        lAdapter = new ListAdapter(MainActivity.this, name, description, images);
        lView.setAdapter(lAdapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, quotes[i], Toast.LENGTH_LONG).show();

            }
        });

        nav1 =  findViewById(R.id.nav1);
        nav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        nav2 =  findViewById(R.id.nav2);
        nav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        nav3 =  findViewById(R.id.nav3);
        nav3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }


    public void openActivity1() {
        Intent intent = new Intent(this, Activity1.class);
        startActivity(intent);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }
}
