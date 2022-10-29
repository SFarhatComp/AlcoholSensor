package com.example.applicationsprint1.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile_table")
public class profile {
    @PrimaryKey(autoGenerate = true)
    public int profileID;
    @ColumnInfo(name="profile_firstname")
    public String firstName;
    @ColumnInfo(name="profile_LastName")
    public String lastname;
    @ColumnInfo(name="profile_gender")
    public String gender;
    @ColumnInfo(name="profile_Height")
    public int height;
    @ColumnInfo(name="profile_Weight")
    public int weight;
    @ColumnInfo(name="profile_Age")
    public int age;

    public profile(int profileID, String firstName,String lastname, String gender, int height, int weight, int age) {
        this.profileID = profileID;
        this.firstName = firstName;
        this.lastname=lastname;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }
}
