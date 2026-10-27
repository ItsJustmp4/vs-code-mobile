package com.example.vscode.mobile.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Typography

private val DarkColorScheme = darkColorScheme(
    primary = VSCodeAccent,
    secondary = VSCodeAccentSoft,
    background = VSCodeBackground,
    surface = VSCodePanel,
    onPrimary = VSCodeText,
    onBackground = VSCodeText,
    onSurface = VSCodeText
)

@Composable
fun VSCodeMobileTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography(),
        content = content
    )
}