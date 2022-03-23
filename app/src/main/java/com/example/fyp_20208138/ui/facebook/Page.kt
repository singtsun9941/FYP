package com.example.fyp_20208138.ui.facebook

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Page (val access_token: String, val category: String,  val name: String, val id: String)
object PageListModel: ViewModel() {
    var pageList: MutableList<Page> = mutableListOf<Page>()
}
