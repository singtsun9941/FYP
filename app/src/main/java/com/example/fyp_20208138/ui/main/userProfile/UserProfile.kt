package com.example.fyp_20208138.ui.main.userProfile

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.fyp_20208138.CameraActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun UserProfile() {
    val context = LocalContext.current

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

            Button(onClick = {
                AuthUI.getInstance()
                    .signOut(context)

            }) {
                Text("Log out")
            }
        }
    }
}