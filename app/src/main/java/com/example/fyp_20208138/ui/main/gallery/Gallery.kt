package com.example.fyp_20208138.ui.main.gallery


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.fyp_20208138.FacebookActivity
import com.example.fyp_20208138.FirebaseDatabase.Picture
import com.example.fyp_20208138.PictureDetailActivity
import com.example.fyp_20208138.R
import com.example.fyp_20208138.ui.main.topbar
import com.example.fyp_20208138.ui.main.userProfile.DrawerProfile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun Gallery() {
    val context = LocalContext.current
    val databaseURL = context.getResources().getString(R.string.databaseURL)
    val pics = GalleryViewModel.pic.value
    val picNames = GalleryViewModel.picNames.value

    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
//    getGalery(databaseURL)

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerProfile(context = context)
        },
        topBar = {
            TopAppBar(
                title = { Text("Gallery" )},
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(Icons.Filled.AccountBox, "Account")
                    }
                },
            )
        }) {
        Column {
            Text("${picNames.length()} Pictures Founded")
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 128.dp)
            ) {
                items(picNames.length()) { index ->
                    val picId = picNames[index].toString()
                    var pic: JSONObject = pics.getJSONObject(picId)
                    var url: String = pic.get("url").toString()

                    showPic(picId, url, context)
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun showPic(picId:String, url: String, context: Context) {
    Log.w("getGallery", "url"+url)
    Spacer(Modifier.height(8.dp))
    ListItem(secondaryText = {
        Card(elevation = 8.dp) {
            Image(
                painter = rememberImagePainter(url),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }
    }, modifier = Modifier.clickable {
        context.startActivity(Intent(context, PictureDetailActivity::class.java).putExtra("picId",picId))

    }
    ){
//                    Text(item.name)
    }

}