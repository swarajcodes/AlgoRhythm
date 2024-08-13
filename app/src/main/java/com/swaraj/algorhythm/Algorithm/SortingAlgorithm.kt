package com.swaraj.algorhythm.Algorithm

interface SortingAlgorithm {
    suspend fun sort(array: IntArray, onSwap: (IntArray) -> Unit)
}

class BubbleSort :SortingAlgorithm
{

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ) {

        val n = arr.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                    onSwap(arr)
                }
            }
        }
    }
}

class InsertionSort : SortingAlgorithm {

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ) {
        val n = arr.size
        for (i in 1 until n) {
            val key = arr[i]
            var j = i - 1

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]
                j -= 1
                onSwap(arr)
            }
            arr[j + 1] = key
            onSwap(arr)
        }
    }
}

class MergeSort : SortingAlgorithm {

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ) {
        val aux = arr.clone()
        mergeSort(arr, aux, 0, arr.size - 1, onSwap)
    }

    private suspend fun mergeSort(
        arr: IntArray,
        aux: IntArray,
        low: Int,
        high: Int,
        onSwap: (IntArray) -> Unit
    ) {
        if (low >= high) return
        val mid = low + (high - low) / 2
        mergeSort(arr, aux, low, mid, onSwap)
        mergeSort(arr, aux, mid + 1, high, onSwap)
        merge(arr, aux, low, mid, high, onSwap)
    }

    private suspend fun merge(
        arr: IntArray,
        aux: IntArray,
        low: Int,
        mid: Int,
        high: Int,
        onSwap: (IntArray) -> Unit
    ) {
        // Copy to auxiliary array
        for (k in low..high) {
            aux[k] = arr[k]
        }

        var i = low
        var j = mid + 1

        for (k in low..high) {
            when {
                i > mid -> {
                    arr[k] = aux[j++]
                }
                j > high -> {
                    arr[k] = aux[i++]
                }
                aux[j] < aux[i] -> {
                    arr[k] = aux[j++]
                }
                else -> {
                    arr[k] = aux[i++]
                }
            }
            // Update the array state
            onSwap(arr.clone())
        }
    }
}

class SelectionSort : SortingAlgorithm {

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ) {
        val n = arr.size
        for (i in 0 until n - 1) {
            var minIdx = i
            for (j in i + 1 until n) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j
                }
            }
            if (minIdx != i) {
                // Swap arr[i] and arr[minIdx]
                val temp = arr[i]
                arr[i] = arr[minIdx]
                arr[minIdx] = temp
                onSwap(arr)
            }
        }
    }
}

class QuickSort : SortingAlgorithm {

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit
    ) {
        quickSort(arr, 0, arr.size - 1, onSwap)
    }

    private suspend fun quickSort(
        arr: IntArray,
        low: Int,
        high: Int,
        onSwap: (IntArray) -> Unit
    ) {
        if (low < high) {
            val pi = partition(arr, low, high, onSwap)
            quickSort(arr, low, pi - 1, onSwap)
            quickSort(arr, pi + 1, high, onSwap)
        }
    }

    private suspend fun partition(
        arr: IntArray,
        low: Int,
        high: Int,
        onSwap: (IntArray) -> Unit
    ): Int {
        val pivot = arr[high]
        var i = low - 1

        for (j in low until high) {
            if (arr[j] <= pivot) {
                i++
                // Swap arr[i] and arr[j]
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
                onSwap(arr)
            }
        }
        // Swap arr[i+1] and arr[high] (or pivot)
        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp
        onSwap(arr)
        return i + 1
    }
}