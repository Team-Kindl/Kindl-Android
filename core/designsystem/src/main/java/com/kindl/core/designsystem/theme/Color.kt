package com.kindl.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Main Color - Slate
val KindlSlate50  = Color(0xFFF8F9FA)
val KindlSlate100 = Color(0xFFE9ECEF)
val KindlSlate200 = Color(0xFFD3D7DC)
val KindlSlate300 = Color(0xFFB0B7C0)
val KindlSlate400 = Color(0xFF7D8794)
val KindlSlate500 = Color(0xFF4A5568)
val KindlSlate600 = Color(0xFF2D3748)
val KindlSlate700 = Color(0xFF1E2530)
val KindlSlate800 = Color(0xFF151A23)
val KindlSlate900 = Color(0xFF0D1117)

// Point Color - Coral
val KindlCoral50  = Color(0xFFFFF5F5)
val KindlCoral100 = Color(0xFFFFE3E3)
val KindlCoral200 = Color(0xFFFFC9C9)
val KindlCoral300 = Color(0xFFFFA8A8)
val KindlCoral400 = Color(0xFFFF8787)
val KindlCoral500 = Color(0xFFFF6B6B)
val KindlCoral600 = Color(0xFFFA5252)
val KindlCoral700 = Color(0xFFF03E3E)
val KindlCoral800 = Color(0xFFE03131)
val KindlCoral900 = Color(0xFFC92A2A)

// Supporting Colors
val KindlBlue500  = Color(0xFF339AF0)
val KindlTeal500  = Color(0xFF20C997)
val KindlAmber500 = Color(0xFFFCC419)

val White = Color(0xFFFFFFFF)

@Immutable
data class KindlColors(
    // Surface
    val background: Color,
    val foreground: Color,
    val card: Color,
    val cardForeground: Color,
    val popover: Color,
    val popoverForeground: Color,

    // Interactive
    val primary: Color,
    val primaryForeground: Color,
    val secondary: Color,
    val secondaryForeground: Color,

    // State
    val muted: Color,
    val mutedForeground: Color,
    val accent: Color,
    val accentForeground: Color,
    val destructive: Color,
    val destructiveForeground: Color,
    val success: Color,
    val successForeground: Color,

    // Form
    val border: Color,
    val input: Color,
    val inputBackground: Color,
    val switchBackground: Color,
    val ring: Color,

    // Chart
    val chart1: Color,
    val chart2: Color,
    val chart3: Color,
    val chart4: Color,
    val chart5: Color,

    // Sidebar
    val sidebar: Color,
    val sidebarForeground: Color,
    val sidebarPrimary: Color,
    val sidebarPrimaryForeground: Color,
    val sidebarAccent: Color,
    val sidebarAccentForeground: Color,
    val sidebarBorder: Color,
    val sidebarRing: Color,

    // Raw palette (필요 시 직접 참조)
    val slate50: Color  = KindlSlate50,
    val slate100: Color = KindlSlate100,
    val slate200: Color = KindlSlate200,
    val slate300: Color = KindlSlate300,
    val slate400: Color = KindlSlate400,
    val slate500: Color = KindlSlate500,
    val slate600: Color = KindlSlate600,
    val slate700: Color = KindlSlate700,
    val slate800: Color = KindlSlate800,
    val slate900: Color = KindlSlate900,

    val coral50: Color  = KindlCoral50,
    val coral100: Color = KindlCoral100,
    val coral200: Color = KindlCoral200,
    val coral300: Color = KindlCoral300,
    val coral400: Color = KindlCoral400,
    val coral500: Color = KindlCoral500,
    val coral600: Color = KindlCoral600,
    val coral700: Color = KindlCoral700,
    val coral800: Color = KindlCoral800,
    val coral900: Color = KindlCoral900,

    val blue500: Color  = KindlBlue500,
    val teal500: Color  = KindlTeal500,
    val amber500: Color = KindlAmber500,

    val isDark: Boolean,
)

val lightKindlColors = KindlColors(
    background           = White,
    foreground           = KindlSlate700,
    card                 = White,
    cardForeground       = KindlSlate700,
    popover              = White,
    popoverForeground    = KindlSlate700,

    primary              = KindlSlate700,
    primaryForeground    = White,
    secondary            = KindlCoral500,
    secondaryForeground  = White,

    muted                = KindlSlate50,
    mutedForeground      = KindlSlate400,
    accent               = KindlCoral500,
    accentForeground     = White,
    destructive          = KindlCoral700,
    destructiveForeground= White,
    success              = KindlTeal500,
    successForeground    = White,

    border               = KindlSlate700.copy(alpha = 0.10f),
    input                = Color.Transparent,
    inputBackground      = KindlSlate50,
    switchBackground     = KindlSlate200,
    ring                 = KindlCoral500,

    chart1               = KindlCoral500,
    chart2               = KindlSlate700,
    chart3               = KindlBlue500,
    chart4               = KindlTeal500,
    chart5               = KindlAmber500,

    sidebar              = KindlSlate50,
    sidebarForeground    = KindlSlate700,
    sidebarPrimary       = KindlSlate700,
    sidebarPrimaryForeground = White,
    sidebarAccent        = KindlSlate100,
    sidebarAccentForeground  = KindlSlate700,
    sidebarBorder        = KindlSlate700.copy(alpha = 0.10f),
    sidebarRing          = KindlCoral500,

    isDark = false,
)

val darkKindlColors = KindlColors(
    background           = KindlSlate900,
    foreground           = KindlSlate50,
    card                 = KindlSlate800,
    cardForeground       = KindlSlate50,
    popover              = KindlSlate800,
    popoverForeground    = KindlSlate50,

    primary              = KindlCoral500,
    primaryForeground    = White,
    secondary            = KindlSlate700,
    secondaryForeground  = White,

    muted                = KindlSlate600,
    mutedForeground      = KindlSlate300,
    accent               = KindlCoral500,
    accentForeground     = White,
    destructive          = KindlCoral700,
    destructiveForeground= White,
    success              = KindlTeal500,
    successForeground    = KindlSlate900,

    border               = KindlCoral500.copy(alpha = 0.20f),
    input                = KindlCoral500.copy(alpha = 0.10f),
    inputBackground      = KindlSlate800,
    switchBackground     = KindlSlate600,
    ring                 = KindlCoral500,

    chart1               = KindlCoral500,
    chart2               = KindlBlue500,
    chart3               = KindlTeal500,
    chart4               = KindlAmber500,
    chart5               = KindlCoral400,

    sidebar              = KindlSlate800,
    sidebarForeground    = KindlSlate50,
    sidebarPrimary       = KindlCoral500,
    sidebarPrimaryForeground = White,
    sidebarAccent        = KindlSlate600,
    sidebarAccentForeground  = KindlSlate50,
    sidebarBorder        = KindlCoral500.copy(alpha = 0.20f),
    sidebarRing          = KindlCoral500,

    isDark = true,
)

val localKindlColors = staticCompositionLocalOf { darkKindlColors }
