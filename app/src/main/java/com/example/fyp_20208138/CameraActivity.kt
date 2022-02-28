package com.example.fyp_20208138

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.fyp_20208138.ui.theme.FYP_20208138Theme
import com.example.fyp_20208138.ui.camera.CameraView
import com.example.fyp_20208138.ui.labeling.Selectlabel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import java.io.IOException

class CameraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        if (allPermissionsGranted()) {
            setContent {
                FYP_20208138Theme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        CameraView(onImageCaptured = { uri, fromGallery ->
                            Log.d(TAG, "Image Uri Captured from Camera View")
                            //Todo : use the uri as needed
                            val image: InputImage
                            try {
                                image = InputImage.fromFilePath(this, uri)

                                val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

                                labeler.process(image)
                                    .addOnSuccessListener { labels ->
                                        // Task completed successfully
                                        // ...
                                        Log.d("MLKit_Vision","Average luminosity: $labels")
                                        setContent {
                                            FYP_20208138Theme {
                                                // A surface container using the 'background' color from the theme
                                                Surface(color = MaterialTheme.colors.background) {
                                                    Selectlabel(labels, uri)
                                                }
                                            }
                                        }

                                    }
                                    .addOnFailureListener { e ->
                                        // Task failed with an exception
                                        // ...
                                        Log.e("MLKit_Vision","Task failed with an exception: $e")
                                    }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }



                        }, onError = { imageCaptureException ->
                            Log.e(TAG, "An error occurred while trying to take a picture")

                        })
                    }
                }
            }
        } else {
            Toast.makeText(this,
                "Permissions not granted by the user.",
                Toast.LENGTH_SHORT).show()
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FYP_20208138Theme {
        Greeting2("Android")
    }
}