package com.example.fyp_20208138.mlkit

import com.google.mlkit.common.model.CustomRemoteModel
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.linkfirebase.FirebaseModelSource

val localModel = LocalModel.Builder()
    .setAssetFilePath("model.tflite")
    // or .setAbsoluteFilePath(absolute file path to model file)
    // or .setUri(URI to model file)
    .build()

// Specify the name you assigned in the Firebase console.
val remoteModel =
    CustomRemoteModel
        .Builder(FirebaseModelSource.Builder("your_model_name").build())
        .build()