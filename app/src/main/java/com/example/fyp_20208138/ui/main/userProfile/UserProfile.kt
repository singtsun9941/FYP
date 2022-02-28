package com.example.fyp_20208138.ui.main.userProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun UserProfile() {
    Scaffold(topBar = { TopAppBar(title = { Text("Home") }) }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val user = Firebase.auth.currentUser
            user?.let {
                // Name, email address, and profile photo Url
                val name = user.displayName
                name?.let{
                    Text(name)
                }
                val email = user.email
                email?.let{
                    Text(email)
                }
                val photoUrl = user.photoUrl


                // Check if user's email is verified
                val emailVerified = user.isEmailVerified


                // The user's ID, unique to the Firebase project. Do NOT use this value to
                // authenticate with your backend server, if you have one. Use
                // FirebaseUser.getToken() instead.
                val uid = user.uid
                uid?.let{
                    Text(uid)
                }
            }
        }
    }
}