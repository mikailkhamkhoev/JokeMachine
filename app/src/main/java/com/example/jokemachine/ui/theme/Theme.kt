package com.example.jokemachine.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val PikachuColorScheme = lightColorScheme(
    primary = PikachuBlack,
    onPrimary = PikachuWhite,
    secondary = PikachuBrown,
    onSecondary = PikachuBlack,
    background = PikachuYellow,
    onBackground = PikachuBlack,
    surface = PikachuWhite,
    onSurface = PikachuBlack,
    error = PikachuCheekRed,
    onError = PikachuWhite
)

@Composable
fun PikachuTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PikachuColorScheme,
        content = content
    )
}
