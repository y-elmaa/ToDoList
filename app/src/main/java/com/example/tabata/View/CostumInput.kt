package com.example.tabata.View

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CostumInput(value : String , onvaluechange: (String) -> Unit) {

    val custom_color = Color.Red




    TextField(value = value, onValueChange = {

        onvaluechange(it)


    }, placeholder = { Text(text = "action", modifier = Modifier.padding(start = 10.dp)) },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Unspecified,
            focusedIndicatorColor = Color.Unspecified,
            unfocusedContainerColor = custom_color.copy(alpha = 0.10f)
            , focusedContainerColor = custom_color.copy(alpha = 0.10f)
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.width(250.dp)
    )
}