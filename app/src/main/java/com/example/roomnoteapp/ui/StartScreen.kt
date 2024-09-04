package com.example.roomnoteapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.roomnoteapp.R
import com.example.roomnoteapp.data.entities.Note
import com.example.roomnoteapp.ui.state.NoteUiState
import com.example.roomnoteapp.ui.theme.RoomNoteAppTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartScreen(
    viewModel: NoteAppViewModel,
    navigateUp: () -> Unit,
    navigateToEdit: (Int) -> Unit
) {
    val notes by viewModel.allNotes.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateUp() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add note")
            }
        }
    ) {
        ListOfNotes(notes = notes.notes, onEditClick = navigateToEdit, onDeleteClick = {
            viewModel.deleteNote(
                NoteUiState(
                    id = it.id,
                    title = it.title,
                    description = it.description
                )
            )
        })
    }
}

@Composable
fun ListOfNotes(notes: List<Note>, onEditClick: (Int) -> Unit, onDeleteClick: (Note) -> Unit) {
    if (notes.isEmpty()) {
        NotesNotFoundText()
    } else {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(10.dp, top = 20.dp, end = 10.dp)
            ) {
                items(notes) { note ->
                    NoteCard(note = note, onEditClick = onEditClick, onDeleteClick = onDeleteClick)
                }
            }
        }
    }
}

@Composable
fun NotesNotFoundText() {
    Text(
        text = stringResource(R.string.notes_not_found),
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    )
}

@Composable
fun NoteCard(note: Note, onEditClick: (Int) -> Unit, onDeleteClick: (Note) -> Unit) {
    Card(
        onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .width(180.dp)
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = note.title, fontSize = 26.sp, modifier = Modifier.padding(8.dp))
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(text = note.description, fontSize = 20.sp, modifier = Modifier.padding(8.dp))

            }
            DeleteAndEditIconButton(
                note = note,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
private fun DeleteAndEditIconButton(
    note: Note,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Note) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(onClick = { onEditClick(note.id) }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "edit note")
        }

        IconButton(onClick = { onDeleteClick(note) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete note")
        }
    }
}