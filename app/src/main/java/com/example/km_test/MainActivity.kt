package com.example.km_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.km_test.ui.screens.screen1.FirstScreen
import com.example.km_test.ui.screens.screen1.FirstScreenViewModel
import com.example.km_test.ui.screens.screen2.SecondScreen
import com.example.km_test.ui.screens.screen3.ThirdScreen
import com.example.km_test.ui.theme.KM_testTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT),
        )
        setContent {
            KM_testTheme {
                val navController = rememberNavController()
                val viewModel: FirstScreenViewModel = hiltViewModel()
                NavHost(navController = navController, startDestination = "first_screen") {
                    composable("first_screen") { FirstScreen(navController, viewModel) }
                    composable("second_screen") { SecondScreen(navController, viewModel) }
                    composable("third_screen") { ThirdScreen(navController)}
                }
            }
        }
    }
}

