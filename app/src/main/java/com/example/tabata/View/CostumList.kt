package com.example.tabata.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tabata.Data.TodoList

@Composable
fun CostumList(list: List<TodoList> , deleteclick:(TodoList)-> Unit) {



    LazyColumn (
        reverseLayout = true
    ){
        items(list) { item ->
            CostumCard(todo = item , deleteclick = { deleteclick(item)})
        }
    }
}

@Composable
fun CostumCard(todo: TodoList, deleteclick: () -> Unit) {
    var card_color = Color.Cyan

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(6.dp), colors = CardDefaults.cardColors(
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
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delet todo" )
            }

        }
    }
}


