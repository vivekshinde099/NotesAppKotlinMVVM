package com.example.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun myNotesDao(): NotesDao

    companion object
    {
        var INSTANCE: NotesDatabase? = null
        fun getDatabaseInstance(context: Context):NotesDatabase{
            val tmpInstance = INSTANCE
            if (tmpInstance!=null)
            {
                return tmpInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance = Room.databaseBuilder(
                    context,NotesDatabase::class.java,
                    "NotesDB").allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance
                return roomDatabaseInstance
            }


        }
    }
}