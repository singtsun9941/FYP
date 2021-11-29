package com.example.fyp_20208138

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.fyp_20208138.ui.nav.Nav
import com.example.fyp_20208138.ui.theme.FYP_20208138Theme
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : ComponentActivity() {

    private lateinit var analytics: FirebaseAnalytics

    @ExperimentalMaterialApi
    @SuppressLint("InvalidAnalyticsName")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = FirebaseAnalytics.getInstance(this)

        setContent {
            FYP_20208138Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Nav()

//                    //Test FireBase
//                    Button(onClick = {
//                        analytics.logEvent("button_clicked", null)
//                    }) {
//
//                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FYP_20208138Theme {
        Greeting("Android")
    }
}