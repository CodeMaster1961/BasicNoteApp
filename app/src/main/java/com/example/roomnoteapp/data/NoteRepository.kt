package com.example.roomnoteapp.data

import com.example.roomnoteapp.data.entities.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<List<Note>>

    suspend fun createNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun getNoteById(noteId: Int): Flow<Note?>
}