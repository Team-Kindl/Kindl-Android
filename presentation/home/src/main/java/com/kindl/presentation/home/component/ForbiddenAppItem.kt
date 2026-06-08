package com.kindl.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.presentation.home.model.ForbiddenAppUiModel

@Composable
internal fun ForbiddenAppItem(
    item: ForbiddenAppUiModel,
    modifier: Modifier = Modifier
) {
    val iconBitmap = remember(item.icon) {
        item.icon?.toBitmap()?.asImageBitmap()
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val iconModifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(16.dp))


        iconBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = iconModifier
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = item.appName,
            style = KindlTheme.typography.semiBold.title3,
            color = KindlTheme.colors.primary
        )
    }
}

@Preview
@Composable
private fun ForbiddenAppItemPreview() {
    KindlTheme {
        ForbiddenAppItem(
            item = ForbiddenAppUiModel(
                appPackageName = "",
                appName = "test"
            )
        )
    }
}
