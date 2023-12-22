package dev.jay.wouldurather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.jay.wouldurather.R

val doodleFontFamily = FontFamily(
    Font(
        resId = R.font.rubik_doodle_shadow_regular,
        weight = FontWeight.Bold
    )
)

val poppinsHeaderQuestionFontFamily = FontFamily(
    Font(
        resId = R.font.poppins_bold,
        weight = FontWeight.Bold
    )
)

val poppinsPercentageFontFamily = FontFamily(
    Font(
        resId = R.font.poppins_extra_bold,
        weight = FontWeight.ExtraBold
    )
)

val poppinsQuestionFontFamily = FontFamily(
    Font(
        resId = R.font.poppins_regular,
        weight = FontWeight.Normal
    )
)

val poppinsOrFontFamily = FontFamily(
    Font(
        resId = R.font.poppins_semi_bold,
        weight = FontWeight.SemiBold
    )
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = doodleFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    )
)