package com.example.fyp_20208138.FirebaseDatabase

import com.google.firebase.database.IgnoreExtraProperties
import com.google.mlkit.vision.label.ImageLabel

@IgnoreExtraProperties
data class Picture(val url: String? = null, var label: List<ImageLabel>? = null) {
}