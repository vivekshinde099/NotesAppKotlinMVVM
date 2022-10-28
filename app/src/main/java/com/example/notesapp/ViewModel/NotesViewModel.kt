package com.example.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.Database.NotesDatabase
import com.example.notesapp.Repository.NotesRepository
import com.example.notesapp.model.Notes

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository:NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository= NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> =
        repository.getAllNotes()
    fun getHighNotes():LiveData<List<Notes>>{
        return repository.getHighNotes()
    }
    fun getMediumNotes():LiveData<List<Notes>>{
        return repository.getMediumNotes()
    }
    fun getLowNotes():LiveData<List<Notes>>{
        return repository.getLowNotes()
    }


    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}