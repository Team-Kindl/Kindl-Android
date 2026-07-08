package com.kindl.onboarding.error

sealed interface NicknameError {
    data object TooShort : NicknameError
    data object TooLong : NicknameError
    data object Duplicated : NicknameError
}
