package mihaelantolovic.ferit.lv4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION    = 1;
    private static final String DATABASE_NAME    = "playersDB.db";
    public static final String TABLE_NAME        = "Players";
    public static final String COLUMN_ID         = "PlayerID";
    public static final String COLUMN_PLAYERNAME = "PlayerName";
    public static final String COLUMN_NUMTRIES   = "NumTries";
    private ArrayList<Player> players = new ArrayList<Player>();

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory , version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String SQL_CREATE_TABLE  = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + COLUMN_PLAYERNAME + " TEXT," + COLUMN_NUMTRIES + " INTEGER )";

        db.execSQL(SQL_CREATE_TABLE);
    };

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {};

    public ArrayList<Player> loadHandler() {
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_NUMTRIES + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
            Player player = new Player();
            player.setPlayerName(cursor.getString(1));
            player.setNumTries(cursor.getInt(2));

            players.add(player);
        }

        return players;
    };
    public void addHandler(Player player) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYERNAME, player.getPlayerName());
        values.put(COLUMN_NUMTRIES, player.getNumTries());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    };
}