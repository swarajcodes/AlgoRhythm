package com.swaraj.algorhythm.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.swaraj.algorhythm.R
import com.swaraj.algorhythm.viewmodel.AlgorithmViewModel


@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Algorithm Visualiser",
                        color = Color.Black,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 40.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 8.dp, bottom = 24.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SortOption(navController = navController, algorithmName = "Bubble", screenname = "bubbleSort", complexity = "n\u00B2",image = "bubble")
            SortOption(navController = navController, algorithmName = "Insertion", screenname = "insertionSort", complexity = "n\u00B2", image = "insertion")
            SortOption(navController = navController, algorithmName = "Merge", screenname = "mergeSort", complexity = "nlogn", image = "merge")
            SortOption(navController = navController, algorithmName = "Quick", screenname = "quickSort", complexity = "nlogn", image = "quick")
            SortOption(navController = navController, algorithmName = "Selection", screenname = "selectionSort", complexity = "n\u00B2", image = "selection")
        }
    }
}

@SuppressLint("DiscouragedApi")
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

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)  // Increased overall height
            .clickable { navController.navigate(screenname) }
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
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
                Text(
                    text = "O($complexity)",
                    fontSize = 28.sp,  // Increased font size
                    lineHeight = 34.sp
                )
            }
        }
    }
}


