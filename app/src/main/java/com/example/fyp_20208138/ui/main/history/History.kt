package com.example.fyp_20208138.ui.main.history

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fyp_20208138.CameraActivity
import com.example.fyp_20208138.FacebookActivity
import com.example.fyp_20208138.FirebaseDatabase.HistoryViewModel
import com.example.fyp_20208138.ui.main.gallery.CheckBox
import com.example.fyp_20208138.ui.main.gallery.GalleryViewModel
import com.example.fyp_20208138.ui.main.topbar
import kotlinx.coroutines.launch

import org.json.JSONObject

@Composable
fun History() {
    val context = LocalContext.current
    val historyIds = HistoryViewModel.historyId.value
    val historys = HistoryViewModel.history.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {

        },
        topBar = {
            TopAppBar(
                title = { Text("History" )},
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
        }

    ) {
        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
            items(historyIds.length()) { index ->
                val historyId = historyIds[index].toString()
                var history: JSONObject = historys.getJSONObject(historyId)
                var refId: String = history.get("refId").toString()
                var socialMedia: String = history.get("socialMedia").toString()
                var date: String = history.get("date").toString()
                var msg: String = history.get("msg").toString()

                showHistory(socialMedia, refId, date, msg)

            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun showHistory(socialMedia:String , refId: String, date:String , msg:String){
    ListItem(secondaryText = {
//                    Text((item.latitude).toString())
    }, modifier = Modifier.clickable {

    }) {
        Row() {
            Text(text = date)
            Text(text = msg)
        }

        Row() {
            Text(text = socialMedia)
            Text(text = refId)
        }

    }
    Divider()



}