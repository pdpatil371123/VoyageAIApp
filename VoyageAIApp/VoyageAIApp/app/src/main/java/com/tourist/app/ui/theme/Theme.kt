package com.tourist.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF1A6B5A)
val OnPrimary = Color(0xFFFFFFFF)
val PrimaryContainer = Color(0xFFB7F0DD)
val Secondary = Color(0xFFFF6B35)
val OnSecondary = Color(0xFFFFFFFF)
val Background = Color(0xFFF8FAF9)
val Surface = Color(0xFFFFFFFF)
val OnBackground = Color(0xFF1A1A2E)
val OnSurface = Color(0xFF1A1A2E)
val SurfaceVariant = Color(0xFFECF4F1)
val Tertiary = Color(0xFF8B5CF6)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    secondary = Secondary,
    onSecondary = OnSecondary,
    background = Background,
    surface = Surface,
    onBackground = OnBackground,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    tertiary = Tertiary
)

@Composable
fun IntelligentTouristTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
