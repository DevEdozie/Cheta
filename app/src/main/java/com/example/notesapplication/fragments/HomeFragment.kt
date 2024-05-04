package com.example.notesapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapplication.MainActivity
import com.example.notesapplication.R
import com.example.notesapplication.adapter.NoteAdapter
import com.example.notesapplication.databinding.FragmentHomeBinding
import com.example.notesapplication.model.Note
import com.example.notesapplication.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener,
    MenuProvider {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        notesViewModel = (activity as MainActivity).noteViewModel
        setupHomeRecyclerView()

        binding.addNoteFab.setOnClickListener {
//            findNavController().navigate(R.id.action_addNoteFragment2_to_homeFragment)
            it.findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment2)
        }
    }

    private fun updateUI(note: List<Note>) {
        if (note != null) {
            if (!note.isEmpty()) {
//                Toast.makeText(context, "Recyclerview is now visible", Toast.LENGTH_LONG).show()
                binding.emptyNotesImage.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            } else {
//                Toast.makeText(context, "Image is now visible", Toast.LENGTH_LONG).show()
                binding.emptyNotesImage.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun setupHomeRecyclerView() {
        noteAdapter = NoteAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = noteAdapter // Set the adapter to the RecyclerView
        }



        activity?.let {
            notesViewModel.getAllNotes().observe(viewLifecycleOwner) { notes ->
                Log.i("AllNotes", "All Notes: $notes")
                noteAdapter.differ.submitList(notes)
                val itemCount = noteAdapter.differ.currentList.size
                Log.i("List Size", "SIZE: ${itemCount}")
                updateUI(notes)

            }
        }
    }

    private fun searchNote(query: String?) {
        val searchQuery = "%$query"

        notesViewModel.searchNote(searchQuery).observe(this) { list ->
            noteAdapter.differ.submitList(list)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)
        }
        return true
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        binding = null
//    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}