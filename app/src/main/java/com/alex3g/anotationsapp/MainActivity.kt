package com.alex3g.anotationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alex3g.anotationsapp.ui.screens.AddNoteScreen
import com.alex3g.anotationsapp.ui.screens.HomeScreen
import com.alex3g.anotationsapp.ui.theme.AnotationsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnotationsAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onAddClick = { navController.navigate("add-note") }
                        )
                    }
                    composable("add-note") {
                        AddNoteScreen(
                            onHomeClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } }
                        )
                    }
                }
            }
        }
    }
}