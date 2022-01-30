package com.example.fyp_20208138.ui.userProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fyp_20208138.MainViewModel


@Composable
fun UserProfile(viewModel: MainViewModel, onSignIn: () -> Unit, onSignOut: () -> Unit) {
    val currentUser = viewModel.currentUser

    Column(modifier = Modifier.padding(16.dp)) {
        if (currentUser == null) {
            Button(onClick = { onSignIn() }){
                Text(text = "Sign in with Google")
            }
        }
        else {
            Text(text = "Welcome, ${currentUser.displayName}")
            Button(onClick = { onSignOut() } ) {
                Text(text = "Sign out")
            }
        }
    }
}