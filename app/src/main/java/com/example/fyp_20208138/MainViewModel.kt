package com.example.fyp_20208138

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class MainViewModel : ViewModel() {
    var currentUser by mutableStateOf<FirebaseUser?>(null)
        private set

    @JvmName("assignCurrentUser")
    fun setCurrentUser(user: FirebaseUser?) {
        currentUser = user
    }
}