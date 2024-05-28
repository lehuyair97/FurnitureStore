package com.example.mobileandroidapp_kotlin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileandroidapp_kotlin.R
import com.example.mobileandroidapp_kotlin.model.model



@Composable
fun Task2Content(models: List<model>) {
    val rememberIndex1 by remember { mutableStateOf(-1) }
    val rememberIndex2 by remember { mutableStateOf(-1) }

    Column {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(models) { item ->
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = item.name,
                        contentDescription = "",
                        modifier = Modifier.size(42.dp).clickable {
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Task2Preview() {
    val models = listOf<model>(
        model(0, painterResource(id = R.drawable.pig)),
        model(1, painterResource(id = R.drawable.tiger)),
        model(0, painterResource(id = R.drawable.pig)),
        model(2, painterResource(id = R.drawable.panda )),
        model(2, painterResource(id = R.drawable.panda )),
        model(1, painterResource(id = R.drawable.tiger )),
    )
    Task2Content(models)
}
