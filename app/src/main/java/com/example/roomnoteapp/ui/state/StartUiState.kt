package com.example.roomnoteapp.ui.state

import com.example.roomnoteapp.data.entities.Note

data class StartUiState(
    val notes: List<Note> = listOf(),
)
