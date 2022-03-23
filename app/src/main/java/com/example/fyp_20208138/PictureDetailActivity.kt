package com.example.fyp_20208138

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fyp_20208138.ui.main.gallery.PictureDetail
import com.example.fyp_20208138.ui.theme.FYP_20208138Theme

class PictureDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val picId = intent.getStringExtra("picId")
        super.onCreate(savedInstanceState)
        setContent {
            FYP_20208138Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PictureDetail(picId)
                }
            }
        }
    }
}

@Composable
fun Greeting4(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    FYP_20208138Theme {
        Greeting4("Android")
    }
}