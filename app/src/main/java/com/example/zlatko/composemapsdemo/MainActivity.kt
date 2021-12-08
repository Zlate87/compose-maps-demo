package com.example.zlatko.composemapsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zlatko.composemapsdemo.ui.theme.ComposeMapsDemoTheme
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "Screen1") {
            composable("Screen1") {
                Button({ navController.navigate("MapsScreen") }) {
                    Text(text = "Go to Maps")
                }
            }
            composable("MapsScreen") {
                val mapView = rememberMapViewWithLifecycle()
                AndroidView({ mapView }) { mapView ->
                    mapView.getMapAsync { map ->
                        map.setOnInfoWindowClickListener {
                            navController.navigate("Screen1")
                        }

                        val markerOptions = MarkerOptions()
                            .position(LatLng(41.390205, 2.154007))
                            .title("Barcelona")
                        map.addMarker(markerOptions)!!
                    }
                }
            }
            composable("Screen2") {
                Button({ navController.navigate("Screen1") }) {
                    Text(text = "Go Home")
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
    ComposeMapsDemoTheme {
        Greeting("Android")
    }
}