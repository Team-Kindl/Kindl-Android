package com.kindl.core.designsystem.animation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.ui.geometry.Rect

object KindlMotion {
    val bouncySpring: FiniteAnimationSpec<Rect> = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessVeryLow
    )
    /*tween (
        delayMillis = 1000,
        easing = FastOutSlowInEasing
    )*/
}
