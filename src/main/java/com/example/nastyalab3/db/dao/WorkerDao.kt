package com.example.nastyalab3.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nastyalab3.model.WorkerModel

@Dao
interface WorkerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // в случае конфликта игнорируем
    suspend fun insertWorker(workerModel: WorkerModel)

    @Delete
    suspend fun deleteWorker(workerModel: WorkerModel)

    @Query("SELECT * from workertable")
    fun getAllWorkers(): LiveData<List<WorkerModel>>

}