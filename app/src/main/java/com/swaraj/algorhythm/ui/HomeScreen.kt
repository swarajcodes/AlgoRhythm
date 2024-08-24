package com.swaraj.algorhythm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.swaraj.algorhythm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    androidx.compose.material3.Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {
                    Text(
                        "AlgoRhythm",
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        //modifier = Modifier.padding(start = 107.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        containerColor = colorResource(id = R.color.white)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 8.dp, bottom = 24.dp),
                //.background(colorResource(id = R.color.teal)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SortOption(navController = navController, algorithmName = "Bubble Sort", screenname = "bubbleSort", complexity = "n\u00B2",image = "bubble3")
            SortOption(navController = navController, algorithmName = "Insertion Sort", screenname = "insertionSort", complexity = "n\u00B2", image = "insertion3")
            SortOption(navController = navController, algorithmName = "Merge Sort", screenname = "mergeSort", complexity = "nlogn", image = "merge3")
            SortOption(navController = navController, algorithmName = "Quick Sort", screenname = "quickSort", complexity = "nlogn", image = "quick3")
            SortOption(navController = navController, algorithmName = "Selection Sort", screenname = "selectionSort", complexity = "n\u00B2", image = "selection3")
        }
    }
}

@Composable
fun SortOption(
    navController: NavController,
    algorithmName: String,
    complexity: String,
    screenname: String,
    image: String
) {
    val context = LocalContext.current
    val imageResId = remember(image) {
        context.resources.getIdentifier(image, "drawable", context.packageName)
    }

    androidx.compose.material3.Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { navController.navigate(screenname) }
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(160.dp)  // Increased image size
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(24.dp))  // Increased spacing

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = algorithmName,
                    fontSize = 24.sp,  // Increased font size
                    fontWeight = FontWeight.Bold,
                    lineHeight = 38.sp
                )

                Spacer(modifier = Modifier.height(12.dp))
//                Text(
//                    text = "O($complexity)",
//                    fontSize = 28.sp,  // Increased font size
//                    lineHeight = 34.sp
//                )
            }
        }
    }
}


