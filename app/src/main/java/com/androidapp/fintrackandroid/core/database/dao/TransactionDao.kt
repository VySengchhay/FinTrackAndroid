package com.androidapp.fintrackandroid.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.androidapp.fintrackandroid.core.database.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions ORDER BY transaction_date DESC")
    fun observeAll(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE id = :transactionId LIMIT 1")
    suspend fun getById(
        transactionId: String
    ): TransactionEntity?

    @Upsert
    suspend fun upsert(
        transaction: TransactionEntity
    )

    @Upsert
    suspend fun upsertAll(
        transactions: List<TransactionEntity>
    )

    @Query("DELETE FROM transactions WHERE id = :transactionId")
    suspend fun deleteById(
        transactionId: String
    )

    @Query("DELETE FROM transactions")
    suspend fun clear()
}