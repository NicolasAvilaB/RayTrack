package com.raytrack.ui.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.raytrack.presentation.home.HomeViewModel
import com.raytrack.ui.screens.homescreen.components.FavoriteList
import com.raytrack.ui.screens.homescreen.components.FuturisticBackground
import com.raytrack.ui.screens.homescreen.components.NewDestinationButton
import com.raytrack.ui.screens.homescreen.components.RayTracHeader
import com.raytrack.ui.screens.homescreen.components.RayTracRadar
import com.raytrack.ui.screens.homescreen.components.RayTracSearchBar
import com.raytrack.ui.screens.homescreen.components.RecentList
import com.raytrack.ui.screens.homescreen.components.StatusPanel
import com.raytrack.ui.screens.homescreen.model.BatteryUtils
import com.raytrack.ui.screens.keyboardscreen.RayTracKeyboard
import com.raytrack.ui.screens.keyboardscreen.rememberSystemLanguage
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun HomeScreen(
    onNavToMaps: () -> Unit,
    viewModel: HomeViewModel,
    onSelectRoute: (String) -> Unit,
) {
    val systemLanguage = rememberSystemLanguage()
    val useSystemKeyboard = systemLanguage == "ja"

    var showRayTracKeyboard by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val context = LocalContext.current

    val batteryLevel = remember {
        BatteryUtils.getBatteryLevel(context)
    }

    val query by viewModel.query.collectAsState()
    val filter by viewModel.filter.collectAsState()

    val favorites by viewModel.favorites.collectAsState(emptyList())
    val recents by viewModel.recents.collectAsState(emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RayTracColors.Background)
    ) {

        FuturisticBackground(
            onHideKeyboard = {
                showRayTracKeyboard = false
                keyboardController?.hide()
            }
        )

        RayTracRadar(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .offset(y = (-100).dp)
                .align(Alignment.BottomCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {

            RayTracHeader()

            RayTracSearchBar(
                query = query,
                filter = filter,
                useSystemKeyboard = useSystemKeyboard,
                onKeyboardToggle = {
                    showRayTracKeyboard = true
                },
                onKeyboardHide = {
                    showRayTracKeyboard = false
                    keyboardController?.hide()
                },
                onQueryChange = viewModel::onQueryChange,
                onFilterChange = viewModel::onFilterChange
            )

            FavoriteList(
                favorites = favorites,
                onSelectRoute = onSelectRoute
            )

            RecentList(
                recents = recents,
                onSelectRoute = onSelectRoute
            )

        }

        Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            NewDestinationButton(
                modifier = Modifier.padding(
                    start = 14.dp,
                    end = 14.dp,
                    bottom = 10.dp
                ),
                onClick = onNavToMaps
            )

            StatusPanel(
                batteryLevel = batteryLevel
            )
        }
        if (showRayTracKeyboard && !useSystemKeyboard) {
            RayTracKeyboard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                showKeyboard = showRayTracKeyboard,
                onCharacter = { character ->
                    viewModel.onQueryChange(query + character)
                },
                onBackspace = {
                    if (query.isNotEmpty()) {
                        viewModel.onQueryChange(query.dropLast(1))
                    }
                },
                onSpace = {
                    viewModel.onQueryChange(query + " ")
                },
                onSearch = {
                    showRayTracKeyboard = false
                    keyboardController?.hide()
                }
            )
        }
    }
}
