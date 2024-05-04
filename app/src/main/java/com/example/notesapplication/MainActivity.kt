package com.example.notesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplication.database.NoteDao
import com.example.notesapplication.database.NoteDatabase
import com.example.notesapplication.repository.NoteRepository
import com.example.notesapplication.viewmodel.NoteViewModel
import com.example.notesapplication.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {


    lateinit var noteViewModel: NoteViewModel
    lateinit var noteRepository: NoteRepository
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
    }

    private fun setupViewModel() {
        val noteDao = NoteDatabase.getDatabase(this).getNoteDao()
        noteRepository = NoteRepository(noteDao)
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)
        noteViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }

}