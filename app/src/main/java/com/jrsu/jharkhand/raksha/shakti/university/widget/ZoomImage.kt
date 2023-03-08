package com.jrsu.jharkhand.raksha.shakti.university.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun ZoomPhoto(url: String){
    var scale by remember { mutableStateOf(1f) }
    Image(
        rememberImagePainter(url),
        "",
        Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = when {
                            scale > 2f -> 1f
                            else -> 3f
                        }
                    }
                )
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale = when {
                        scale < 1f -> 1f
                        scale > 3f -> 3f
                        else -> scale * zoom
                    }
                }
            },
        contentScale = ContentScale.FillWidth
    )
}