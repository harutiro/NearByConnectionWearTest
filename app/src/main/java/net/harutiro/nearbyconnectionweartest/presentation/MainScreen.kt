package net.harutiro.nearbyconnectionweartest.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyColumnDefaults
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.Text
import net.harutiro.nearbyconnectionsapitest.MainViewModel
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.ScrollIndicator

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val listState = rememberScalingLazyListState()
    ScreenScaffold(
        scrollState = listState,
        scrollIndicator = {
            ScrollIndicator(state = listState)
        }
    ) {
        val receivedList = viewModel.receivedDataList.takeLast(20)
        ScalingLazyColumn(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp),
            state = listState,
            flingBehavior = ScalingLazyColumnDefaults.snapFlingBehavior(state = listState)
        ) {
            item {
                Chip(
                    onClick = { viewModel.startDiscovery() },
                    label = { Text("データ受信待機") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ChipDefaults.primaryChipColors()
                )
            }
            item {
                Chip(
                    onClick = { viewModel.startAdvertise() },
                    label = { Text("データ送信待機") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ChipDefaults.primaryChipColors()
                )
            }
            item {
                Chip(
                    onClick = { viewModel.sendData("Wearからだよ") },
                    label = { Text("データ送信") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ChipDefaults.primaryChipColors()
                )
            }
            item {
                Text(viewModel.connectState, modifier = Modifier.padding(vertical = 8.dp))
            }
            // 受信データリストを表示
            items(receivedList) { (endpointId, data) ->
                Text("[$endpointId] $data", modifier = Modifier.padding(vertical = 2.dp))
            }
        }
    }
} 