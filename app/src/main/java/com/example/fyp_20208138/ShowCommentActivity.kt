package com.example.fyp_20208138

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fyp_20208138.ui.facebook.Comment
import com.example.fyp_20208138.ui.facebook.CommentListModel
import com.example.fyp_20208138.ui.facebook.PageListModel
import com.example.fyp_20208138.ui.facebook.getComment
import com.example.fyp_20208138.ui.theme.FYP_20208138Theme
import java.util.*
import kotlin.concurrent.schedule

class ShowCommentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val commentList = CommentListModel.commentList
            setContent {
                FYP_20208138Theme {
                    // A surface container using the 'background' color from the theme
                    Surface(

                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Scaffold(topBar = { TopAppBar(title = { Text("Comment") }) }) {
                            LazyColumn {
                                items(commentList.size) { index ->
                                    showComment(commentList, index)
                                }
                            }
                        }

                    }
                }
            }
        
    }
}

@Composable
fun Greeting6(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    FYP_20208138Theme {
        Greeting6("Android")
    }
}

@Composable
fun showComment(commentList:MutableList<Comment> , index:Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)

        ,
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = commentList[index].text)
            Text(text = commentList[index].timestamp)
        }
    }

}