package com.example.fyp_20208138.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun Home() {
    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Home")
        }
    }
}