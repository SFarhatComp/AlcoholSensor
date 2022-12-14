package com.example.applicationsprint1.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class contacts {
    @ColumnInfo(name = "Profile_ID")
    public int profileID;
    @PrimaryKey(autoGenerate = true)
    public int contactID;
    @ColumnInfo(name="contact_firstname")
    public String contactFirstName;
    @ColumnInfo(name="contact_lastname")
    public String contactLastName;
    @ColumnInfo(name="contact_phoneNumber")
    public String contactPhoneNumber;
    @ColumnInfo(name="priority_of_contact")
    public int priority;


    public contacts(int profileID,int contactID, String contactFirstName, String contactLastName, String contactPhoneNumber, int priority) {
        this.contactID = contactID;
        this.profileID=profileID;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.priority = priority;
    }
}
