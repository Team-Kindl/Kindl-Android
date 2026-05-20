package com.kindl.domain.forbidden.repository

import com.kindl.domain.forbidden.model.InstalledAppModel

interface ForbiddenAppRepository {
    fun getInstalledUserApps(): List<InstalledAppModel>
}
