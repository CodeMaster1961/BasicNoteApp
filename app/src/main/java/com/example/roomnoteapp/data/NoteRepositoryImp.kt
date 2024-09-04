package com.example.roomnoteapp.data

import com.example.roomnoteapp.data.dao.NoteDao
import com.example.roomnoteapp.data.entities.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImp (private val noteDao: NoteDao) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun createNote(note: Note) {
        noteDao.createNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun getNoteById(noteId: Int): Flow<Note?> {
        return noteDao.getNoteById(noteId)
    }

}