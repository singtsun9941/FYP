package com.example.fyp_20208138.ui.main.gallery

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fyp_20208138.CameraActivity
import com.example.fyp_20208138.FacebookActivity

import org.json.JSONArray
import org.json.JSONObject

//@Composable
//fun SelectPage() {
//    val context = LocalContext.current
//    val pageList = pageListModel.pageList.value
//    Scaffold(
//
//        topBar = { TopAppBar(title = { Text("Home") }) }
//
//    ) {
//
//
//        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
//
//            items(pageList.length()) { index ->
//                val page = pageList[index].toString()
//                Log.w("getPage", "page"+ page)
//            }
//        }
//
//    }
//}