package hr.ferit.jurajbirovic.newsheet.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// Colors: Black, Yellow, and White
private val CustomDarkColorScheme = darkColorScheme(
    primary = Color(0xFF2196F3), // Bright yellow for primary elements
    secondary = Color(0xFFE91E63), // Slightly darker yellow for accents
    tertiary = Color(0xFF4CAF50), // Golden yellow for highlights

    background = Color(0xFF191919), // Pure black for the background
    surface = Color(0xFF191919), // Slightly lighter black for surfaces
    onPrimary = Color.Black, // Black text/icons on yellow
    onSecondary = Color.Black, // Black text/icons on secondary yellow
    onBackground = Color.White, // White text on black background
    onSurface = Color.White, // White text on surfaces

    error = Color(0xFFD32F2F), // Deep red for errors
    onError = Color.White // White text/icons on error
)

// Typography aligned with the new theme
private val CustomTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        color = Color.White // White for headlines
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = Color(0xFFFFFFFF) // Yellow for body text
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = Color(0xFFFFFFFF) // Slightly darker yellow for small labels
    )
)

// Shapes for consistent design
private val CustomShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(20.dp)
)

@Composable
fun NewSheetTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color(0xFF191919), // Transparent system bars
            darkIcons = useDarkIcons
        )
    }

    MaterialTheme(
        colorScheme = CustomDarkColorScheme,
        typography = CustomTypography,
        shapes = CustomShapes,
        content = content
    )
}
