package com.example.applicationsprint1.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.applicationsprint1.database.entities.contacts;
import com.example.applicationsprint1.database.entities.profile;

import java.util.List;

@Dao
public interface ContactsDao {
    // This returns a list of all contacts
    @Query("SELECT * from contact_table")
    List<contacts> getAll();


    // This query returns a list of all contacts but sorted with the LAST NAME in Ascending order
    @Query("SELECT * FROM contact_table WHERE Profile_ID=:profileID ORDER BY contact_lastname,contact_firstname ASC")
    List<contacts> getAllByLastName(int profileID);


    // This query returns a list of all contacts but sorted with the priority first then last name and first name
    @Query("SELECT * FROM contact_table ORDER BY priority_of_contact,contact_lastname,contact_firstname ASC")
    List<contacts> getAllByPriority();


    // This returns a contact by its contact ID, will be useful to set and fetch values for a specific ID
    @Query("SELECT * FROM contact_table WHERE Profile_ID=:profileID ")
    List<contacts> FindById(int profileID);

    @Query("SELECT * FROM contact_table WHERE Profile_ID=:profileID AND contactID=:contact_ID")
    contacts FindByProfileIdAndContactID(int profileID,int contact_ID);


    @Insert
    void InsertContact(contacts... Contact);
    // Delete a contact from the database if needed



    @Delete
    void delete(contacts Contact);



}



