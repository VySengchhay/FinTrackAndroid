package com.androidapp.fintrackandroid.di
import android.content.Context
import androidx.room.Room
import com.androidapp.fintrackandroid.core.database.FinTrackDatabase
import com.androidapp.fintrackandroid.core.database.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFinTrackDatabase(
        @ApplicationContext context: Context
    ): FinTrackDatabase {
        return Room.databaseBuilder(
            context,
            FinTrackDatabase::class.java,
            FinTrackDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideTransactionDao(
        database: FinTrackDatabase
    ): TransactionDao {
        return database.transactionDao()
    }
}