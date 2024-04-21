package com.example.nastyalab3.db.repository

import androidx.lifecycle.LiveData
import com.example.nastyalab3.db.dao.WorkerDao
import com.example.nastyalab3.model.WorkerModel

class WorkerRealization(private val workerDao: WorkerDao): WorkerRepository {
    override val allWorkers: LiveData<List<WorkerModel>>
        get() = workerDao.getAllWorkers()

    override suspend fun insertWorker(workerModel: WorkerModel, onSuccess: () -> Unit) {
        workerDao.insertWorker(workerModel)
        onSuccess()
    }

    override suspend fun deleteWorker(workerModel: WorkerModel, onSuccess: () -> Unit) {
        workerDao.deleteWorker(workerModel)
        onSuccess()
    }
}