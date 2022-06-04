package com.example.lostfoundmap.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lostfoundmap.model.LostFoundMod;
import com.example.lostfoundmap.ulti.Ulti;

import java.util.ArrayList;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {

    public databaseHelper(@Nullable Context context) {
        super(context.getApplicationContext(), Ulti.DATABASE_NAME, null, Ulti.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOST_FOUND_TABLE = "CREATE TABLE " + Ulti.TABLE_NAME + " ("
                + Ulti.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Ulti.TYPE + " TEXT, "
                + Ulti.NAME + " TEXT, " + Ulti.PHONE + " TEXT, "
                + Ulti.DESCRIPTION + " TEXT, " + Ulti.DATE + " TEXT, "
                + Ulti.LOCATION + " TEXT, " + Ulti.LATITUDE + " REAL, " + Ulti.LONGITUDE + " REAL )";
        db.execSQL(CREATE_LOST_FOUND_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_USER_TABLE = "DROP TABLE IF EXISTS USERS";
        db.execSQL(DROP_USER_TABLE, new String[]{Ulti.TABLE_NAME});

        onCreate(db);
    }

    public long insertLostFound(@NonNull LostFoundMod lostFound)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Ulti.TYPE, lostFound.getType());
        contentValues.put(Ulti.NAME, lostFound.getName());
        contentValues.put(Ulti.PHONE, lostFound.getPhone());
        contentValues.put(Ulti.DESCRIPTION, lostFound.getDescription());
        contentValues.put(Ulti.DATE, lostFound.getDate());
        contentValues.put(Ulti.LOCATION, lostFound.getLocation());
        contentValues.put(Ulti.LATITUDE, lostFound.getLatitude());
        contentValues.put(Ulti.LONGITUDE, lostFound.getLongitude());
        long newRowId = db.insert(Ulti.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    public ArrayList<LostFoundMod> fetchAllItems()
    {
        ArrayList<LostFoundMod> LostFoundList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = "SELECT * FROM " + Ulti.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst())
        {
            do {
                LostFoundMod lostFound = new LostFoundMod();

                lostFound.setId(cursor.getInt(0));
                lostFound.setType((cursor.getString(1)));
                lostFound.setName((cursor.getString(2)));
                lostFound.setPhone((cursor.getString(3)));
                lostFound.setDescription((cursor.getString(4)));
                lostFound.setDate((cursor.getString(5)));
                lostFound.setLocation((cursor.getString(6)));
                lostFound.setLatitude((cursor.getDouble(7)));
                lostFound.setLongitude((cursor.getDouble(8)));

                LostFoundList.add(lostFound);
            }
            while (cursor.moveToNext());
        }
        return LostFoundList;
    }

//    public ArrayList<LostFoundMod> fetchAllItems()
//    {
//        ArrayList<LostFoundMod> LostFoundList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + Ulti.TABLE_NAME, null);
//try {
//    if (cursor.moveToFirst()) {
//        do {
//            LostFoundList.add(new LostFoundMod(
//                    cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    cursor.getString(4),
//                    cursor.getString(5),
//                    cursor.getString(6),
//                    cursor.getDouble(7),
//                    cursor.getDouble(8)
//            ));
//        }
//        while (cursor.moveToNext());
//    }
//}
//catch (Exception e){e.printStackTrace();}
//finally {
//    db.close();
//}
//        if (LostFoundList.size() == 0)
//            Log.d(null, "list is null");
//        else
//            Log.d(null, "list is not null");
//        return LostFoundList;
//    }

    public void deleteData (String name, String phone, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL( " DELETE FROM " + Ulti.TABLE_NAME + " WHERE " + Ulti.NAME + "= '" + name + "' AND "
                + Ulti.PHONE + " = '" + phone + "' AND " + Ulti.DATE + "= '" + date + "'");
    }
}


