package com.example.fyp_20208138.ui.facebook

import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONArray

data class Comment (val timestamp: String, val text:String, val id:String)

object CommentListModel: ViewModel() {
    var commentList: MutableList<Comment> = mutableListOf<Comment>()
}
