package com.example.notesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.Notes

//this DAO Data Access Object Interface used to get the data or update/insert
//the data with he help of querries

@Dao
interface NotesDao {

    //To Get all data from Notes Table
    @Query("SELECT *  FROM Notes ")
    fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT *  FROM Notes WHERE priority=3")
    fun getHighNotes(): LiveData<List<Notes>>

    @Query("SELECT *  FROM Notes WHERE priority=1")
    fun getLowNotes(): LiveData<List<Notes>>

    @Query("SELECT *  FROM Notes WHERE priority=2")
    fun getMediumNotes(): LiveData<List<Notes>>

    //To Insert the data in the Notes Table
    @Insert(onConflict = OnConflictStrategy.REPLACE) //this is used to replace the same query.
    fun insertNotes(notes: Notes)

    //To Delete the Data in Notes Table
    @Query("DELETE FROM NOTES WHERE id=:id")
    fun deleteNotes(id:Int)

    //To update the data un the Notes Table
    @Update
    fun updateNotes(notes: Notes)

}