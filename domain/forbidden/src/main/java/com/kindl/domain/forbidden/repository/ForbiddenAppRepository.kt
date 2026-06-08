package com.kindl.domain.forbidden.repository

import com.kindl.domain.forbidden.model.InstalledAppModel

interface ForbiddenAppRepository {
    suspend fun getInstalledUserApps(): List<InstalledAppModel>
}
