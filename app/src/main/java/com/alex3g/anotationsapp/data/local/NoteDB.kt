package com.alex3g.anotationsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex3g.anotationsapp.data.local.dao.NoteDao
import com.alex3g.anotationsapp.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDB : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDB? = null

        fun getDatabase(context: Context): NoteDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDB::class.java,
                    "note_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
//        fun getDatabase(context: Context): NoteDB =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                NoteDB::class.java,
//                "note_database"
//            ).build()
    }

}