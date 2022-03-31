package com.example.fyp_20208138.ui.facebook

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.fyp_20208138.ui.main.gallery.CheckBox
import com.facebook.AccessToken
import com.facebook.Profile
import com.facebook.login.LoginManager

@Composable
fun ShowInfo() {

    var facebookToken = AccessToken.getCurrentAccessToken()
    var profile = Profile.getCurrentProfile()
    val pageList = PageListModel.pageList
    val context = LocalContext.current
    val activity = (context as? Activity)


    Column() {

//        Image(
//            painter = getUserIcon(profile.id),
//            contentDescription = null,
//            modifier = Modifier
//                // Set image size to 40 dp
//                .size(40.dp)
//                // Clip image to be shaped as a circle
//                .clip(CircleShape)
//        )

        Text(text = "Hello!")
        Text(text = profile.name)

//        Text(text = profile.linkUri.)


        Text(text = "Instagram")
        LazyColumn(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 64.dp)) {
            items(pageList.size){index->
                var page = pageList[index]

                page.igId?.let {
                    Row{
                        Text(page.name)
                    }

                }
            }
        }

        Button(onClick = {
            LoginManager.getInstance().logOut()
            if (activity != null) {
                activity.finish()
            }
        }) {
            Text(text = "Logout")

        }
    }


}
