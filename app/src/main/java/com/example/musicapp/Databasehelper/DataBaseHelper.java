package com.example.musicapp.Databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="FavoriteRecord.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME1 ="my_favorite";
    private static final String DATABASE_THEME_ID ="_id3";
    private static final String THEME_TITLE3 ="theme_title";
    private static final String TABLE_NAME1 ="my_favorite1";
    private static final String THEME_TIME ="theme_time";
    private static final String THEME_IMAGE ="theme_image";
    private static final String UNIQUE_ID ="unique_id";
    private Context context;

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String query3 = "CREATE TABLE " + TABLE_NAME1 + " (" + DATABASE_THEME_ID + " STRING PRIMARY KEY, "
            + THEME_TITLE3 + " TEXT, " +
                THEME_TIME + " TEXT, " +
            THEME_IMAGE + " TEXT, " +
            UNIQUE_ID + " TEXT);";
    sqLiteDatabase.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
    }

    public long addscanRecord3(String unique_id, String title, int image_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DATABASE_THEME_ID,UNIQUE_ID);
        cv.put(THEME_TITLE3,title);
        cv.put(THEME_IMAGE,image_id);

        long result = db.insert(TABLE_NAME1,null,cv);
        if (result == -1){
            Toast.makeText(context, "faild", Toast.LENGTH_SHORT).show();
        }
        else {

        }
        db.close();
        return result;
    }

    public Cursor readAllData3(){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_NAME1;
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(Query ,null);
        }
        return cursor;
    }

    public void deleteItem(String get_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1+ "WHERE "+DATABASE_THEME_ID+"='"+get_id+"'");
    }
}
