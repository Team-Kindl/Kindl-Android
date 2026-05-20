package com.kindl.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kindl.presentation.home.component.ForbiddenAppItem

@Composable
internal fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        paddingValues = paddingValues,
        state = state
    )

}

@Composable
private fun HomeScreen(
    paddingValues: PaddingValues,
    state: HomeState
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                count = state.forbiddenApps.size,
                key = { index -> state.forbiddenApps[index].appPackageName },
                contentType = { "forbidden_app" }
            ) { index ->
                ForbiddenAppItem(item = state.forbiddenApps[index])
            }
        }
    }
}
