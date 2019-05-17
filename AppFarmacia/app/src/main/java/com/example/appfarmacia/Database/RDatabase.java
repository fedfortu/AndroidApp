package com.example.appfarmacia.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import com.example.appfarmacia.Model.Farmacie;



@Database (entities ={ Farmacie.class }, version = 1, exportSchema = false)
public abstract class RDatabase extends RoomDatabase {
    public abstract FarmacieDao getFarmacieDao();


    private static RDatabase instance = null;

    public RDatabase(){}

    public static RDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context,
                    RDatabase.class,
                    "myRoomDatabase").build();
        }
        return instance;
    }

}
