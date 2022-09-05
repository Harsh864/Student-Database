package com.example.database_studentrecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "Students.db";
    public static final String TABLE_NAME = "student_table";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String MARKS = "marks";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME + " TEXT, " + SURNAME + " TEXT, " + MARKS + " TEXT" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addRecords(String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(SURNAME, surname);
        values.put(MARKS, marks);
        long result = db.insert(TABLE_NAME, null, values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllDAta(){
        SQLiteDatabase db = this.getWritableDatabase();
        String data = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(data, null);
        return  cursor;
    }

    public boolean updateData(String id, String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(NAME, name);
        values.put(SURNAME, surname);
        values.put(MARKS, marks);
        db.update(TABLE_NAME, values, "id = ?", new String [] { id });
        return true;
    }

    public void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[] { id });
    }
}
