package com.example.applicationsprint1.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class contacts {
    @PrimaryKey(autoGenerate = true)
    public int contactID;
    @ColumnInfo(name="contact_firstname")
    public String contactFirstName;
    @ColumnInfo(name="contact_lastname")
    public String contactLastName;
    @ColumnInfo(name = "Profile_ID")
    public int profileID;
    @ColumnInfo(name="contact_phoneNumber")
    public double contactPhoneNumber;
    @ColumnInfo(name="contact_alternatePhoneNumber")
    public double alternatePhoneNumber;
    @ColumnInfo(name="priority_of_contact")
    public int priority;


    public contacts(int profileID,int contactID, String contactFirstName, String contactLastName, double contactPhoneNumber, double alternatePhoneNumber, int priority) {
        this.contactID = contactID;
        this.profileID=profileID;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.alternatePhoneNumber = alternatePhoneNumber;
        this.priority = priority;
    }
}
