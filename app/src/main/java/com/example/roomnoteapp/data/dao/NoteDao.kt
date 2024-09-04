package com.example.roomnoteapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomnoteapp.data.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note_table WHERE id = :noteId")
    fun getNoteById(noteId: Int): Flow<Note?>
}