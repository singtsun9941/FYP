package com.example.fyp_20208138.FirebaseDatabase

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.fyp_20208138.ui.main.gallery.GalleryViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

fun addHistory(postFrom:String,msg:String, mediaId:String){
    val googleUser = Firebase.auth.currentUser
    val database = Firebase.database("https://fyp20208138-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef = database.getReference("Users/"+ googleUser?.uid + "/History").push()
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())
    System.out.println(" C DATE is  "+currentDate)

    val pic = History(postFrom, msg,mediaId, currentDate)
    myRef.setValue(pic)
}

fun getHistory(
    databaseURL: String
){
    val TAG = "History"

    val database = Firebase.database(databaseURL)
    val googleUser = Firebase.auth.currentUser
    val myRef = database.getReference("Users/"+ googleUser?.uid + "/History")

    val postListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            val value = dataSnapshot.getValue<Object>()
            Log.w(TAG, "json"+ value.toString())
            // ...

            if (value!= null) {
                val json = Gson().toJson(value)
                val jsonObject = JSONObject(json)
                Log.w(TAG, "" + jsonObject.names())

                val jsonArray = jsonObject.names()

                HistoryViewModel.historyId = mutableStateOf(jsonArray)
                HistoryViewModel.history = mutableStateOf(jsonObject)
            }

        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
        }
    }
    myRef.addValueEventListener(postListener)
}