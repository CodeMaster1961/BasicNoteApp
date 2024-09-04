package com.example.roomnoteapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomnoteapp.data.dao.NoteDao
import com.example.roomnoteapp.data.entities.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteAppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}