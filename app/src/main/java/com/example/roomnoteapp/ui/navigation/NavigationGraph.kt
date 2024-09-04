package com.example.roomnoteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomnoteapp.ui.CreateNoteScreen
import com.example.roomnoteapp.ui.EditScreen
import com.example.roomnoteapp.ui.NoteAppViewModel
import com.example.roomnoteapp.ui.StartScreen


@Composable
fun NoteApp(
    viewModel: NoteAppViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screens.START_SCREEN.name) {
        composable(Screens.START_SCREEN.name) {
            StartScreen(viewModel = viewModel, navigateUp = {
                navController.navigate(Screens.CREATE_NOTE_SCREEN.name)
            }, navigateToEdit = { noteId ->
                navController.navigate("${Screens.EDIT_NOTE_SCREEN.name}/$noteId")
            })
        }

        composable(Screens.CREATE_NOTE_SCREEN.name) {
            CreateNoteScreen(viewModel = viewModel, navigateBack = {
                navController.navigateUp()
            })
        }

        composable("${Screens.EDIT_NOTE_SCREEN.name}/{noteId}") { navBackStackEntry ->
            val noteId = navBackStackEntry.arguments?.getString("noteId")?.toIntOrNull() ?: -1
            EditScreen(viewModel = viewModel, noteId = noteId, navigateBack = {
                navController.navigateUp()
            })
        }
    }
}