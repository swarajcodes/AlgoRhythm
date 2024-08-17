package com.swaraj.algorhythm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swaraj.algorhythm.Algorithm.SortingAlgorithm
import com.swaraj.algorhythm.data.AlgorithmEvents
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

class AlgorithmViewModel(
    private val sortingAlgorithm: SortingAlgorithm
) : ViewModel() {
    var arr = mutableStateOf(
        intArrayOf(
            34, 115, 45, 378, 220, 99, 340, 105, 388, 199, 132, 255,
            323, 107, 89, 180, 121, 110, 234, 376, 89,
            72, 289, 273, 198, 125, 201, 378, 253, 325, 282, 305, 370,
            290, 346, 212, 189, 334
        )
    )

    val isPlaying = mutableStateOf(false)
    val onSortingFinished = mutableStateOf(false)
    private var delayy = 150L
    private var pause = false
    private var next = 1
    private var previous = 0

    private var sortedArrayLevels = mutableListOf<List<Int>>()

    init {
        viewModelScope.launch {
            sortingAlgorithm.sort(
                arr.value.clone()
            ) { modifiedArray ->
                sortedArrayLevels.add(modifiedArray.toMutableList())
            }
        }
    }

    fun onEvent(event: AlgorithmEvents) {
        when (event) {
            is AlgorithmEvents.PlayPause -> {
                playPauseAlgorithm()
            }
            is AlgorithmEvents.SlowDown -> {
                slowDown()
            }
            is AlgorithmEvents.SpeedUp -> {
                speedUp()
            }
            is AlgorithmEvents.Previous -> {
                previousState()
            }
            is AlgorithmEvents.Next -> {
                nextState()
            }
        }
    }

    private fun playPauseAlgorithm() {
        if (isPlaying.value) {
            pause()
        } else {
            play()
        }
        isPlaying.value = !isPlaying.value
    }

    private var sortingState = 0
    private fun play() = viewModelScope.launch {
        pause = false
        for (i in sortingState until sortedArrayLevels.size) {
            if (!pause) {
                kotlinx.coroutines.delay(delayy)
                arr.value = sortedArrayLevels[i].toIntArray()
            } else {
                sortingState = i
                next = i +1
                previous = i
                return@launch
            }
        }
        onSortingFinished.value = true
    }

    private fun pause() {
        pause = true
    }

    private fun nextState() {
        if (next < sortedArrayLevels.size) {
            arr.value = sortedArrayLevels[next++].toIntArray()
            next++
            previous++
        }
    }

    private fun previousState() {
        if (previous >= 0) {
            arr.value = sortedArrayLevels[previous--].toIntArray()
            next--
            previous--
        }
    }

    private fun speedUp() {
        if (delayy >=30L) {
            delayy -=10
        }
    }

    private fun slowDown() {

        delayy+=20

    }
}
