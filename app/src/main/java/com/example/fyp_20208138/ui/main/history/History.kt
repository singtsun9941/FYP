package com.example.fyp_20208138.ui.main.history

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.fyp_20208138.FirebaseDatabase.HistoryViewModel
import com.example.fyp_20208138.ui.main.gallery.CheckBox
import com.example.fyp_20208138.ui.main.gallery.GalleryViewModel

import org.json.JSONObject

@Composable
fun History() {
    val context = LocalContext.current
    val historyIds = HistoryViewModel.historyId.value
    val historys = HistoryViewModel.history.value
    Scaffold(

        topBar = { TopAppBar(title = { Text("History") }) }

    ) {
        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
            items(historyIds.length()) { index ->
                val historyId = historyIds[index].toString()
                var history: JSONObject = historys.getJSONObject(historyId)
                var refId: String = history.get("refId").toString()
                var socialMedia: String = history.get("socialMedia").toString()

                showHistory(socialMedia, refId)

            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun showHistory(socialMedia:String , refId: String){
    ListItem(secondaryText = {
//                    Text((item.latitude).toString())
    }, modifier = Modifier.clickable {

    }) {
        Text(text = socialMedia)
        Text(text = refId)
    }
    Divider()



}