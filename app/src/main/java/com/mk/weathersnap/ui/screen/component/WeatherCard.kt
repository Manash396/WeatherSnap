package com.mk.weathersnap.ui.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherCard(
    cityName: String,
    onCreateReport: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(cityName, style = MaterialTheme.typography.titleMedium)
                    Text("Partly cloudy")
                }

                Text("17°C", style = MaterialTheme.typography.titleLarge)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                InfoBox("Humidity", "43%")
                InfoBox("Wind", "3.83 m/s")
                InfoBox("Pressure", "791")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Camera and Room DB enabled")

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onCreateReport,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Report")
            }
        }
    }
}

@Composable
fun InfoBox(title: String, value: String) {
    Column {
        Text(title, style = MaterialTheme.typography.labelSmall)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}