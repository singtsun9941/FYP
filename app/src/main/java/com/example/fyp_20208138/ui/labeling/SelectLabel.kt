package com.example.fyp_20208138.ui.labeling

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.fyp_20208138.FirebaseDatabase.Picture
import com.example.fyp_20208138.FirebaseDatabase.User
import com.example.fyp_20208138.MainActivity
import com.example.fyp_20208138.R
import com.example.fyp_20208138.ui.main.history.LoadingDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.mlkit.vision.label.ImageLabel
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.concurrent.schedule


@Composable
fun Selectlabel(labels:(List<ImageLabel>), uri:Uri){
    val context = LocalContext.current
    val activity = (context as? Activity)
    val databaseUrl = context.getResources().getString(R.string.databaseURL)
    val openDialog = remember { mutableStateOf(false)  }

    Column(
        modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberImagePainter(uri),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )


        Spacer(modifier = Modifier.height(30.dp))
        labels.forEach{imageLabel ->
            Row() {
                Text(imageLabel.text)
                Spacer(modifier = Modifier.width(30.dp))
                val format = DecimalFormat("0.###")
                format.roundingMode = RoundingMode.FLOOR
                Text(format.format(imageLabel.confidence.toDouble()))
            }
        }
        Row() {
            Button(onClick = {
                if (activity != null) {
                    activity.finish()
                }
            }) {
                Text("Cancel")
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(onClick = {
                openDialog.value = true
                uploadImage(uri, labels, databaseUrl)
                Timer().schedule(4000) {
                    openDialog.value = false
                    if (activity != null) {
                        activity.finish()
                    }
                }


            }) {
                Text("Save")
            }
        }

        LoadingDialog(openDialog)



    }
}

fun uploadToDatabase(url:String, labels: List<ImageLabel>, databaseUrl: String){
    val googleUser = Firebase.auth.currentUser
    val database = Firebase.database(databaseUrl)
    val myRef = database.getReference("Users/"+ googleUser?.uid + "/Gallery").push()
    val pic = Picture(url, labels)
    myRef.setValue(pic)
}

fun uploadImage(uri:Uri, labels: List<ImageLabel>, databaseUrl: String): Uri? {
    val storage = Firebase.storage
    val storageRef = storage.reference

    val riversRef = storageRef.child("images/${uri.lastPathSegment}")
    var uploadTask = riversRef.putFile(uri)

    var downloadUri: Uri? = null

// Register observers to listen for when the download is done or if it fails
    uploadTask.addOnFailureListener {
        // Handle unsuccessful uploads
        Log.e("Firebase_Storage","unsuccessful uploads")
    }.addOnSuccessListener { taskSnapshot ->
        // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
        // ...
        Log.w("Firebase_Storage","successful uploads")

    }.continueWithTask { task ->
        if (!task.isSuccessful) {
            task.exception?.let {
                throw it
            }
        }
        riversRef.downloadUrl
    }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            downloadUri = task.result
            Log.w("Firebase_Storage","downloadUri: "+downloadUri)
            uploadToDatabase(downloadUri.toString(), labels, databaseUrl)

        } else {
            // Handle failures
            Log.e("Firebase_Storage","unsuccessful get url")
        }
    }
    return downloadUri
}