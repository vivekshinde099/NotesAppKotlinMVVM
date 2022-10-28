package com.example.notesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
//this is the Entity class which sets the table name and table constraints in it

@Entity(tableName = "Notes")
class Notes (

    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,
    var title: String,
    var subTitle: String,
    var notes: String,
    var date: String,
    var priority:String

    ): Parcelable