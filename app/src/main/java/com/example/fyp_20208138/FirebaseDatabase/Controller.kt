package com.example.fyp_20208138.FirebaseDatabase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

fun addHistory(postFrom:String, mediaId:String){
    val googleUser = Firebase.auth.currentUser
    val database = Firebase.database("https://fyp20208138-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef = database.getReference("Users/"+ googleUser?.uid + "/History").push()
    val pic = History(postFrom, mediaId)
    myRef.setValue(pic)
}