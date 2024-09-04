package com.example.roomnoteapp

import android.app.Application
import androidx.room.Room
import com.example.roomnoteapp.data.database.NoteAppDatabase
import com.example.roomnoteapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RoomNoteApplication : Application() {

    lateinit var database: NoteAppDatabase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            NoteAppDatabase::class.java,
            "note_database"
        ).build()
        startKoin {
            androidLogger()
            androidContext(this@RoomNoteApplication)
            modules(appModule)

        }
    }
}