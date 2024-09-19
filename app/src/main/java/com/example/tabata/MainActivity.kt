package com.example.tabata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tabata.Data.TodoList
import com.example.tabata.View.CostumBotuun
import com.example.tabata.View.CostumInput
import com.example.tabata.View.CostumeList
import com.example.tabata.View.Myapp
import com.example.tabata.Viewmodel.TodoViewModel


import com.example.tabata.ui.theme.TabataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todoViewModel: TodoViewModel by viewModels()
        setContent {
            TabataTheme {
               Myapp(todoViewModel = todoViewModel)
            }
        }
    }


}


@Composable
fun MainScreen(todoViewModel: TodoViewModel , navController: NavHostController) {
    val todolist  by todoViewModel.todos.collectAsState()

    var inputText by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(5.dp)

    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            CostumInput(value = inputText,
                onvaluechange = { inputText = it }
            )


            CostumBotuun( name = "add",  onclick = {
                if (inputText.isNotEmpty()) {
                    todoViewModel.insertTodo(TodoList(text = inputText))
                    inputText = ""
                }
            })


        }
        Spacer(Modifier.height(10.dp))

        CostumeList(
            list = todolist, // Use the collected todo list
            deleteclick = { todoItem ->
                todoViewModel.deleteTodo(todoItem)
            },
            onitemclick = { clickedItem ->
                todoViewModel.setselectedtodo(clickedItem)
                navController.navigate("detail")
            }
        )

    }
}



