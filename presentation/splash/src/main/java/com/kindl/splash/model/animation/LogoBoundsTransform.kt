package com.kindl.splash.model.animation

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import com.kindl.core.designsystem.animation.KindlMotion

@OptIn(ExperimentalSharedTransitionApi::class)
internal val LogoBoundsTransform = BoundsTransform { _, _ ->
    KindlMotion.bouncySpring
}
