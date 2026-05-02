package com.tourist.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourist.app.data.DummyData
import com.tourist.app.ui.components.FeaturedCard
import com.tourist.app.ui.components.PopularDestinationRow

@Composable
fun HomeScreen(onPlaceClick: (Int) -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        // Header with gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.85f)
                        )
                    )
                )
                .padding(top = 52.dp, bottom = 28.dp, start = 20.dp, end = 20.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Good Morning! 🌅",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        Text(
                            text = "Where to next?",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                // Search bar
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Search destinations...",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Featured Section
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Featured",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                TextButton(onClick = {}) {
                    Text(
                        text = "See all",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Horizontal featured cards
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            DummyData.featuredDestinations.forEach { destination ->
                FeaturedCard(
                    destination = destination,
                    onClick = { onPlaceClick(destination.id) }
                )
            }
        }

        Spacer(Modifier.height(28.dp))

        // Quick Stats
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listOf(
                Triple("2.4K+", "Destinations", "🌍"),
                Triple("98%", "Happy Trips", "😊"),
                Triple("50+", "Categories", "🗂️")
            ).forEach { (value, label, emoji) ->
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(emoji, fontSize = 20.sp)
                        Text(
                            text = value,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(28.dp))

        // Popular Destinations
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Popular Destinations",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold
                )
                TextButton(onClick = {}) {
                    Text(
                        text = "View all",
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                DummyData.popularDestinations.forEach { destination ->
                    PopularDestinationRow(
                        destination = destination,
                        onClick = { onPlaceClick(destination.id) }
                    )
                }
            }
        }

        Spacer(Modifier.height(100.dp))
    }
}
