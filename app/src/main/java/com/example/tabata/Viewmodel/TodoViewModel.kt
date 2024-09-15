package com.example.tabata.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope

import androidx.room.Room
import com.example.tabata.Data.DataBase
import com.example.tabata.Data.TodoList
import kotlinx.coroutines.launch


class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val db:DataBase= Room.databaseBuilder(
        application,
        DataBase::class.java, "todo"
    ).build()

    private val tododao = db.tododao()

    fun getAllTodo(): LiveData<List<TodoList>> = liveData {
        emit(tododao.getalltodo())
    }
    fun insertTodo(todoList: TodoList){
            viewModelScope.launch {
                tododao.InsertTodo(todoList)
            }

    }
}