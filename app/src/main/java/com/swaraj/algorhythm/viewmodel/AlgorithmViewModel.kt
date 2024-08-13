package com.swaraj.algorhythm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swaraj.algorhythm.Algorithm.SortingAlgorithm
import com.swaraj.algorhythm.data.AlgorithmEvents
import com.swaraj.algorhythm.data.AlgorithmEvents.SlowDown
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

class AlgorithmViewModel(
    private val sortingAlgorithm: SortingAlgorithm
) : ViewModel() {
    var arr = mutableStateOf(
        intArrayOf(
            34, 215, 45, 678, 320, 199, 540, 105, 688, 299, 432, 355,
            623, 207, 89, 580, 421, 310, 234, 576, 199,
            472, 589, 673, 298, 125, 401, 678, 253, 525, 482, 605, 370,
            290, 346, 512, 399, 634
        )
    )

    val isPlaying = mutableStateOf(false)
    val onSortingFinished = mutableStateOf(false)
    private var delay = 150L
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
                //delay(delay)
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
        if (delay >=30L) {
            delay -=10
        }
    }

    private fun slowDown() {

        delay+=20

    }
}
