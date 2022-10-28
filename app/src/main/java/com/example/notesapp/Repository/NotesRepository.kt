package com.example.notesapp.Repository

import androidx.lifecycle.LiveData
import com.example.notesapp.Dao.NotesDao
import com.example.notesapp.model.Notes

class NotesRepository(val dao:NotesDao) {


    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun getHighNotes():LiveData<List<Notes>>{
        return dao.getHighNotes()
    }
    fun getMediumNotes():LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }
    fun getLowNotes():LiveData<List<Notes>>{
        return dao.getLowNotes()
    }

    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

}