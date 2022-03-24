package com.example.fyp_20208138.ui.main.gallery

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.fyp_20208138.R
import com.example.fyp_20208138.ui.facebook.*
//import com.example.fyp_20208138.ui.facebook.PageListModel
import com.example.fyp_20208138.ui.labeling.uploadImage
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject


@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class,
    androidx.compose.material.ExperimentalMaterialApi::class
)
@Composable
fun PictureDetail(picId:String?) {
    val context = LocalContext.current
    val pics = GalleryViewModel.pic.value
    val picNames = GalleryViewModel.picNames.value
    val pageList = PageListModel.pageList

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


            LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
                items(pageList.size){index->
                    var page = pageList[index]
                    page.igId?.let {
                        Text(page.name)
                        CheckBox(page)
                    }
                }
            }

            Button(onClick = {
                if (activity != null) {
                    activity.finish()
                }
            }) {
                Text("Back")
            }
            Button(onClick = {
                pageList.forEach(){page ->
                    if(page.isPost){
                        page.igId?.let {
                            post(it!!, url)
                        }
                    }
                }
            }
            ) {
                Text("Post")
            }
        }


    }
}

@Composable
fun CheckBox(page:Page) {
    val checkedState = remember { mutableStateOf(page.isPost) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = {
            checkedState.value = it
            page.isPost = it
            Log.w("getPage", "isPost check"+ page.isPost)
        }
    )
}