package com.example.notesapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.findFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.notesapplication.MainActivity
import com.example.notesapplication.R
import com.example.notesapplication.database.NoteDatabase
import com.example.notesapplication.databinding.FragmentAddNoteBinding
import com.example.notesapplication.model.Note
import com.example.notesapplication.repository.NoteRepository
import com.example.notesapplication.viewmodel.NoteViewModel
import com.example.notesapplication.viewmodel.NoteViewModelFactory

//class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {
class AddNoteFragment : Fragment(R.layout.fragment_add_note) {


    private lateinit var binding: FragmentAddNoteBinding

    private lateinit var notesViewModel: NoteViewModel
    private lateinit var addNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val menuHost: MenuHost = requireActivity()
//        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).noteViewModel
        addNoteView = view
        binding.addNoteFab.setOnClickListener {
            saveNote(addNoteView)
        }
    }


//    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//        menu.clear()
//        menuInflater.inflate(R.menu.menu_add_note, menu)
//    }
//
//    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//        return when (menuItem.itemId) {
//            R.id.saveMenu -> {
//                saveNote(addNoteView)
//                true
//            }
//
//            else -> false
//        }
//
//    }

    private fun saveNote(view: View) {
        val noteTitle = binding.addNoteTitle.text.toString().trim()
        val noteDesc = binding.addNoteDesc.text.toString().trim()

        if (noteTitle.isNotEmpty()) {
            val note = Note(0, noteTitle, noteDesc)
            notesViewModel.addNote(note)

            Toast.makeText(addNoteView.context, "Note Saved", Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment, false)
        } else {
            Toast.makeText(addNoteView.context, "Please enter note title", Toast.LENGTH_SHORT)
                .show()
        }
    }


}