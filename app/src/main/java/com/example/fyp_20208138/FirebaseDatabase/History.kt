package com.example.fyp_20208138.FirebaseDatabase

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.mlkit.vision.label.ImageLabel

@IgnoreExtraProperties
data class History(val socialMedia: String? = null, var refId: String? = null) {

}