package com.swaraj.algorhythm.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.swaraj.algorhythm.Algorithm.SortingAlgorithm
import com.swaraj.algorhythm.viewmodel.AlgorithmViewModel

class AlgorithmViewModelProvider(
    private val sortingAlgorithm: SortingAlgorithm
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlgorithmViewModel::class.java)) {
            return AlgorithmViewModel(sortingAlgorithm) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}