package com.astrazeneca.androidtutorial.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "android";
    private static final String TABLE_PERSONS = "Person";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PERSONS_TABLE = "CREATE TABLE " + TABLE_PERSONS + "("+ KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT" + ")";
        db.execSQL(CREATE_PERSONS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);

        // Create tables again
        onCreate(db);

    }

    // code to add the new contact
    void addPerson(Person person) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, person.getFirst_name()); // Contact Name
        values.put(KEY_LAST_NAME, person.getLast_name()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_PERSONS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    // code to get all contacts in a list view
    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PERSONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();

                person.setFirst_name(cursor.getString(0));
                person.setLast_name(cursor.getString(1));
                // Adding Person to list
                personList.add(person);
            } while (cursor.moveToNext());
        }

        // return contact list
        return personList;
    }






}
