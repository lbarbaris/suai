package com.example.nastyalab3.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.nastyalab3.REPOSITORY
import com.example.nastyalab3.db.WorkerDatabase
import com.example.nastyalab3.db.repository.WorkerRealization

class StartViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDataBase(){
        val daoWorker = WorkerDatabase.getInstance(context).getWorkerDao()
        REPOSITORY = WorkerRealization(daoWorker)
    }

    fun getAllWorkers(): LiveData<List<WorkerModel>> {
        return REPOSITORY.allWorkers
    }
}