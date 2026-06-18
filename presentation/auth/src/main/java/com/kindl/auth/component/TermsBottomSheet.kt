package com.kindl.auth.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.auth.model.Terms
import com.kindl.core.common.extension.noRippleClickable
import com.kindl.core.designsystem.component.bottomsheet.KindlBottomSheet
import com.kindl.core.designsystem.component.button.KindlButton
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.presentation.auth.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TermsBottomSheet(
    onDismiss: () -> Unit,
    onClickTerms: (Terms) -> Unit,
    navigateToTerms: (Terms) -> Unit,
    modifier: Modifier = Modifier
) {
    KindlBottomSheet(
        onDismiss = onDismiss,
        modifier = modifier
    ) {
        TermsBottomSheetContent(
            onClickTerms = onClickTerms,
            navigateToTerms = navigateToTerms,
            navigateToOnboarding = onDismiss // Todo: navigate 코드 작성
        )
    }
}

@Composable
private fun TermsBottomSheetContent(
    onClickTerms: (Terms) -> Unit,
    navigateToTerms: (Terms) -> Unit,
    navigateToOnboarding: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = KindlTheme.colors.slate700,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ),
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "앱 사용을 위해 약관에 동의해주세요.\n" +
                    "서비스 이용에 필수적인 약관들이에요.",
            style = KindlTheme.typography.regular.body1,
            color = KindlTheme.colors.white,
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        TermsItem(
            termsText = "서비스 이용약관",
            isChecked = true,
            onClickTerms = {
                onClickTerms(Terms.SERVICE_AGREEMENT)
            },
            navigateToTerms = {
                navigateToTerms(Terms.SERVICE_AGREEMENT)
            },
        )

        TermsItem(
            termsText = "서비스 이용약관",
            isChecked = false,
            onClickTerms = {
                onClickTerms(Terms.PRIVACY_AGREEMENT)
            },
            navigateToTerms = {
                navigateToTerms(Terms.PRIVACY_AGREEMENT)
            },
        )

        Spacer(modifier = Modifier.height(32.dp))

        KindlButton(
            text = "다음",
            onClick = navigateToOnboarding,
            isEnabled = true, // Todo: 정책이 모두 선택되고 활성화되어야함
            modifier = Modifier
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
private fun TermsItem(
    termsText: String,
    isChecked: Boolean,
    onClickTerms: () -> Unit,
    navigateToTerms: () -> Unit,
    modifier: Modifier = Modifier
) {
    val textColor by animateColorAsState(
        targetValue = if (isChecked) KindlTheme.colors.white else KindlTheme.colors.slate400,
        animationSpec = tween(durationMillis = 200),
        label = "TextColorAnimation"
    )

    val checkboxBgColor by animateColorAsState(
        targetValue = if (isChecked) KindlTheme.colors.coral500 else Color.Transparent,
        animationSpec = tween(durationMillis = 200),
        label = "CheckboxBgColorAnimation"
    )

    val checkboxBorderColor by animateColorAsState(
        targetValue = if (isChecked) KindlTheme.colors.coral500 else KindlTheme.colors.slate400,
        animationSpec = tween(durationMillis = 200),
        label = "CheckboxBorderColorAnimation"
    )

    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            modifier = Modifier
                .weight(1f)
                .noRippleClickable(onClickTerms),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(11.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(
                        color = checkboxBgColor,
                        shape = RoundedCornerShape(3.dp)
                    )
                    .border(
                        width = 1.5.dp,
                        color = checkboxBorderColor,
                        shape = RoundedCornerShape(3.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isChecked) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_check),
                        contentDescription = null,
                        tint = KindlTheme.colors.slate800,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

            Text(
                text = termsText,
                style = KindlTheme.typography.regular.body3,
                color = textColor,
            )
        }

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(20.dp)
                .noRippleClickable(onClick = navigateToTerms)
        )
    }
}

@Preview
@Composable
private fun TermsBottomSheetPreview() {
    KindlTheme {
        TermsBottomSheetContent(
            onClickTerms = {},
            navigateToTerms = {},
            navigateToOnboarding = {}
        )
    }
}
