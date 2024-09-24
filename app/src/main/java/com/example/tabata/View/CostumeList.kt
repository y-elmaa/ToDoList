package com.example.tabata.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tabata.Data.TodoList
import com.example.tabata.Viewmodel.TodoViewModel

@Composable
fun CostumeList(
    list: List<TodoList>,
    deleteclick: (TodoList) -> Unit,
    onitemclick: (TodoList) -> Unit
) {


    LazyColumn(
        reverseLayout = true

    ) {
        items(list) { item ->
            CostumCard(
                todo = item,
                deleteclick = { deleteclick(item) },
                onitemclick = { onitemclick(item) })
        }
    }
}

@Composable
fun CostumCard(todo: TodoList, deleteclick: () -> Unit, onitemclick: () -> Unit) {
    val card_color = Color.Cyan

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(6.dp)
            .clickable { onitemclick() }, colors = CardDefaults.cardColors(
            containerColor = card_color.copy(alpha = 0.20f)

        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = todo.text,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp)
            )

            IconButton(onClick = deleteclick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delet todo")
            }

        }
    }
}

@Composable
fun Detail(todoViewModel: TodoViewModel, navController: NavHostController) {

    val selectedTodo by todoViewModel.setlelectedtodo.collectAsState()

    var texxxt by remember {
        mutableStateOf(selectedTodo?.text ?: "")
    }


    selectedTodo?.let {


        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                CostumInput(value = texxxt, onvaluechange = { texxxt = it }, "modifier")

                CostumBotuun(name = "modifier", onclick = {

                    if (texxxt.isNotEmpty()) {
                        todoViewModel.updateTodo(TodoList(id = selectedTodo!!.id, text = texxxt))

                        navController.navigate("main")
                    }
                }


                )


            }
        }
    } ?: Text(text = "No todo selected", fontSize = 20.sp)
}

@Composable
fun Detail2(todoViewModel: TodoViewModel, navController: NavHostController) {


    var inputText by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            CostumInput(value = inputText, onvaluechange = { inputText = it }, "add tasck")



            CostumBotuun(name = "add", onclick = {

                if (inputText.isNotEmpty()) {
                    todoViewModel.insertTodo(TodoList(text = inputText))

                    navController.navigate("main")
                }
            }


            )


        }
    }


}




