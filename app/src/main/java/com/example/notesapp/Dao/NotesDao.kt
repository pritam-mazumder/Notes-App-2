package com.example.notesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.Model.Notes

@Dao
interface NotesDao {

    // Read data
    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    // Sorting by HIGH
    @Query("SELECT * FROM Notes WHERE priority = 3")
    fun getHighNotes(): LiveData<List<Notes>>

    // Sorting by HIGH
    @Query("SELECT * FROM Notes WHERE priority = 2")
    fun getMediumNotes(): LiveData<List<Notes>>

    // Sorting by HIGH
    @Query("SELECT * FROM Notes WHERE priority = 1")
    fun getLowNotes(): LiveData<List<Notes>>

    // Insert data
    @Insert(onConflict = OnConflictStrategy.REPLACE) // OnConflictStrategy us used to deal with same query
    fun insertNotes(notes: Notes)

    // Delete data
    @Query("DELETE FROM Notes WHERE id =:id")
    fun deleteNotes(id: Int)

    // Update data
    @Update
    fun updateNotes(notes: Notes)
}