package com.swaraj.algorhythm.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.swaraj.algorhythm.Algorithm.BubbleSort
import com.swaraj.algorhythm.Algorithm.InsertionSort
import com.swaraj.algorhythm.Algorithm.MergeSort
import com.swaraj.algorhythm.Algorithm.QuickSort
import com.swaraj.algorhythm.Algorithm.SelectionSort
import com.swaraj.algorhythm.Algorithm.SortingAlgorithm
import com.swaraj.algorhythm.data.AlgorithmViewModelProvider
import com.swaraj.algorhythm.viewmodel.AlgorithmViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeScreen(navController)
        }
        composable("Bubble Sort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(BubbleSort())
            )
            AlgorithmInfoScreen(navHostController = navController, viewModel = viewModel, algorithmName = "Bubble Sort")
        }
        composable("mergeSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(MergeSort())
            )
            AlgorithmInfoScreen(navController, viewModel, "Merge Sort")
        }
        composable("quickSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(QuickSort())
            )
            AlgorithmInfoScreen(navController, viewModel, "Quick Sort")
        }
        composable("selectionSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(SelectionSort())
            )
            AlgorithmInfoScreen(navController, viewModel, "Selection Sort")
        }
        composable("insertionSort") {
            val viewModel: AlgorithmViewModel = viewModel(
                factory = AlgorithmViewModelProvider(InsertionSort())
            )
            AlgorithmInfoScreen(navController, viewModel, "Insertion Sort")
        }
        // Add more routes for other algorithms as needed
    }
}