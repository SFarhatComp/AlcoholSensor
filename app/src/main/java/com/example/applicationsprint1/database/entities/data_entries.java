package com.example.applicationsprint1.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data_entries_table")
public class data_entries {

    @PrimaryKey(autoGenerate = true)
    public int dataID;
    @ColumnInfo(name = "Data_Amount")
    public int data;
    @ColumnInfo(name="UserID")
    public int userId;
    @ColumnInfo(name="UserLastName")
    public String userLastName;
    @ColumnInfo(name="UserFirstName")
    public String userFirstName;
    @ColumnInfo(name="DrivingCapabilities")
    public String canHeDrive;
    @ColumnInfo(name="Date")
    public String dateOfData;







}
