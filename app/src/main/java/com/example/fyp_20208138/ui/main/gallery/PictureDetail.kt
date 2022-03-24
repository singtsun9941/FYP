package com.example.fyp_20208138.ui.main.gallery

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.fyp_20208138.FirebaseDatabase.label
import com.example.fyp_20208138.R
import com.example.fyp_20208138.ui.facebook.*
//import com.example.fyp_20208138.ui.facebook.PageListModel
import com.example.fyp_20208138.ui.labeling.uploadImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.mlkit.vision.label.ImageLabel
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
    val picJsonString = pic.get("label").toString()
    val labels:List<label> = Gson().fromJson(picJsonString, object : TypeToken<List<label>>() {}.type)

    val activity = (context as? Activity)
    val databaseUrl = context.getResources().getString(R.string.databaseURL)

    var msg by remember { mutableStateOf("") }

    Scaffold(topBar = { TopAppBar(title = { Text("Picture Detail") }) }) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
//                .verticalScroll(rememberScrollState())
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = rememberImagePainter(url),
                contentDescription = null,
                modifier = Modifier.height(200.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                value = msg,
                onValueChange = { msg = it },
                label = { Text("Message") }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Log.w("getPage", "picJsonString"+ picJsonString)
            labelFromAI(labels)

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn{

                pageList?.let{
                    items(pageList.size) { index ->
                        var page = pageList[index]

                        page.igId?.let {
                            Row {
                                Text(page.name)
                                CheckBox(page)
                            }

                        }
                    }
                }


            }


            Spacer(modifier = Modifier.height(15.dp))
            Row{
                Button(onClick = {
                    if (activity != null) {
                        activity.finish()
                    }
                }) {
                    Text("Back")
                }
                Spacer(modifier = Modifier.width(30.dp))
                Button(onClick = {
                    pageList.forEach(){page ->
                        if(page.isPost){
                            page.igId?.let {
                                Log.w("Post", "msg"+ msg)
                                post(it!!, url , msg, toCaption(labels))
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



@Composable
fun CheckBoxForLabel(label:label) {
    val checkedState = remember { mutableStateOf(label.isPost) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = {
            checkedState.value = it
            label.isPost = it
            Log.w("getPage", "isPost check"+ label.isPost)
        }
    )
}

@Composable
fun labelFromAI(labels:List<label>){
    LazyColumn{
        
        items(labels.size){index ->
            var label = labels[index]
            Log.w("getPage", "label: "+ label.text)
            Row() {
                CheckBoxForLabel(label)
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = label.text)
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = label.confidence)
            }

        }

    }
}

fun toCaption(labels: List<label>):String{
    var caption:String = ""
    labels.forEach(){
        if(it.isPost) {
            caption += "#" + it.text
        }
    }
    return caption
}