package com.example.notesapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val subtitle: String,
    val notes: String,
    val date: String,
    val priority: String
) {
}