package com.example.applicationsprint1.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.applicationsprint1.database.entities.data_entries;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DataDao {

    // This returns a list of all datas object
    @Query("SELECT * from data_entries_table")
    List<data_entries> getAll();


    // THE NEXT TWO QUERYS HAVE TO BE REVIEWED< NOTE SURE ABOUT THE QUERY ANND WHAT IT SHOUDL RETURN



    // This query returns a list of data for a given profile ID. Multiple access to the data entry table that are all related  to the same profile ID.
    @Query("SELECT * FROM data_entries_table WHERE userId=:profileId" )
    List<data_entries> GetDataByProfileID(int profileId);


    // This returns a specifc data OBJECT  by its data ID, will be useful to set and fetch values for a specific ID
    @Query("SELECT * FROM data_entries_table WHERE userId=:profileID ")
    data_entries FindById(int profileID);



    // This query should return a list of data entries by a specific user ID ordered by CREATION DATE. This query has to be tested.
    @Query("SELECT * FROM data_entries_table WHERE userId=:profileId ORDER BY Date ASC"  )
    List<data_entries> GetDataByProfileIDAscendingDate(int profileId);


    @Insert
    void insertData(data_entries ... Data_);


    // we don't want to delete data, as it could be a way for the user to trick the system .


}
