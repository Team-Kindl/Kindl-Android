package com.focpet.core.localstorage.constant

import androidx.datastore.preferences.core.intPreferencesKey

object DataStoreConstant {
    const val DATA_STORE_NAME = "kindl_datastore"
    val KEY_NOTIFICATION_DENIED_COUNT = intPreferencesKey("notification_denied_count")
}
