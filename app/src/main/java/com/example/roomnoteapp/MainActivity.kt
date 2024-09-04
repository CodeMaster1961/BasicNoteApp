package com.example.roomnoteapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.roomnoteapp.ui.NoteAppViewModel
import com.example.roomnoteapp.ui.navigation.NoteApp
import com.example.roomnoteapp.ui.theme.RoomNoteAppTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = getViewModel<NoteAppViewModel>()
                    NoteApp(viewModel)
                }
            }
        }
    }
}

