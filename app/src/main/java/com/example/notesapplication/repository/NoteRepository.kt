package com.example.notesapplication.repository

import com.example.notesapplication.database.NoteDao
import com.example.notesapplication.model.Note

class NoteRepository(private val noteDao: NoteDao) {




    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }


    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }


    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    fun getAllNotes() = noteDao.getAllNotes()
    fun searchNote(query: String?) = noteDao.searchNote(query)


}