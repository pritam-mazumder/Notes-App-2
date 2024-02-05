package com.example.notesapp.DataBase

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.Dao.NotesDao

abstract class NotesDatabase : RoomDatabase() {

    abstract fun myNotesDao(): NotesDao

    companion object {
        @Volatile
        var INSTANCE: NotesDatabase? = null

        fun getDatabaseInstance(context: Context): NotesDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInference =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "Notes").build()
                INSTANCE = roomDatabaseInference
                return roomDatabaseInference
            }
        }
    }
}