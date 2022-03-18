package com.example.fyp_20208138.ui.facebook

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.platform.LocalContext
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.util.*


@Composable
fun LoginScreen(LocalFacebookCallbackManager: ProvidableCompositionLocal<CallbackManager>) {
    val callbackManager = LocalFacebookCallbackManager.current
    DisposableEffect(Unit) {
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    println("onSuccess $loginResult")
                    val accessToken = AccessToken.getCurrentAccessToken()
                    val isLoggedIn = accessToken != null && !accessToken.isExpired
                    Log.w("FacebookAPI","isLoggedIn: "+isLoggedIn+" "+accessToken)
                }

                override fun onCancel() {
                    println("onCancel")
                    Log.w("FacebookAPI","onCancel")
                }

                override fun onError(exception: FacebookException) {
                    println("onError $exception")
                    Log.e("FacebookAPI",exception.toString())
                }
            }
        )
        onDispose {
            LoginManager.getInstance().unregisterCallback(callbackManager)
        }
    }
    val context = LocalContext.current
    Button(onClick = {
        LoginManager.getInstance()
            .logInWithReadPermissions(context.findActivity(), Arrays.asList("public_profile","instagram_basic","pages_show_list","business_management","instagram_basic","pages_read_engagement","instagram_content_publish"));
        LoginManager.getInstance()
            .logInWithPublishPermissions(context.findActivity(), Arrays.asList("ads_management"));

    }) {
        Text("FB Login")
    }

}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}