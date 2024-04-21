package com.example.nastyalab3.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nastyalab3.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddWorkerViewModel: ViewModel() {
    fun insert(workerModel: WorkerModel, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insertWorker(workerModel){
                onSuccess
            }
        } //Dispatchers.IO - сопрограмма, работает в фоне, нужна для ввода-вывода
}