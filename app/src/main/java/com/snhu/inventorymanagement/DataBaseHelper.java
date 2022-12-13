package com.snhu.inventorymanagement;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {


    // to make my use of the table easier
    public static final String INVENTORY_TABLE = "INVENTORY_TABLE";
    public static final String INVENTORY_ITEM = "INVENTORY_ITEM";
    public static final String INVENTORY_QTY = "INVENTORY_QTY";
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_ID = "UserID";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";




    // constructor for new class
    public DataBaseHelper(Context context) {

        super(context, "data.db", null, 1);
        getWritableDatabase();  // added 12 dec
    }

    //called the first time a DB is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + INVENTORY_TABLE + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + INVENTORY_ITEM + " TEXT, "
                + INVENTORY_QTY + " INT )";

        String createUserTable = "CREATE TABLE " + USER_TABLE + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAME + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT )";


        db.execSQL(createUserTable);
        db.execSQL(createTableStatement);

    }

    // called for backwards compatibility
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop TABLE if exists " + INVENTORY_TABLE);
        db.execSQL("drop TABLE if exists " + USER_TABLE);


    }

    //add user to database
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, username);
        cv.put(COLUMN_USER_PASSWORD,password);


        long insert = db.insert(USER_TABLE, null, cv);
        return insert != -1;
    }

    public boolean checkDBforUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE USER_NAME = ?" , new String[]{username});
        if (cursor.getCount()>0) {
            cursor.close();
            return true;
        } else {

            return false;
        }
    }
        // adding an item and quantity to the DB
    public boolean addOne(ItemAttributes itemAtttributes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(INVENTORY_ITEM, itemAtttributes.getName());
        cv.put(INVENTORY_QTY, itemAtttributes.getQuantity());

        long insert = db.insert(INVENTORY_TABLE, null, cv);
        return insert != -1;
    }
    public void deleteOne(ItemAttributes itemAttributes) {
        //find itemAttributes in the database/ if found delete and return true
        //if not return false     Ran out of time to implement this function properly
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + INVENTORY_TABLE + " WHERE " + COLUMN_ID + " = " + itemAttributes.getId();

        Cursor cursor = db.rawQuery(queryString, null);
       if (cursor.moveToFirst()) {
        cursor.close();
        db.close();
       }
       else {
       }
        cursor.close();
        db.close();

    }

    // pull everything out of my DB
    public List<ItemAttributes> getEverything() {

        List<ItemAttributes> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + INVENTORY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor and create new customer objects into the return list
            do {
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                int itemQuantity = cursor.getInt(2);

                ItemAttributes newItems = new ItemAttributes(itemID, itemName, itemQuantity);
                returnList.add(newItems);
            } while (cursor.moveToNext());
        } else {
            // do not add anything
            cursor.close();
            db.close();
        }
        cursor.close();
        db.close();


        return returnList;


    }
}

