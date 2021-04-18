package mihaelantolovic.ferit.lv4;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class HighScore extends AppCompatActivity {
    private DataBase db;
    private ArrayList<Player> players;
    private RecyclerView rvPlayerStats;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration mItemDecoration;
    private PlayerAdapter mPlayerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score);
        initializeUI();
    }

    public void initializeUI(){
        Context context = getApplicationContext();
        this.rvPlayerStats = (RecyclerView) findViewById(R.id.rvPlayerStats);
        this.db = new DataBase(this,"playersDB.db",null,1);
        this.mLayoutManager = new LinearLayoutManager(context);
        this.mItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        this.mPlayerAdapter = new PlayerAdapter(db.loadHandler());

        this.rvPlayerStats.addItemDecoration(this.mItemDecoration);
        this.rvPlayerStats.setLayoutManager(this.mLayoutManager);
        this.rvPlayerStats.setAdapter(this.mPlayerAdapter);
    }
}