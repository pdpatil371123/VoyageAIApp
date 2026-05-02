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

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        // Profile header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                        )
                    )
                )
                .padding(top = 52.dp, bottom = 32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("👤", fontSize = 40.sp)
                }
                Spacer(Modifier.height(14.dp))
                Text(
                    "Arjun Sharma",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    "arjun.sharma@email.com",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.75f)
                )
                Spacer(Modifier.height(16.dp))

                // Stats row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    ProfileStat("12", "Trips")
                    ProfileStat("48", "Places")
                    ProfileStat("4.9", "Rating")
                }
            }
        }

        Column(modifier = Modifier.padding(20.dp)) {
            // Travel Badges
            Text(
                "Travel Badges",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOf(
                    Triple("🏔️", "Explorer", true),
                    Triple("🌊", "Beach Lover", true),
                    Triple("🏛️", "Historian", false),
                    Triple("🌿", "Nature Fan", true)
                ).forEach { (emoji, label, earned) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(
                                    if (earned) MaterialTheme.colorScheme.primaryContainer
                                    else MaterialTheme.colorScheme.surfaceVariant
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                emoji,
                                fontSize = 22.sp,
                                color = if (earned) Color.Unspecified else Color.Gray
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(
                            label,
                            style = MaterialTheme.typography.labelSmall,
                            color = if (earned) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Recent trips section
            Text(
                "Recent Trips",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(12.dp))

            listOf(
                Triple("🏰", "Jaisalmer, Rajasthan", "March 2025"),
                Triple("🌿", "Munnar, Kerala", "January 2025"),
                Triple("🕯️", "Varanasi, UP", "November 2024")
            ).forEach { (emoji, place, date) ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(emoji, fontSize = 28.sp)
                        Column(modifier = Modifier.weight(1f)) {
                            Text(place, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                            Text(date, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                        }
                        Icon(Icons.Filled.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Settings
            Text(
                "Settings",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(12.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column {
                    SettingsRow(Icons.Filled.Notifications, "Notifications", MaterialTheme.colorScheme.primary)
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    SettingsRow(Icons.Filled.Language, "Language", Color(0xFF2196F3))
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    SettingsRow(Icons.Filled.DarkMode, "Dark Mode", Color(0xFF9C27B0))
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    SettingsRow(Icons.Filled.Help, "Help & Support", Color(0xFF4CAF50))
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                    SettingsRow(Icons.Filled.Logout, "Sign Out", Color(0xFFF44336))
                }
            }
        }

        Spacer(Modifier.height(100.dp))
    }
}

@Composable
fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun SettingsRow(icon: ImageVector, title: String, iconTint: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconTint.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = iconTint, modifier = Modifier.size(18.dp))
        }
        Text(
            title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.Filled.ChevronRight,
            null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            modifier = Modifier.size(18.dp)
        )
    }
}
