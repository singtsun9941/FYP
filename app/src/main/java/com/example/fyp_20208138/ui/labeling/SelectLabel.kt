package com.example.fyp_20208138.ui.labeling

import android.content.Intent
import android.net.Uri
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.mlkit.vision.label.ImageLabel

@Composable
fun Selectlabel(labels:(List<ImageLabel>), uri:Uri){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberImagePainter(uri),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )

        labels.forEach{imageLabel ->
            Row() {
                Text(imageLabel.text)
                Text(imageLabel.confidence.toString())
            }
        }


    }
}