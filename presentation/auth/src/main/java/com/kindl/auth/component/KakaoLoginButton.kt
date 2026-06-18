package com.kindl.auth.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kindl.core.common.extension.noRippleClickable
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.presentation.auth.R

@Composable
internal fun KakaoLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFEE500),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 15.dp)
            .noRippleClickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.img_kakao_logo),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "카카오 로그인",
            style = KindlTheme.typography.regular.body3,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun KakaoLoginButtonPreview() {
    KindlTheme {
        KakaoLoginButton(
            onClick = {}
        )
    }
}
