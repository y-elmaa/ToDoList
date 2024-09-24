package com.example.tabata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


import androidx.compose.ui.unit.dp

import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController

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
fun MainScreen(todoViewModel: TodoViewModel, navController: NavHostController) {
    val todolist by todoViewModel.todos.collectAsState()
    var input by remember { mutableStateOf("") }
    var isclicked by remember { mutableStateOf(false) }


    val filteredList = todolist.filter {
        it.text.contains(input, ignoreCase = true)
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        if (filteredList.isNotEmpty()) {

            CostumeList(
                list = filteredList,
                deleteclick = { todoItem -> todoViewModel.deleteTodo(todoItem) },
                onitemclick = { clickedItem ->
                    todoViewModel.setselectedtodo(clickedItem)
                    navController.navigate("detail")
                }
            )
        } else {
            Text(
                text = "No items available",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }

        FloatingActionButton(
            onClick = { navController.navigate("detail2") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Text(text = "+")
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xFFF6E6F7)
                ),
                onClick = { isclicked = !isclicked
                input = ""
                }
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }

            if (isclicked) {

                CostumInput(
                    value = input,
                    onvaluechange = { input = it },
                    placeholder = "search"
                )
            }
        }
    }
}



