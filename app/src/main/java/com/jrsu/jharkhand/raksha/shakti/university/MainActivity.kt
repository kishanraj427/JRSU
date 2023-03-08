package com.jrsu.jharkhand.raksha.shakti.university

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.jrsu.jharkhand.raksha.shakti.university.screens.*
import com.jrsu.jharkhand.raksha.shakti.university.ui.theme.JRSUTheme
import com.onesignal.OneSignal

class MainActivity : ComponentActivity() {

    private val ONESIGNAL_APP_ID = "524d9166-7320-4874-93eb-2776e4f69e9b"

    @ExperimentalFoundationApi
    @ExperimentalCoilApi
    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        setContent {
            JRSUTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
                        composable(route = Screen.SplashScreen.route){ SplashScreen(navController) }
                        composable(route = Screen.HomeScreen.route){ HomeScreen(navController) }
                        composable(route = Screen.LogInScreen.route){ LogInScreen(navController) }
                        composable(route = Screen.SingUpScreen.route){ SignUpScreen(navController) }
                        composable(route = Screen.SyllabusScreen.route){ SyllabusScreen(navController) }
                    }
                }
            }
        }
    }
}