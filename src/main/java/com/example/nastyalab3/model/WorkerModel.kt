package com.example.nastyalab3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WorkerTable")
class WorkerModel (

    @PrimaryKey(autoGenerate = true)
    var id: Int, // added in 4 lab

    @ColumnInfo
    val lastName: String,

    @ColumnInfo
    val firstName: String
                    ) {

}