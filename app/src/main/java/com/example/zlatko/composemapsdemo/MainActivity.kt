package com.example.zlatko.composemapsdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Screen1") {
                composable("Screen1") {
                    Button({ navController.navigate("MapsScreen") }) {
                        Text(text = "Go to Maps")
                    }
                }
                composable("MapsScreen") {
                    val button = android.widget.Button(applicationContext)

                    AndroidView({ button }) { button ->
                        button.text = "Go to Screen1"
                        button.setOnClickListener { navController.navigate("Screen1") }
                    }
                }
            }
        }
    }
}