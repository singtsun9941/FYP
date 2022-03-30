package com.example.fyp_20208138

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.fyp_20208138.FirebaseDatabase.getHistory
import com.example.fyp_20208138.ui.facebook.getPages
import com.example.fyp_20208138.ui.main.gallery.getGalery
import com.example.fyp_20208138.ui.main.nav.Nav
import com.example.fyp_20208138.ui.theme.FYP_20208138Theme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.concurrent.schedule

class StartActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
    // Choose authentication providers
    val providers = arrayListOf(
//        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build())
    // Create and launch sign-in intent
    val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = Firebase.auth.currentUser
        if(user != null) {
            loadData()
            setContent {
                FYP_20208138Theme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Greeting5("Loading")
                    }
                }
            }
        }else{
            signInLauncher.launch(signInIntent)
        }
    }

    private fun loadData() {
        val databaseUrl:String = "https://fyp20208138-default-rtdb.asia-southeast1.firebasedatabase.app"
        getGalery(databaseUrl)
        getHistory(databaseUrl)
        getPages()
    }

    @OptIn(ExperimentalMaterialApi::class)
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            Log.i("Google Auth","Successfully signed in")

            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            Log.i("Google Auth","user: " + user)
            // ...
            loadData()
            setContent {
                FYP_20208138Theme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        Greeting5("Loading")
                    }
                }
            }


        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
            signInLauncher.launch(signInIntent)
        }
    }
}

@Composable
fun Greeting5(name: String) {
    val context = LocalContext.current
    Text(text = " $name!")
    Timer().schedule(5000) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    FYP_20208138Theme {
        Greeting5("Android")
    }
}