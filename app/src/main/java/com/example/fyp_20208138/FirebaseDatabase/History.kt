package com.example.fyp_20208138.FirebaseDatabase

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.mlkit.vision.label.ImageLabel
import org.json.JSONArray
import org.json.JSONObject

@IgnoreExtraProperties
data class History(val socialMedia: String? = null, var refId: String? = null) {

}

object HistoryViewModel: ViewModel() {
    var historyId: MutableState<JSONArray> = mutableStateOf(JSONArray())
    var history: MutableState<JSONObject> = mutableStateOf(JSONObject())

}