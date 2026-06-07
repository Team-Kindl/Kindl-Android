package com.kindl.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.kindl.core.designsystem.R

object KindlFont {
    val semiBold = FontFamily(Font(R.font.pretendard_semibold))  // TODO: 실제 폰트 리소스명으로 교체
    val bold     = FontFamily(Font(R.font.pretendard_bold))
    val regular  = FontFamily(Font(R.font.pretendard_regular))
    val medium   = FontFamily(Font(R.font.pretendard_medium))
}

private object TypographyDefaults {
    val RegularLetterSpacing   = (-0.005).em
    val RegularLineHeight      = 1.5.em

    val SemiBoldLetterSpacing  = (-0.01).em
    val SemiBoldLineHeight     = 1.5.em

    val BoldLetterSpacing      = (-0.01).em
    val BoldLineHeight         = 1.5.em

    val PlatformStyle = PlatformTextStyle(includeFontPadding = false)
}

sealed interface TypographyTokens {

    /**
     * Regular weight body 텍스트
     * CSS base 16px 기준 스케일 유지
     */
    @Immutable
    data class Regular(
        val body1: TextStyle,  // 18sp
        val body2: TextStyle,  // 16sp
        val body3: TextStyle,  // 14sp
        val body4: TextStyle,  // 12sp
        val body5: TextStyle,  // 11sp
        val body6: TextStyle,  // 10sp
    )

    /**
     * SemiBold weight title 텍스트
     * CSS h1~h4 에서 font-weight: medium(500) 대응
     */
    @Immutable
    data class SemiBold(
        val title1: TextStyle,  // 22sp
        val title2: TextStyle,  // 20sp
        val title3: TextStyle,  // 16sp
        val title4: TextStyle,  // 14sp
    )

    /**
     * Bold weight headline 텍스트
     */
    @Immutable
    data class Bold(
        val headLine1: TextStyle,  // 22sp
        val headLine2: TextStyle,  // 20sp
        val headLine3: TextStyle,  // 16sp
        val headLine4: TextStyle,  // 14sp
    )
}

@Immutable
data class KindlTypography(
    val regular: TypographyTokens.Regular,
    val semiBold: TypographyTokens.SemiBold,
    val bold: TypographyTokens.Bold,
)

val defaultKindlTypography = KindlTypography(
    regular = TypographyTokens.Regular(
        body1 = TextStyle(
            fontFamily    = KindlFont.regular,
            fontSize      = 18.sp,
            letterSpacing = TypographyDefaults.RegularLetterSpacing,
            lineHeight    = TypographyDefaults.RegularLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        body2 = TextStyle(
            fontFamily    = KindlFont.regular,
            fontSize      = 16.sp,
            letterSpacing = TypographyDefaults.RegularLetterSpacing,
            lineHeight    = TypographyDefaults.RegularLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        body3 = TextStyle(
            fontFamily    = KindlFont.regular,
            fontSize      = 14.sp,
            letterSpacing = TypographyDefaults.RegularLetterSpacing,
            lineHeight    = TypographyDefaults.RegularLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        body4 = TextStyle(
            fontFamily    = KindlFont.regular,
            fontSize      = 12.sp,
            letterSpacing = TypographyDefaults.RegularLetterSpacing,
            lineHeight    = TypographyDefaults.RegularLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        body5 = TextStyle(
            fontFamily    = KindlFont.regular,
            fontSize      = 11.sp,
            letterSpacing = TypographyDefaults.RegularLetterSpacing,
            lineHeight    = TypographyDefaults.RegularLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        body6 = TextStyle(
            fontFamily    = KindlFont.regular,
            fontSize      = 10.sp,
            letterSpacing = TypographyDefaults.RegularLetterSpacing,
            lineHeight    = TypographyDefaults.RegularLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
    ),
    semiBold = TypographyTokens.SemiBold(
        title1 = TextStyle(
            fontFamily    = KindlFont.semiBold,
            fontSize      = 22.sp,
            letterSpacing = TypographyDefaults.SemiBoldLetterSpacing,
            lineHeight    = TypographyDefaults.SemiBoldLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        title2 = TextStyle(
            fontFamily    = KindlFont.semiBold,
            fontSize      = 20.sp,
            letterSpacing = TypographyDefaults.SemiBoldLetterSpacing,
            lineHeight    = TypographyDefaults.SemiBoldLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        title3 = TextStyle(
            fontFamily    = KindlFont.semiBold,
            fontSize      = 16.sp,
            letterSpacing = TypographyDefaults.SemiBoldLetterSpacing,
            lineHeight    = TypographyDefaults.SemiBoldLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        title4 = TextStyle(
            fontFamily    = KindlFont.semiBold,
            fontSize      = 14.sp,
            letterSpacing = TypographyDefaults.SemiBoldLetterSpacing,
            lineHeight    = TypographyDefaults.SemiBoldLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
    ),
    bold = TypographyTokens.Bold(
        headLine1 = TextStyle(
            fontFamily    = KindlFont.bold,
            fontSize      = 22.sp,
            letterSpacing = TypographyDefaults.BoldLetterSpacing,
            lineHeight    = TypographyDefaults.BoldLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        headLine2 = TextStyle(
            fontFamily    = KindlFont.bold,
            fontSize      = 20.sp,
            letterSpacing = TypographyDefaults.BoldLetterSpacing,
            lineHeight    = TypographyDefaults.BoldLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        headLine3 = TextStyle(
            fontFamily    = KindlFont.bold,
            fontSize      = 16.sp,
            letterSpacing = TypographyDefaults.BoldLetterSpacing,
            lineHeight    = TypographyDefaults.BoldLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
        headLine4 = TextStyle(
            fontFamily    = KindlFont.bold,
            fontSize      = 14.sp,
            letterSpacing = TypographyDefaults.BoldLetterSpacing,
            lineHeight    = TypographyDefaults.BoldLineHeight,
            platformStyle = TypographyDefaults.PlatformStyle,
        ),
    ),
)

val localKindlTypography = staticCompositionLocalOf { defaultKindlTypography }
