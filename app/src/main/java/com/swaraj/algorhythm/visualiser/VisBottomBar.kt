package com.swaraj.algorhythm.visualiser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun VisBottomBar(
    modifier: Modifier = Modifier,
    playPauseClick:()->Unit,
    slowDownClick:()->Unit,
    speedUpClick:()->Unit,
    previousClick:()->Unit,
    nextClick:()->Unit,
    isPlaying:Boolean = false
){
    androidx.compose.material3.BottomAppBar(
        modifier = modifier,
        containerColor = Color.Transparent
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            IconButton(onClick = slowDownClick)
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Slow Down",
                        tint = Color.Black
                    )
                    //Text(text = "Slow Down")
                }
            }
            IconButton(onClick = playPauseClick)
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = if(!isPlaying) Icons.Default.PlayArrow else Icons.Default.Close,
                        contentDescription = "Play/Pause",
                        tint = Color.Black
                    )
                    //Text(text = if(!isPlaying) "Play" else "Pause")
                }
            }
            IconButton(onClick = speedUpClick)
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Speed Up",
                        tint = Color.Black
                    )
                    //Text(text = "Speed Up")
                }
            }
            IconButton(onClick = previousClick)
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous step",
                        tint = Color.Black
                    )

                    // Text(text = "Previous")
                }

            }
            IconButton(onClick = nextClick)
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Next Step",
                        tint = Color.Black
                    )

                    //Text(text = "Next")
                }
            }
        }
    }
}