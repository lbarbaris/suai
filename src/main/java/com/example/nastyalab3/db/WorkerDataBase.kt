package com.example.nastyalab3.db;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nastyalab3.db.dao.WorkerDao
import com.example.nastyalab3.model.WorkerModel;



@Database(entities = [WorkerModel::class], version = 1)
abstract class WorkerDatabase: RoomDatabase() {
    abstract fun getWorkerDao(): WorkerDao

    companion object{
        private var database: WorkerDatabase ?= null

        @Synchronized
        fun getInstance(context: Context): WorkerDatabase{
            return if (database == null){
                database = Room.databaseBuilder(context, WorkerDatabase::class.java, "db").build()
                database as WorkerDatabase
            }
            else{
                database as WorkerDatabase
            }
        }
    }
}
