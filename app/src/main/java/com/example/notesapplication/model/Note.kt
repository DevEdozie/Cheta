package com.example.notesapplication.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteDesc: String
) : Parcelable {
    // Override toString() method to return note details in JSON-like format
    override fun toString(): String {
        return "{\"id\":$id, \"title\":\"$noteTitle\", \"description\":\"$noteDesc\"}"
    }

}

