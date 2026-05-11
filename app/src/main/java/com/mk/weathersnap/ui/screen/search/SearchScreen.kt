package com.mk.weathersnap.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mk.weathersnap.ui.screen.component.WeatherCard
import com.mk.weathersnap.util.UiResult

@Composable
fun SearchScreen(
    onSearchClick: () -> Unit,
    onReportsClick: () -> Unit,
    viewModel: SearchUiViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Header
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text("WeatherSnap", style = MaterialTheme.typography.titleLarge)
                Text("Live weather reports with camera evidence")
            }

            Button(onClick = onReportsClick) {
                Text("Reports")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Box
        var query by remember { mutableStateOf("") }

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.onQueryChange(it)
            },
            label = { Text("City") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Enter more than 2 letters to start city suggestions.")

        Spacer(modifier = Modifier.height(16.dp))

        // Result Section
        when (val result = state.value) {

            is UiResult.Loading -> {
                CircularProgressIndicator()
            }

            is UiResult.Success -> {
                val city = result.data.firstOrNull()

                if (city != null) {
                    WeatherCard(
                        cityName = "${city.name}, ${city.country}",
                        onCreateReport = onSearchClick
                    )
                }
            }

            is UiResult.Error -> {
                Text(result.message)
            }

            else -> {

            }
        }
    }
}