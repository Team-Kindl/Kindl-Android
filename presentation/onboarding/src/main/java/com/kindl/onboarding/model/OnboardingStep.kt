package com.kindl.onboarding.model

enum class OnboardingStep(
    val progress: Float,        // 0f ~ 1f
    val stepNumber: Int?,       // 표시할 단계 번호 (MAIN/COMPLETE는 null)
    val totalSteps: Int = 3,
) {
    MAIN(
        progress = 0f,
        stepNumber = null,
    ), // 온보딩 진입 화면
    NAME(
        progress = 1f / 3f,
        stepNumber = 1,
    ), // 온보딩 첫번째 스텝 닉네임 설정 화면
    FORBIDDEN(
        progress = 2f / 3f,
        stepNumber = 2,
    ), // 온보딩 두번째 스텝 허용할 앱 설정 화면
    POINT(
        progress = 3f / 3f,
        stepNumber = 3,
    ), // 온보딩 세번째 스텝 포인트 시스템 설명 화면
    COMPLETE(
        progress = 1f,
        stepNumber = null,
    ); // 온보딩 완료 상태

    val showProgressBar: Boolean
        get() = stepNumber != null
}
