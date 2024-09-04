package com.example.roomnoteapp.di

import com.example.roomnoteapp.RoomNoteApplication
import com.example.roomnoteapp.data.NoteRepository
import com.example.roomnoteapp.data.NoteRepositoryImp
import com.example.roomnoteapp.ui.NoteAppViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single {
        (androidApplication() as RoomNoteApplication).database.noteDao()
    }

    single<NoteRepository> {
        NoteRepositoryImp(get())
    }

    single {
        NoteAppViewModel(get())
    }
}