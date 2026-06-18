package com.kindl.auth.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.auth.model.animation.LogoBoundsTransform
import com.kindl.core.designsystem.R
import com.kindl.core.designsystem.theme.KindlTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun SharedTransitionScope.AuthInfoHolder(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.img_icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp)
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "kindl_logo_image"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = LogoBoundsTransform
                )
        )

        Text(
            text = "집중이 습관이 되는 곳,\nKindl을 만나보세요!",
            style = KindlTheme.typography.bold.headLine1,
            color = KindlTheme.colors.white
        )

        Text(
            text = "포인트를 걸고 함께 집중하며,\nAI 코치와 꾸준한 습관을 만들어보세요.",
            style = KindlTheme.typography.regular.body3,
            color = KindlTheme.colors.white
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun AuthInfoHolderPreview() {
    KindlTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                this@SharedTransitionLayout.AuthInfoHolder(
                    animatedVisibilityScope = this@AnimatedVisibility
                )
            }
        }
    }
}
