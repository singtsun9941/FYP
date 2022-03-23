package com.example.fyp_20208138.ui.main.home

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fyp_20208138.CameraActivity
import com.example.fyp_20208138.FacebookActivity
import kotlinx.coroutines.launch

//import com.example.fyp_20208138.MainActivity

@Composable

fun Home() {

    val context = LocalContext.current
    Scaffold(

        topBar = { TopAppBar(title = { Text("Home") }) }

    ) {


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Take photo")
            Button(onClick = {
                context.startActivity(Intent(context, CameraActivity::class.java))
            }) {
                Text("+")
            }
            Button(onClick = {
                context.startActivity(Intent(context, FacebookActivity::class.java))
            }) {
                Text("facebook")
            }

            ExtendedFloatingActionButton(
                onClick = { /* ... */ },
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                },
                text = { Text("Like") }
            )
        }

    }
}
