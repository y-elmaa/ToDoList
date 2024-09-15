package com.example.tabata.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoList (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0 ,
    var text : String
)
