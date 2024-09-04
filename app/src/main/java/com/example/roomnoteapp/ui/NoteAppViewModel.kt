package com.example.roomnoteapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomnoteapp.data.NoteRepository
import com.example.roomnoteapp.data.entities.Note
import com.example.roomnoteapp.ui.state.NoteUiState
import com.example.roomnoteapp.ui.state.StartUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteAppViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    var allNotes: StateFlow<StartUiState> = noteRepository.getAllNotes().map { StartUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StartUiState()
        )



    private val _noteState = MutableStateFlow(NoteUiState())
    val noteState: StateFlow<NoteUiState> = _noteState.asStateFlow()

    /**
     * Updates the UI state of the note based on the user input.
     * @author Ömer Aynaci
     * @param note
     */
    fun onValueChange(note: NoteUiState) {
        _noteState.value = note
    }

     fun toNote(): Note {
        return Note(
            id = 0,
            title = noteState.value.title,
            description = noteState.value.description
        )
    }

     fun convertNoteToNoteUiState(note: NoteUiState): Note {
        return Note(
            id = note.id,
            title = note.title,
            description = note.description
        )
    }

    fun toNoteUiState(note: Note): NoteUiState {
        return NoteUiState(
            id = note.id,
            title = note.title,
            description = note.description
        )
    }

    fun createNote() {
        viewModelScope.launch {
            noteRepository.createNote(toNote())
        }
    }

    fun updateNote(note: NoteUiState) {
        val noteState = convertNoteToNoteUiState(note)
        viewModelScope.launch {
            noteRepository.updateNote(noteState)
        }
    }

    fun deleteNote(note: NoteUiState) {
        val noteState = convertNoteToNoteUiState(note)
        viewModelScope.launch {
            noteRepository.deleteNote(noteState)
        }
    }

    fun getNoteById(noteId: Int): Flow<NoteUiState> = flow {
        val note = withContext(viewModelScope.coroutineContext) {
            noteRepository.getNoteById(noteId).firstOrNull()
        }
        emit(
            note?.let {
                NoteUiState(
                    id = it.id,
                    title = it.title,
                    description = it.description
                )
            } ?: NoteUiState()
        )
    }
}