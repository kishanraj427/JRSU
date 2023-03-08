package com.jrsu.jharkhand.raksha.shakti.university.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jrsu.jharkhand.raksha.shakti.university.Objects.Colors
import com.jrsu.jharkhand.raksha.shakti.university.R
import com.jrsu.jharkhand.raksha.shakti.university.Screen
import com.jrsu.jharkhand.raksha.shakti.university.ui.theme.T
import com.jrsu.jharkhand.raksha.shakti.university.viewmodel.Fire
import com.onesignal.OneSignal


@Composable
fun SplashScreen(navController : NavController){

    val scale = remember{ Animatable(0f) }
    Colors.SetStatusColor(
        color = Colors.AppYellow
    )
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = { OvershootInterpolator(4f).getInterpolation(it)}
            )
        )
        if(Fire.firebaseAuth != null){
            val uid = OneSignal.getDeviceState()?.userId!!
            val map = mutableMapOf<String, String>().put("notificationId", uid)
            if (map != null) {
                Fire.firebaseStore.collection("Users").document(Fire.firebaseAuth?.uid!!)
                    .set(map)
            }
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.SplashScreen.route) { inclusive = true }
            }

        }
        else
            navController.navigate(Screen.LogInScreen.route) {
                popUpTo(Screen.SplashScreen.route){ inclusive = true }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Colors.AppYellow, Color.LightGray
                    )
                )
            ),
        Alignment.Center
    ){
        Image(
            painterResource(id = R.drawable.logo),
            "logo",
            Modifier.scale(scale.value).size(T.width(80f).dp)
        )
    }

}
