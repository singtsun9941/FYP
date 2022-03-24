package com.example.fyp_20208138.FirebaseDatabase

import com.google.mlkit.vision.label.ImageLabel

data class label(val text: String, var confidence: String, var isPost:Boolean = false)