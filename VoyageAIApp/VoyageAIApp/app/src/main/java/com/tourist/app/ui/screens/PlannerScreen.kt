package com.tourist.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tourist.app.data.DummyData

@Composable
fun PlannerScreen() {
    var destination by remember { mutableStateOf("") }
    var days by remember { mutableStateOf("") }
    var travelers by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }
    var selectedInterests by remember { mutableStateOf(setOf<String>()) }
    var showResult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = 52.dp, start = 20.dp, end = 20.dp, bottom = 24.dp)
        ) {
            Column {
                Text(
                    text = "Trip Planner",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    text = "Let us craft your perfect itinerary ✨",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {

            if (showResult) {
                // Generated Plan Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🎉", style = MaterialTheme.typography.headlineSmall)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "Your Trip Plan is Ready!",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                        PlanRow("📍 Destination", destination.ifBlank { "Not specified" })
                        PlanRow("📅 Duration", if (days.isNotBlank()) "$days days" else "Not specified")
                        PlanRow("👥 Travelers", travelers.ifBlank { "Not specified" })
                        PlanRow("💰 Budget", if (budget.isNotBlank()) "₹$budget" else "Not specified")
                        if (selectedInterests.isNotEmpty()) {
                            PlanRow("🎯 Interests", selectedInterests.joinToString(", "))
                        }
                        Spacer(Modifier.height(14.dp))
                        Divider()
                        Spacer(Modifier.height(14.dp))
                        Text(
                            "Day-by-Day Summary",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(8.dp))
                        val dayCount = days.toIntOrNull() ?: 3
                        (1..minOf(dayCount, 3)).forEach { day ->
                            Text(
                                text = "Day $day: ${
                                    listOf(
                                        "Arrival & local sightseeing 🗺️",
                                        "Main attractions & cultural sites 🏛️",
                                        "Nature, food & departure prep 🌿"
                                    ).getOrElse(day - 1) { "Exploration & leisure" }
                                }",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                        Spacer(Modifier.height(14.dp))
                        Button(
                            onClick = { showResult = false },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Plan Another Trip")
                        }
                    }
                }
                Spacer(Modifier.height(80.dp))
                return@Column
            }

            Spacer(Modifier.height(4.dp))

            // Destination Input
            PlannerInputField(
                value = destination,
                onValueChange = { destination = it },
                label = "Destination",
                placeholder = "e.g. Kerala, Rajasthan...",
                icon = Icons.Filled.LocationOn
            )

            Spacer(Modifier.height(14.dp))

            // Days & Travelers in row
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                PlannerInputField(
                    value = days,
                    onValueChange = { days = it },
                    label = "No. of Days",
                    placeholder = "e.g. 5",
                    icon = Icons.Filled.CalendarMonth,
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(1f)
                )
                PlannerInputField(
                    value = travelers,
                    onValueChange = { travelers = it },
                    label = "Travelers",
                    placeholder = "e.g. 2",
                    icon = Icons.Filled.Group,
                    keyboardType = KeyboardType.Number,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(14.dp))

            // Budget
            PlannerInputField(
                value = budget,
                onValueChange = { budget = it },
                label = "Budget (₹)",
                placeholder = "e.g. 50000",
                icon = Icons.Filled.CurrencyRupee,
                keyboardType = KeyboardType.Number
            )

            Spacer(Modifier.height(20.dp))

            // Interests
            Text(
                text = "Interests",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Select what you love",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
            Spacer(Modifier.height(10.dp))

            // Interest chips grid
            val chunked = DummyData.interestOptions.chunked(4)
            chunked.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEach { interest ->
                        val isSelected = interest in selectedInterests
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    selectedInterests = if (isSelected) {
                                        selectedInterests - interest
                                    } else {
                                        selectedInterests + interest
                                    }
                                }
                                .border(
                                    width = 1.5.dp,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                        ) {
                            Text(
                                text = interest,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp)
                            )
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
            }

            Spacer(Modifier.height(24.dp))

            // Generate button
            Button(
                onClick = { showResult = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(Icons.Filled.AutoAwesome, null)
                Spacer(Modifier.width(8.dp))
                Text(
                    "Generate My Itinerary",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.height(100.dp))
    }
}

@Composable
fun PlannerInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder, color = Color.Gray) },
        leadingIcon = {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
        },
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        )
    )
}

@Composable
fun PlanRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Text(value, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
    }
}
