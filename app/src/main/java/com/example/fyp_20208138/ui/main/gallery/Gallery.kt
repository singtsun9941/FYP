package com.example.fyp_20208138.ui.main.gallery


import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.fyp_20208138.FirebaseDatabase.Picture
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
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Gallery() {
    val context = LocalContext.current
    val databaseURL = context.getResources().getString(R.string.databaseURL)

//    getGalery(databaseURL)

    Scaffold(topBar = { TopAppBar(title = { Text("Gallery") }) }) {
        Text("Hello World")
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(4){

                Text(GalleryViewModel.pic.value.toString())

//                showPic()
            }

        }
    }
}

@Composable
private fun showPic() {
    Image(
        painter = rememberImagePainter("https://firebasestorage.googleapis.com/v0/b/fyp20208138.appspot.com/o/images%2Fmountains.jpg?alt=media&token=a4fc5e43-9517-480a-b372-ce23594478ff"),
        contentDescription = null,
        modifier = Modifier.size(128.dp)
    )
}