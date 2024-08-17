package com.swaraj.algorhythm.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomWindow(
    modifier: Modifier = Modifier,
    description: @Composable () -> Unit,
    code: @Composable () -> Unit
) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // adjust the height as needed
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Tab(selected = selectedTabIndex.value == 0, onClick = { selectedTabIndex.value = 0 }) {
                Text("Description")
            }
            Tab(selected = selectedTabIndex.value == 1, onClick = { selectedTabIndex.value = 1 }) {
                Text("Code")
            }
        }
        when (selectedTabIndex.value) {
            0 -> description()
            1 -> code()
        }
    }
}
