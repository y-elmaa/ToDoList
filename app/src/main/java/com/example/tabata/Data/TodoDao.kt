package com.example.tabata.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface TodoDao {
    @Insert
    suspend fun InsertTodo(todoList: TodoList)

    @Query("SELECT * FROM todo")
    suspend fun getalltodo():List<TodoList>
}