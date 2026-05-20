package com.kindl.presentation.home.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.core.designsystem.component.UrlImage
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.presentation.home.model.ForbiddenAppUiModel

@Composable
internal fun ForbiddenAppItem(
    item: ForbiddenAppUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        UrlImage(
            url = item.icon,
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(text = item.appName)
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
