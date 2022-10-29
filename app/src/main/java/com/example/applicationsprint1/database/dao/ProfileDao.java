package com.example.applicationsprint1.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.applicationsprint1.database.entities.profile;

import java.util.List;

@Dao
public interface ProfileDao {
    // This returns a list of all profile
    @Query("SELECT * from profile_table")
    List<profile> getAll();


    // This query returns a list of all profiles but sorted with the LAST NAME in Ascending order
    @Query("SELECT * FROM profile_table ORDER BY profile_LastName,profile_firstname ASC")
    List<profile> getAllByLastName();


    // This returns a profile by its profile ID, will be useful to set and fetch values for a specific ID
    @Query("SELECT * FROM profile_table WHERE profileID=:profileID ")
    profile FindById(int profileID);




    @Insert
    void insertProfile(profile... Profiles);



    //Deletes a contact from the database if needed
    @Delete
    void delete(profile Profile);
}
