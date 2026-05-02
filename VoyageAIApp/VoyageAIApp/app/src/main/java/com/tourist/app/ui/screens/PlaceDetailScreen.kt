package com.tourist.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tourist.app.data.DummyData
import com.tourist.app.ui.components.DestinationImageBox

@Composable
fun PlaceDetailScreen(placeId: Int, onBack: () -> Unit, onPlanTrip: () -> Unit) {
    val destination = DummyData.featuredDestinations.find { it.id == placeId }
        ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Hero image area
        Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            DestinationImageBox(
                color = destination.imageColor,
                emoji = destination.emoji,
                modifier = Modifier.fillMaxSize()
            )
            // Dark gradient overlay at bottom
            Box(
                modifier = Modifier.fillMaxSize().background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f)),
                        startY = 150f
                    )
                )
            )
            // Back button
            Box(
                modifier = Modifier
                    .padding(top = 50.dp, start = 16.dp)
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.9f))
                    .align(Alignment.TopStart),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, "Back", tint = MaterialTheme.colorScheme.primary)
                }
            }
            // Favorite button
            Box(
                modifier = Modifier
                    .padding(top = 50.dp, end = 16.dp)
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.9f))
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.FavoriteBorder, "Favourite", tint = Color.Red)
            }
            // Bottom info overlay
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.9f)
                ) {
                    Text(
                        text = destination.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                    )
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    text = destination.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(Icons.Filled.LocationOn, null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(14.dp))
                    Text(
                        text = destination.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Content
        Column(modifier = Modifier.padding(20.dp)) {
            // Quick info cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickInfoCard(
                    icon = Icons.Filled.Star,
                    label = "Rating",
                    value = "${destination.rating}/5",
                    iconTint = Color(0xFFFFC107),
                    modifier = Modifier.weight(1f)
                )
                QuickInfoCard(
                    icon = Icons.Filled.WbSunny,
                    label = "Best Time",
                    value = destination.bestTime,
                    iconTint = Color(0xFFFF9800),
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickInfoCard(
                    icon = Icons.Filled.Schedule,
                    label = "Duration",
                    value = destination.duration,
                    iconTint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                QuickInfoCard(
                    icon = Icons.Filled.Category,
                    label = "Category",
                    value = destination.category,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(24.dp))

            // Description
            Text(
                text = "About this Place",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = destination.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.72f),
                lineHeight = 22.sp
            )

            Spacer(Modifier.height(24.dp))

            // Highlights
            Text(
                text = "Highlights",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(10.dp))
            listOf(
                "📸 Stunning photo opportunities",
                "🚌 Easy accessibility by public transport",
                "🍽️ Local cuisine experiences nearby",
                "🛕 Cultural & heritage significance"
            ).forEach { highlight ->
                Row(
                    modifier = Modifier.padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = highlight, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(Modifier.height(32.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(vertical = 14.dp)
                ) {
                    Icon(Icons.Filled.Directions, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Directions", fontWeight = FontWeight.SemiBold)
                }
                Button(
                    onClick = onPlanTrip,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    contentPadding = PaddingValues(vertical = 14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(Icons.Filled.EventNote, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Plan My Trip", fontWeight = FontWeight.SemiBold)
                }
            }
        }

        Spacer(Modifier.height(30.dp))
    }
}

@Composable
fun QuickInfoCard(
    icon: ImageVector,
    label: String,
    value: String,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconTint.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, null, tint = iconTint, modifier = Modifier.size(18.dp))
            }
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
