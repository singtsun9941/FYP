package com.example.fyp_20208138.ui.main.gallery

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.fyp_20208138.R
import com.example.fyp_20208138.ui.labeling.uploadImage
import org.json.JSONObject


@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun PictureDetail(picId:String?) {
    val context = LocalContext.current
    val pics = GalleryViewModel.pic.value
    val picNames = GalleryViewModel.picNames.value
    val pic = pics.getJSONObject(picId)
    val url = pic.get("url").toString()

    val activity = (context as? Activity)
    val databaseUrl = context.getResources().getString(R.string.databaseURL)

    Scaffold(topBar = { TopAppBar(title = { Text("Picture Detail") }) }) {
        Column {
            Image(
                painter = rememberImagePainter(url),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Button(onClick = {
                if (activity != null) {
                    activity.finish()
                }
            }) {
                Text("Back")
            }
            Button(onClick = {
//            uploadImage(uri, labels)
//            uploadImage(uri, labels, databaseUrl)
            }) {
                Text("Post")
            }
        }


    }
}