package com.example.tabata.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.viewModelScope


import com.example.tabata.Data.DataBase
import com.example.tabata.Data.TodoList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch


class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val todoDao = DataBase.getDatabase(application).tododao()

    private val _todos = MutableStateFlow<List<TodoList>>(emptyList())
    val todos: StateFlow<List<TodoList>> = _todos



    private val _setslectedtodo  = MutableStateFlow<TodoList?>(null)
    val setlelectedtodo : StateFlow<TodoList?> = _setslectedtodo.asStateFlow()

    init {
        viewModelScope.launch {
            _todos.value = todoDao.getalltodo() // Assuming you have this method in TodoDao
        }
    }

     fun insertTodo(todo: TodoList) {

        viewModelScope.launch {

            todoDao.InsertTodo(todo)
            _todos.value = todoDao.getalltodo() // Update the list after insertion
        }

    }

    fun deleteTodo(todo: TodoList) {

        viewModelScope.launch {

            todoDao.Deletetodo(todo)
            _todos.value = todoDao.getalltodo() // Update the list after deletion
        }
    }

    fun updateTodo(todo: TodoList) {

        viewModelScope.launch {

            todoDao.UpdateTodo(todo)
            _todos.value = todoDao.getalltodo() // Update the list after deletion
        }
    }



    fun setselectedtodo(todo: TodoList){
            _setslectedtodo.value = todo
    }

}
