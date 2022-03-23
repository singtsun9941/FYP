package com.example.fyp_20208138.ui.facebook

import android.os.Bundle
import android.util.Log
import com.facebook.AccessToken
import com.facebook.GraphRequest
import org.json.JSONObject


fun getInfo(){
    val request = GraphRequest.newMeRequest(
        AccessToken.getCurrentAccessToken()
    ) { `object`, response ->
        // Insert your code here

        Log.w("FacebookAPI","getInfo: "+response)
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
    }

    request.executeAsync()
}

fun getPageDetail(pageId:String){
    val request = GraphRequest.newGraphPathRequest(
        AccessToken.getCurrentAccessToken(),
        "/"+pageId
    ) {
        // Insert your code here
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
    }
    request.executeAsync()
}