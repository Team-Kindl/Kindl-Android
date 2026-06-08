package com.kindl.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val KindlDarkColorScheme = darkColorScheme(
    primary              = KindlCoral500,
    onPrimary            = White,
    primaryContainer     = KindlSlate700,
    onPrimaryContainer   = KindlSlate50,
    secondary            = KindlSlate700,
    onSecondary          = White,
    secondaryContainer   = KindlSlate600,
    onSecondaryContainer = KindlSlate100,
    background           = KindlSlate900,
    onBackground         = KindlSlate50,
    surface              = KindlSlate800,
    onSurface            = KindlSlate50,
    surfaceVariant       = KindlSlate700,
    onSurfaceVariant     = KindlSlate300,
    outline              = KindlCoral500,
    error                = KindlCoral700,
    onError              = White,
)

private val KindlLightColorScheme = lightColorScheme(
    primary              = KindlSlate700,
    onPrimary            = White,
    primaryContainer     = KindlSlate100,
    onPrimaryContainer   = KindlSlate700,
    secondary            = KindlCoral500,
    onSecondary          = White,
    secondaryContainer   = KindlCoral100,
    onSecondaryContainer = KindlCoral700,
    background           = White,
    onBackground         = KindlSlate700,
    surface              = White,
    onSurface            = KindlSlate700,
    surfaceVariant       = KindlSlate50,
    onSurfaceVariant     = KindlSlate500,
    outline              = KindlSlate700,
    error                = KindlCoral700,
    onError              = White,
)

@Composable
fun ProvideKindlColorsAndTypography(
    colors: KindlColors,
    typography: KindlTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        localKindlColors provides colors,
        localKindlTypography provides typography,
        content = content
    )
}

@Composable
fun KindlTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    val kindlColors = if (darkTheme) darkKindlColors else lightKindlColors
    val colorScheme = if (darkTheme) KindlDarkColorScheme else KindlLightColorScheme

    ProvideKindlColorsAndTypography(
        colors = kindlColors,
        typography = defaultKindlTypography,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

object KindlTheme {
    val colors: KindlColors
        @Composable
        @ReadOnlyComposable
        get() = localKindlColors.current

    val typography: KindlTypography
        @Composable
        @ReadOnlyComposable
        get() = localKindlTypography.current
}
