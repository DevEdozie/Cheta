package com.example.notesapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapplication.databinding.NoteLayoutBinding
import com.example.notesapplication.fragments.HomeFragmentDirections
import com.example.notesapplication.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itemBinding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

//    private val differCallBack = object : DiffUtil.ItemCallback<Note>() {
//        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
//            return oldItem.id == newItem.id &&
//                    oldItem.noteDesc == newItem.noteDesc &&
//                    oldItem.noteTitle == newItem.noteTitle
//        }
//
//        @SuppressLint("DiffUtilEquals")
//        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
//            return oldItem == newItem
//        }
//
//    }


    private val differCallBack = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            // Check if the IDs of the items are the same
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            // Check if the content of the items is the same
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.noteTitle.text = currentNote.noteTitle
        holder.itemBinding.noteDesc.text = currentNote.noteDesc

        holder.itemView.setOnClickListener {
            val directions =
                HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
            it.findNavController().navigate(directions)
        }
    }
}