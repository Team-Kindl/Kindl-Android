package com.kindl.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kindl.core.designsystem.theme.KindlTheme

@Composable
internal fun AuthButtonHolder(
    onKakaoLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
    ) {
        KakaoLoginButton(
            onClick = onKakaoLogin
        )
    }
}

@Preview
@Composable
private fun AuthButtonHolderPreview() {
    KindlTheme {
        AuthButtonHolder(
            onKakaoLogin = {}
        )
    }
}
