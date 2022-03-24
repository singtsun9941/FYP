package com.example.fyp_20208138.ui.facebook

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fyp_20208138.FirebaseDatabase.addHistory
import com.example.fyp_20208138.ui.main.gallery.GalleryViewModel
import com.example.fyp_20208138.ui.main.gallery.getGalery
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception


fun getInfo(){
    val request = GraphRequest.newMeRequest(
        AccessToken.getCurrentAccessToken()
    ) { `object`, response ->
        // Insert your code here
        Log.w("FacebookAPI","response: "+response)
        var jsonObject = response.jsonObject
        Log.w("FacebookAPI","getInfo: "+jsonObject)
//        var jsonObject = response.jsonObject

    }

    val parameters = Bundle()
    parameters.putString("fields", "id,name")
    request.parameters = parameters
    request.executeAsync()
}

fun getPages(){
    val request = GraphRequest.newGraphPathRequest(
        AccessToken.getCurrentAccessToken(),
        "/me/accounts"
    ) {
        // Insert your code here
        Log.w("FacebookAPI","response: "+it)
        Log.w("FacebookAPI","getInfo: "+it.jsonObject)
//        PageListModel.pageList = mutableStateOf(it.jsonObject)
        var pageList:List<Page> = Gson().fromJson(it.jsonObject.get("data").toString(), object : TypeToken<List<Page>>() {}.type)
        Log.w("FacebookAPI","pageList[0]: "+pageList.toString())
        val mutablePage:MutableList<Page> = mutableListOf<Page>().apply {addAll(pageList)}
        PageListModel.pageList = mutablePage

        loadIgId(mutablePage)


    }

    request.executeAsync()
}

//fun getPageDetail(pageId:String, url: String ){
fun getPageDetail(page: Page, pageId:String){
    val request = GraphRequest.newGraphPathRequest(
        AccessToken.getCurrentAccessToken(),
        "/"+pageId
    ) {
        // Insert your code here
        //jsonObject.name() cehck instagram_business_account
        try {
            val igId = JSONObject(it.jsonObject.get("instagram_business_account").toString()).get("id").toString()
            Log.w("FacebookAPI","igId: "+igId)
            page.igId = igId
//        post(igId, url)
        }catch (e:Exception){
            Log.e("FacebookAPI",e.toString())
        }

    }

    val parameters = Bundle()
    parameters.putString("fields", "instagram_business_account")
    request.parameters = parameters
    request.executeAsync()
}

fun post(igId:String, url:String){
    val request = GraphRequest.newPostRequest(
        AccessToken.getCurrentAccessToken(),
        "/"+igId+"/media",
        JSONObject("{\"image_url\":\"" + url+ "\"}")
    ) {
        // Insert your code here
        val creation_id = it.jsonObject.get("id").toString()
        Log.w("FacebookAPI","creation_id: "+creation_id)
        publish(igId, creation_id)
    }
    request.executeAsync()
}
fun publish(igId:String,creation_id:String){
    val request = GraphRequest.newPostRequest(
        AccessToken.getCurrentAccessToken(),
        "/"+igId+"/media_publish",
        JSONObject("{\"creation_id\":\"" +creation_id+ "\"}")
    ) {
        // Insert your code here
        val mediaId:String = it.jsonObject.get("id").toString()
        Log.w("FacebookAPI","mediaId: "+mediaId)
        addHistory("IG", mediaId)

    }
    request.executeAsync()
}

fun loadIgId(mutablePage:MutableList<Page>){
    mutablePage.forEach(){page ->
        page.getIgId()
    }
}