package com.example.fyp_20208138.ui.main.home

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.fyp_20208138.CameraActivity
//import com.example.fyp_20208138.MainActivity

@Composable
fun Home() {
    val context = LocalContext.current
    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Take photo")
            Button(onClick = {
                context.startActivity(Intent(context, CameraActivity::class.java))
            }) {
                Text("+")
            }
        }
    }
}
