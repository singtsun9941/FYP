package com.example.fyp_20208138.ui.facebook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fyp_20208138.ui.main.gallery.CheckBox
import com.facebook.AccessToken

@Composable
fun ShowInfo() {

    var facebookToken = AccessToken.getCurrentAccessToken()
    val pageList = PageListModel.pageList


    Column() {
        Text(text = "Hello!")
        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
            items(pageList.size){index->
                var page = pageList[index]

                page.igId?.let {
                    Row{
                        Text(page.name)
                        CheckBox(page)
                    }

                }
            }
        }
    }


}
