package com.carolina.characterscounterapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.carolina.characterscounterapp.R


val electrolizeFont =
    FontFamily(
        Font(R.font.electrolize),
        Font(R.font.electrolize, FontWeight.W500),
    )
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = electrolizeFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = electrolizeFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    /* Other default text styles to override

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)



val defaultTextStyle =
    TextStyle(
        fontFamily = electrolizeFont,
        platformStyle =
        PlatformTextStyle(
            includeFontPadding = false,
        ),
        lineHeightStyle =
        LineHeightStyle(
            alignment = LineHeightStyle.Alignment.Center,
            trim = LineHeightStyle.Trim.None,
        ),
    )

val titleMediumStyle =
    defaultTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
    )

val titleLargeStyle =
    defaultTextStyle.copy(
        fontSize = 24.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.W500,
    )