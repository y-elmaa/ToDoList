package com.example.tabata

import androidx.lifecycle.liveData
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tabata.Data.TodoList
import com.example.tabata.View.CostumBotuun
import com.example.tabata.View.CostumInput
import com.example.tabata.View.CostumList
import com.example.tabata.Viewmodel.TodoViewModel


import com.example.tabata.ui.theme.TabataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val todoViewModel:TodoViewModel by viewModels()
        setContent {
            TabataTheme {
                MainScreen(todoViewModel)
            }
        }
    }


}





@Composable
fun MainScreen(todoViewModel: TodoViewModel) {
    val todo_list by todoViewModel.getAllTodo().observeAsState(emptyList())

    var input_text by remember {
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

            CostumInput(value = input_text,
                onvaluechange = { input_text = it }
            )

            CostumBotuun(onclick = {
                if (input_text.isNotEmpty()) {
                    todoViewModel.insertTodo(TodoList(text = input_text))
                    input_text = ""

                }
            })


        }
        Spacer(Modifier.height(10.dp))

        CostumList(list = todo_list)

    }
}







@Preview(showSystemUi = true)
@Composable
fun preview(modifier: Modifier = Modifier) {





}
