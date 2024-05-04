package com.example.notesapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapplication.database.NoteDatabase
import com.example.notesapplication.model.Note
import com.example.notesapplication.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application, private val repository: NoteRepository) :
    AndroidViewModel(application) {

//    val allNotes: LiveData<List<Note>>


//    init {
//        val dao = NoteDatabase.getDatabase(application).getNoteDao()
//        repository = NoteRepository(dao)
//        allNotes = repository.allNotes
//    }


    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(note)
    }

    fun getAllNotes() = repository.getAllNotes()

    fun searchNote(query: String?) = repository.searchNote(query)
}