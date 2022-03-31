package com.example.fyp_20208138.ui.main.userProfile

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.fyp_20208138.FacebookActivity
import com.example.fyp_20208138.ui.main.history.showHistory
import com.example.fyp_20208138.ui.main.nav.NavItem
import com.example.fyp_20208138.ui.theme.FYP_20208138Theme
import com.example.fyp_20208138.ui.theme.Purple700
import com.example.fyp_20208138.ui.theme.Teal200
import com.facebook.AccessToken
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.json.JSONObject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerProfile(context: Context) {
//    (horizontalAlignment = Alignment.CenterHorizontally)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Acount") },

            ) },
//        bottomBar = {
//            BottomAppBar(Modifier.background(color = Teal200)) {
//            /* Bottom app bar content */
//
//            }
//        }
    ){
        Column {
            val user = Firebase.auth.currentUser
            user?.let {
                // Name, email address, and profile photo Url
                Row(modifier = Modifier.padding(all = 8.dp)) {
                    val photoUrl = user.photoUrl
                    photoUrl?.let {
                        Image(
                            painter = rememberImagePainter(photoUrl),
                            contentDescription = null,
                            modifier = Modifier
                                // Set image size to 40 dp
                                .size(40.dp)
                                // Clip image to be shaped as a circle
                                .clip(CircleShape)
                        )
                    }

                    // Add a horizontal space between the image and the column
                    Spacer(modifier = Modifier.width(8.dp))

                    Column {
                        val name = user.displayName
                        name?.let {
                            Text(name)
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        val email = user.email
                        email?.let {
                            Text(email)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        AuthUI.getInstance()
                            .signOut(context)

                    }) {
                        Text("Log out")
                    }
                }

    //            // Check if user's email is verified
    //            val emailVerified = user.isEmailVerified


            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier =  Modifier.background(MaterialTheme.colors.secondary)) {
                Text("Social Media", modifier =  Modifier.fillMaxWidth().padding(8.dp))
            }
            LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {

                SocialMediaItem.data.forEach() {

                    item {
                        ListItem(secondaryText = {
    //                    Text((item.latitude).toString())
                        }, modifier = Modifier.clickable {
                            context.startActivity(
                                Intent(
                                    context,
                                    FacebookActivity::class.java
                                ).putExtra("isLinked", it.isLinked.toString())
                            )
                        }) {
                            Row {
                                Text(text = it.name)
                                Spacer(modifier = Modifier.width(30.dp))
                                if(it.isLinked){
                                    Text( text = "Linked")
                                }else{
                                    Text( text = "Login")
                                }
                                


                            }
                        }


                    }
                }
            }

        }

    }
}


data class SocialMediaItem(

    val name: String,
    val isLinked: Boolean,
    val icon: String? = null,

) {
    companion object {
        val data = listOf(
            SocialMediaItem("Facebook/Instagram", isLinkedFacebook()),
//            SocialMediaItem(),


        )
    }
}

fun isLinkedFacebook():Boolean{
    return AccessToken.getCurrentAccessToken()!=null
}