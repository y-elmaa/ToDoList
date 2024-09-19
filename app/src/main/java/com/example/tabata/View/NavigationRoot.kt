package com.example.tabata.View

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tabata.MainScreen
import com.example.tabata.Viewmodel.TodoViewModel



@Composable
fun Myapp(todoViewModel: TodoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination =  "main") {
        composable("main"){
            MainScreen(todoViewModel = todoViewModel , navController = navController )
        }
        composable("detail"){
            Detail(todoViewModel = todoViewModel , navController= navController)
        }
    }
}