package com.kindl.core.localstorage.permissions

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences
import com.kindl.core.localstorage.constant.DataStoreConstant.KEY_NOTIFICATION_DENIED_COUNT
import com.kindl.core.common.util.suspendRunCatching
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PermissionInfoManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : PermissionInfoManager {
    override suspend fun saveNotificationDeniedCount() {
        suspendRunCatching {
            dataStore.edit { preferences ->
                val currentCount = preferences[KEY_NOTIFICATION_DENIED_COUNT] ?: 0
                preferences[KEY_NOTIFICATION_DENIED_COUNT] = currentCount + 1
            }
        }
    }

    override val notificationDeniedCount: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[KEY_NOTIFICATION_DENIED_COUNT] ?: 0
        }
}