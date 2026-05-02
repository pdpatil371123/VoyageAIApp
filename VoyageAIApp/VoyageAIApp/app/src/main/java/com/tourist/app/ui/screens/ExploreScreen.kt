package com.tourist.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourist.app.data.Destination
import com.tourist.app.data.DummyData
import com.tourist.app.ui.components.DestinationImageBox

@Composable
fun ExploreScreen(onPlaceClick: (Int) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val filtered = remember(searchQuery, selectedCategory) {
        DummyData.featuredDestinations.filter { dest ->
            val matchesSearch = searchQuery.isEmpty() ||
                dest.name.contains(searchQuery, ignoreCase = true) ||
                dest.location.contains(searchQuery, ignoreCase = true)
            val matchesCategory = selectedCategory == "All" || dest.category == selectedCategory
            matchesSearch && matchesCategory
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = 52.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Explore",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Discover amazing places",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
            )
            Spacer(Modifier.height(14.dp))

            // Search bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                tonalElevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 13.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        decorationBox = { inner ->
                            if (searchQuery.isEmpty()) {
                                Text(
                                    "Search places, cities...",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
                                )
                            }
                            inner()
                        }
                    )
                    Icon(
                        Icons.Filled.Tune,
                        contentDescription = "Filter",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Category filters
        LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(DummyData.categories) { category ->
                val isSelected = selectedCategory == category
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = category },
                    label = {
                        Text(
                            text = category,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        // Results count
        Text(
            text = "${filtered.size} places found",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
        )

        // Destinations list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(filtered) { destination ->
                ExploreDestinationCard(destination = destination, onClick = { onPlaceClick(destination.id) })
            }
            item { Spacer(Modifier.height(80.dp)) }
        }
    }
}

@Composable
fun ExploreDestinationCard(destination: Destination, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            // Image area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                DestinationImageBox(
                    color = destination.imageColor,
                    emoji = destination.emoji,
                    modifier = Modifier.fillMaxSize()
                )
                // Category badge
                Surface(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopStart),
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = destination.category,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
                // Rating badge
                Surface(
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFFFF8E1)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(Icons.Filled.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(13.dp))
                        Text(
                            text = destination.rating.toString(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF5D4037)
                        )
                    }
                }
            }

            // Info area
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = destination.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Icon(
                        Icons.Filled.LocationOn,
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = destination.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoChip("🕐 ${destination.duration}")
                    InfoChip("📅 ${destination.bestTime}")
                }
            }
        }
    }
}

@Composable
fun InfoChip(text: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}
