package com.androidapp.fintrackandroid.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidapp.fintrackandroid.core.database.dao.TransactionDao
import com.androidapp.fintrackandroid.core.database.entity.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class FinTrackDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        const val DATABASE_NAME = "fintrack.db"
    }
}