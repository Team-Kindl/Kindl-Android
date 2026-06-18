package com.kindl.auth.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.auth.model.Terms
import com.kindl.core.common.extension.noRippleClickable
import com.kindl.core.designsystem.theme.KindlTheme

@Composable
internal fun AuthTermsHolder(
    onClickTerms: (Terms) -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "로그인하시면 아래 내용에 동의하는 것으로 간주됩니다.",
            style = KindlTheme.typography.regular.body4,
            color = KindlTheme.colors.slate400
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TermsText(
                termsText = "개인정보 처리방침",
                onClickTerms = {
                    onClickTerms(Terms.PRIVACY_AGREEMENT)
                }
            )

            Spacer(modifier = Modifier.width(30.dp))

            TermsText(
                termsText = "서비스 이용약관",
                onClickTerms = {
                    onClickTerms(Terms.SERVICE_AGREEMENT)
                }
            )
        }
    }
}

@Composable
private fun TermsText(
    termsText: String,
    onClickTerms: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = termsText,
        style = KindlTheme.typography.regular.body4,
        color = KindlTheme.colors.white,
        textDecoration = TextDecoration.Underline,
        modifier = modifier
            .noRippleClickable(onClick = onClickTerms)
    )
}

@Preview
@Composable
private fun AuthTermsHolderPreview() {
    KindlTheme {
        AuthTermsHolder(
            onClickTerms = {}
        )
    }
}
