package com.swaraj.algorhythm.Algorithm

interface SortingAlgorithm {
    suspend fun sort(array: IntArray, onSwap: (IntArray) -> Unit)
}

