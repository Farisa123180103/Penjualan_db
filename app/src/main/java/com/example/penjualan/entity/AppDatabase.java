package com.example.penjualan.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataPenjualan.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DataPenjualanDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase iniDb(Context context){
        if(appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class,"dbPenjualan").allowMainThreadQueries().build();

        return appDatabase;
    }

}
