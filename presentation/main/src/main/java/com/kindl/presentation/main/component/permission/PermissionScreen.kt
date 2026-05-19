package com.kindl.presentation.main.component.permission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.kindl.core.designsystem.theme.KindlTheme
import com.kindl.core.permission.PermissionType
import kotlinx.collections.immutable.ImmutableList

@Composable
fun PermissionScreen(
    missingPermissions: ImmutableList<PermissionType>,
    isPermanentlyDenied: Boolean,
    notificationDeniedCount: Int,
    onRequestPermission: (PermissionType) -> Unit,
    onPermissionGranted: () -> Unit,
) {
    LifecycleResumeEffect(Unit) {
        onPermissionGranted()

        onPauseOrDispose {

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        missingPermissions.forEach { type ->
            PermissionItem(
                type = type,
                isPermanentlyDenied = isPermanentlyDenied && type == PermissionType.POST_NOTIFICATIONS,
                notificationDeniedCount = notificationDeniedCount,
                onRequest = { onRequestPermission(type) },
            )
        }
    }
}

@Composable
private fun PermissionItem(
    type: PermissionType,
    isPermanentlyDenied: Boolean,
    notificationDeniedCount: Int,
    onRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val (title, description) = when (type) {
        PermissionType.USAGE_STATS -> "앱 사용 정보" to "금지 앱 감지를 위해 필요해요"
        PermissionType.OVERLAY -> "다른 앱 위에 표시" to "집중 방해 시 경고창 표시에 필요해요"
        PermissionType.BATTERY_OPTIMIZATION -> "배터리 최적화 제외" to "백그라운드 감지를 위해 필요해요"
        PermissionType.POST_NOTIFICATIONS -> "알림 권한" to "집중 시간 알림을 위해 필요해요"
        PermissionType.ACCESSIBILITY -> "접근 권한" to "앱 접근 시 필요해요"
    }

    val buttonText = when {
        type == PermissionType.POST_NOTIFICATIONS && isPermanentlyDenied -> "설정에서 허용"
        else -> "권한 허용"
    }

    val descriptionText = when {
        type == PermissionType.POST_NOTIFICATIONS && isPermanentlyDenied ->
            "설정에서 직접 알림 권한을 허용해주세요"
        else -> description
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title)
            Text(text = descriptionText)
        }
        Button(onClick = onRequest) {
            Text(text = buttonText)
        }
    }
}

@Preview
@Composable
private fun PermissionItemPreview() {
    KindlTheme {
        PermissionItem(
            type = PermissionType.POST_NOTIFICATIONS,
            isPermanentlyDenied = false,
            notificationDeniedCount = 0,
            onRequest = {},
        )
    }
}
