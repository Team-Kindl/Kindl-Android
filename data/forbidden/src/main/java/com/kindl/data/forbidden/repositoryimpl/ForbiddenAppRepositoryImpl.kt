package com.kindl.data.forbidden.repositoryimpl

import android.content.Context
import android.content.Intent
import com.kindl.domain.forbidden.model.InstalledAppModel
import com.kindl.domain.forbidden.repository.ForbiddenAppRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class ForbiddenAppRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : ForbiddenAppRepository {
    override fun getInstalledUserApps(): List<InstalledAppModel> {
        val pm = context.packageManager

        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        return pm.queryIntentActivities(intent, 0)
            .filter { it.activityInfo.packageName != context.packageName }
            .map { resolveInfo ->
                InstalledAppModel(
                    packageName = resolveInfo.activityInfo.packageName,
                    appName = resolveInfo.loadLabel(pm).toString(),
                    //icon = resolveInfo.loadIcon(pm),
                )
            }
            .sortedBy { it.appName }
    }
}
