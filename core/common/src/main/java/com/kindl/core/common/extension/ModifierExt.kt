package com.kindl.core.common.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

fun Modifier.forcePixelToDp(painter: Painter): Modifier {
    return this.size(
        width = painter.intrinsicSize.width.dp,
        height = painter.intrinsicSize.height.dp
    )
}

// LazyColumn 같이 스크롤 후 더 이상 스크롤할 곳이 없을 때 바텀시트 움직임 막기
fun Modifier.disableNestedScroll(): Modifier = composed {
    val connection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                return available
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                return available
            }
        }
    }
    this.nestedScroll(connection)
}

/**
 * 바텀시트 내부에서 위로 당기는(Upward) 드래그, 스크롤 이벤트만 가로채서 소비하는 Modifier
 */
fun Modifier.disableUpWardEvent(): Modifier = this.pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent(PointerEventPass.Main)

            event.changes.forEach { change ->
                if (!change.isConsumed) {
                    val dy = change.position.y - change.previousPosition.y

                    // 손가락이 위로 향할 때만 차단
                    if (dy < 0) {
                        change.consume()
                    }
                }
            }
        }
    }
}
