package com.raytrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.raytrack.ui.navigation.NavController
import com.raytrack.ui.theme.RayTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RayTrackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavController(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
