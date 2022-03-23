package com.example.fyp_20208138.ui.main.gallery

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fyp_20208138.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.File



object GalleryViewModel: ViewModel() {
    var picNames:MutableState<JSONArray> = mutableStateOf(JSONArray())
    var pic:MutableState<JSONObject> = mutableStateOf(JSONObject())
    init {
        getGalery("https://fyp20208138-default-rtdb.asia-southeast1.firebasedatabase.app")
    }
}

fun test():String{
    return "test"
}

fun getGalery(
    databaseURL: String
){

    val database = Firebase.database(databaseURL)
    val googleUser = Firebase.auth.currentUser
    val myRef = database.getReference("Users/"+ googleUser?.uid + "/Gallery")

    val postListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            val value = dataSnapshot.getValue<Object>()
            Log.w("getGallery", "json"+ value.toString())
            // ...

            if (value!= null) {
                val json = Gson().toJson(value)
                val jsonObject = JSONObject(json)

                Log.w("getGallery", "" + jsonObject.names())

                val jsonArray = jsonObject.names()

                GalleryViewModel.picNames = mutableStateOf(jsonArray)
                GalleryViewModel.pic = mutableStateOf(jsonObject)
            }

        }
        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            Log.w("getGallery", "loadPost:onCancelled", databaseError.toException())
        }
    }
    myRef.addValueEventListener(postListener)
}