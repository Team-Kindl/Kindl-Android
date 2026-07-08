package com.kindl.onboarding.model

import androidx.annotation.DrawableRes
import com.kindl.presentation.onboarding.R

enum class OnboardingMainInfoItem(
    val title: String,
    val description: String,
    @param:DrawableRes val iconRes: Int,
) {
    POINT_INFO(
        title = "포인트 스테이킹",
        description = "입장시 포인트를 걸고, 목표를 달성해보세요. 목표를 달성하면 포인트를\u2028다시 돌려받을 수 있어요.",
        iconRes = R.drawable.ic_onboarding_point,
    ),
    CONCENTRATION_ROOM(
        title = "실시간 집중방",
        description = "같은 목표를 가진 사람들과 함께\u2028집중해보세요. 언제 어디서든 참여할 수 있어요.",
        iconRes = R.drawable.ic_onboarding_group,
    ),
    AVATAR(
        title = "아바타 성장",
        description = "아바타는 집중 시간에 따라 성장하고, 실패가 반복될수록 컨디션이 하락해요.",
        iconRes = R.drawable.ic_onboarding_trophy,
    ),
    AI_COACH(
        title = "AI 집중 코치",
        description = "실패 로그와 앱 사용 패턴을 분석해서언제 집중이 무너지는지 파악하고\u2028현실적인 목표를 제안드려요.",
        iconRes = R.drawable.ic_onboarding_point,
    ),
}
