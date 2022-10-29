package com.example.applicationsprint1.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.applicationsprint1.database.dao.ContactsDao;
import com.example.applicationsprint1.database.dao.DataDao;
import com.example.applicationsprint1.database.dao.ProfileDao;
import com.example.applicationsprint1.database.entities.contacts;
import com.example.applicationsprint1.database.entities.data_entries;
import com.example.applicationsprint1.database.entities.profile;

@Database(entities={profile.class, contacts.class, data_entries.class},version = 1)

public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase DataBaseInstance;
    private static final String DatabaseName="Database_project_alcohol_sensor";

    // the constructor is protected to stop people from using it
    protected AppDatabase(){};


    // Database creator
    private static AppDatabase Create(Context context){
        return Room.databaseBuilder(context,AppDatabase.class,DatabaseName).allowMainThreadQueries().build();

    }

    public static synchronized AppDatabase CreateDatabase(Context context){

        if (DataBaseInstance==null){

            DataBaseInstance=Create(context);
        }

        return DataBaseInstance;



    }

    public abstract ProfileDao profileDao();
    public abstract DataDao dataDao();
    public abstract ContactsDao contactsDao();


}