package com.example.parkingsharking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {



    public DatabaseHelper(Context context) {
        super(context, "databaza.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text)");
        db.execSQL("Create table city(cityname text primary key, citynumber int)");
        db.execSQL("Create table park(parkName text primary key, cityNamee text,  parkSpaces int, takenSpaces int, lat double, long double)");

        db.execSQL("Create table reservation(rowid int primary key, userR text, cityR text, parkR text, dateR text, timeR text )");

        DataCity(db);
        DataParking(db);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists city");
        db.execSQL("drop table if exists park");
        db.execSQL("drop table if exists reservation");
        onCreate(db);

    }

    public boolean insertReservation(String user, String city, String park, String date, String time){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userR", user);
        contentValues.put("cityR", city);
        contentValues.put("parkR", park);
        contentValues.put("dateR", date);
        contentValues.put("timeR", time);
        long ins = db.insert("reservation", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }

    public void DataCity(SQLiteDatabase db) {

        String[] cities = {"Winterfell", "King's Landing", "Pyke", "Riverrun", "Probistip", "Casterly Rock", "Eyrie", "Highgarden", "Sunspear", "Storm's End"};
        int[] numbers = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        ContentValues values = new ContentValues();

        for (int i=0; i < cities.length;i++) {
            values.put("cityname", cities[i]);
            values.put("citynumber", numbers[i]);
            db.insert("city", null, values);
        }
    }

    private void DataParking(SQLiteDatabase db) {
        String[] parkingLots = {"Lot 1","Lot 2","Lot 3","Lot 4","Lot 5","Lot 6","Lot 7","Lot 8","Lot 9"
        ,"Lot 10"};

        String[] cityP= {"Winterfell", "King's Landing", "Pyke", "Riverrun", "Probistip", "Casterly Rock", "Eyrie", "Highgarden", "Sunspear", "Storm's End"};

        double[] latitude = {42.005864, 41.994514, 41.993442, 41.985663 , 41.996776,
                42.128971, 42.135614,
                41.718129, 41.713903,
                41.737259, 41.741124,
                41.143215,
                41.439633,
                41.343759, 41.344044,
                41.026927, 41.030836,
                42.007646
        };
        double[] longitude = {21.392932, 21.437454, 21.422464, 21.465201, 21.437004,
                21.718501, 21.728963,
                21.772912, 21.785763,
                22.190368, 22.189238,
                22.512149,
                22.639943,
                21.551713, 21.540177,
                21.333055, 21.334509,
                20.798746
        };

        ContentValues values = new ContentValues();

        for (int i=0; i < parkingLots.length;i++) {

            values.put("parkName", parkingLots[i]);
            values.put("cityNamee", cityP[i]);
            values.put("parkSpaces", 50);
            values.put("takenSpaces", 0);
            db.insert("park", null, values);
        }
    }

    public boolean insert_1(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }


    public City query(int position) {
        String query = "SELECT * FROM city ORDER BY cityname ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        City entry = new City();

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        entry.setName(cursor.getString(cursor.getColumnIndex("cityname")));
        entry.setParkings(cursor.getInt(cursor.getColumnIndex("citynumber")));
        cursor.close();
        return entry;

    }



    public Parking querypark(int position) {

        Cursor cursor;
        Parking entry = new Parking();

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery( "Select * from park order by parkName asc limit " + position + ",1", null);
        cursor.moveToFirst();

        entry.setName(cursor.getString(cursor.getColumnIndex("parkName")));
        entry.setCity(cursor.getString(cursor.getColumnIndex("cityNamee")));
        entry.setSlots(cursor.getInt(cursor.getColumnIndex("parkSpaces")));
        entry.setSlotsTaken(cursor.getInt(cursor.getColumnIndex("takenSpaces")));

        cursor.close();

        return entry;

    }



    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "city");
    }

    public long countpark(){
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(this.getReadableDatabase(), "park");
    }

    public Boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else return true;
    }

    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public int numberResPerUser(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from reservation where userR=?", new String[]{user});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getCount();
            cursor.close();
            return count;
        } else return 0;
    }

    public int numberResAtDateTime(String date, String time, String park){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from reservation where dateR=? and timeR=? and parkR=?", new String[]{date, time, park});
        int number = 0;
        if(cursor.moveToFirst()){
            number = cursor.getCount();
            cursor.close();
            return number;
        }else return 0;
    }

    public double latitude (String park){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from park where parkName=?", new String[]{park});
        if(cursor.moveToFirst()){
            return cursor.getDouble(4);
        }else return 0;
    }

    public double longitude (String park){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from park where parkName=?", new String[]{park});
        if(cursor.moveToFirst()){
            return cursor.getDouble(5);
        }else return 0;
    }



}