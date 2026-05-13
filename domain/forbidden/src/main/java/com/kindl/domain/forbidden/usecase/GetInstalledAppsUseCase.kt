package com.kindl.domain.forbidden.usecase

import com.kindl.domain.forbidden.model.InstalledAppModel
import com.kindl.domain.forbidden.repository.ForbiddenAppRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class GetInstalledAppsUseCase @Inject constructor(
    private val appRepository: ForbiddenAppRepository,
) {
    operator fun invoke(): ImmutableList<InstalledAppModel> =
        appRepository.getInstalledUserApps().toImmutableList()
}
