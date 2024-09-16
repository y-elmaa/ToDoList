package com.example.tabata.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private  val _todo = MutableLiveData<List<TodoList>>()
    val todos : LiveData<List<TodoList>> get() = _todo

    init {
        getAllTodo()
    }
    fun getAllTodo() {
        viewModelScope.launch {
            _todo.value = tododao.getalltodo()
        }
    }
    fun insertTodo(todoList: TodoList){
            viewModelScope.launch {
                tododao.InsertTodo(todoList)
                getAllTodo()
            }

    }

    fun DeleteTodo(todoList: TodoList){

        viewModelScope.launch {
            tododao.Deletetodo(todoList)

            getAllTodo()
        }
    }
}