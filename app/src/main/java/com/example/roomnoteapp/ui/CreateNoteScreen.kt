package com.example.roomnoteapp.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomnoteapp.R
import com.example.roomnoteapp.data.entities.Note
import com.example.roomnoteapp.ui.state.NoteUiState
import com.example.roomnoteapp.ui.theme.RoomNoteAppTheme

@Composable
fun CreateNoteScreen(
    viewModel: NoteAppViewModel,
    navigateBack: () -> Unit
) {
    val noteState by viewModel.noteState.collectAsState()
    CreateNoteForm(note = noteState, onValueChange = viewModel::onValueChange, onSaveClick = {
        viewModel.createNote()
        navigateBack()
    })
}

@Composable
fun CreateNoteForm(
    note: NoteUiState,
    onValueChange: (NoteUiState) -> Unit,
    onSaveClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(value = note.title, onValueChange = {
            onValueChange(note.copy(title = it))
        }, label = { Text(text = stringResource(R.string.title_placeholder)) })
        Spacer(modifier = Modifier.padding(top = 10.dp))
        OutlinedTextField(value = note.description, onValueChange = {
            onValueChange(note.copy(description = it))
        }, label = { Text(text = stringResource(R.string.description_placeholder)) })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Button(
            onClick = { onSaveClick() },
            modifier = Modifier.width(285.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.save_button_text))
        }
    }
}

