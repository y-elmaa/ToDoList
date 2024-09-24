package com.example.tabata.View


import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.tabata.MainScreen
import com.example.tabata.Viewmodel.TodoViewModel


@Composable
fun Myapp(todoViewModel: TodoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(todoViewModel = todoViewModel, navController = navController)
        }
        composable("detail") {
            Detail(todoViewModel = todoViewModel, navController = navController)
        }
        composable("detail2") {
            Detail2(todoViewModel = todoViewModel, navController = navController)
        }

    }
}



