package com.example.roomnoteapp.ui


import android.util.Log
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
import androidx.compose.ui.unit.dp
import com.example.roomnoteapp.R
import com.example.roomnoteapp.ui.state.NoteUiState

@Composable
fun EditScreen(
    viewModel: NoteAppViewModel,
    noteId: Int,
    navigateBack: () -> Unit
) {
    val noteUiState by viewModel.getNoteById(noteId).collectAsState(initial = NoteUiState())
    val noteUiNewState by viewModel.noteState.collectAsState()
    val noteState = noteUiState as? NoteUiState
    if (noteState != null) {
        EditForm(
            noteState = noteState,
            onSaveClick = {
                viewModel.updateNote(
                    NoteUiState(
                        id = noteState.id,
                        title = noteUiNewState.title,
                        description = noteUiNewState.description
                    )
                )
            },
            onValueChange = viewModel::onValueChange,
            navigateBack = navigateBack
        )
    }
}


@Composable
fun EditForm(
    noteState: NoteUiState,
    onSaveClick: () -> Unit,
    onValueChange: (NoteUiState) -> Unit,
    navigateBack: () -> Unit
) {
    var title by remember(noteState) {
        mutableStateOf(noteState.title)
    }
    var description by remember(noteState) {
        mutableStateOf(noteState.description)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(value = title, onValueChange = {
            title = it
            onValueChange(noteState.copy(title = it))
        }, label = { Text(text = stringResource(R.string.title_placeholder)) })
        Spacer(modifier = Modifier.padding(top = 10.dp))
        OutlinedTextField(value = description, onValueChange = {
            description = it
            onValueChange(noteState.copy(description = it))
        }, label = { Text(text = stringResource(R.string.description_placeholder)) })
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Button(
            onClick = {
                onSaveClick()
                navigateBack()
            }, modifier = Modifier.width(285.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(stringResource(R.string.save_button_text))
        }
    }
}