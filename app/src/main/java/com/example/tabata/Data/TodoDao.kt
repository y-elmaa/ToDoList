package com.example.tabata.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    suspend fun getalltodo():List<TodoList>


    @Insert
    suspend fun InsertTodo(todoList: TodoList)

    @Update
    suspend fun UpdateTodo(todoList: TodoList)




    @Delete
    suspend fun Deletetodo(todoList: TodoList)
}