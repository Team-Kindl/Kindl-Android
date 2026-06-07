package com.kindl.data.forbidden.repositoryimpl

import android.content.Context
import android.content.Intent
import com.kindl.coroutine.qualifier.IoDispatcher
import com.kindl.domain.forbidden.model.InstalledAppModel
import com.kindl.domain.forbidden.repository.ForbiddenAppRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ForbiddenAppRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ForbiddenAppRepository {
    override suspend fun getInstalledUserApps(): List<InstalledAppModel> = withContext(ioDispatcher) {
        val pm = context.packageManager

        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        pm.queryIntentActivities(intent, 0)
            .filter { it.activityInfo.packageName != context.packageName }
            .map { resolveInfo ->
                InstalledAppModel(
                    packageName = resolveInfo.activityInfo.packageName,
                    appName = resolveInfo.loadLabel(pm).toString(),
                    // icon = resolveInfo.loadIcon(pm),
                )
            }
            .sortedBy { it.appName }
    }
}
