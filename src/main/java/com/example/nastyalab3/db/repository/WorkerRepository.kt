package com.example.nastyalab3.db.repository

import androidx.lifecycle.LiveData
import com.example.nastyalab3.model.WorkerModel

interface WorkerRepository {
    val allWorkers: LiveData<List<WorkerModel>>
    suspend fun insertWorker(workerModel: WorkerModel, onSuccess: () -> Unit)
    suspend fun deleteWorker(workerModel: WorkerModel, onSuccess: () -> Unit)
}