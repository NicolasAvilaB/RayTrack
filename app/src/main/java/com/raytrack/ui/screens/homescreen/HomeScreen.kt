package com.raytrack.ui.screens.homescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.presentation.home.HomeViewModel
import com.raytrack.ui.screens.homescreen.components.FavoriteList
import com.raytrack.ui.screens.homescreen.components.FuturisticBackground
import com.raytrack.ui.screens.homescreen.components.NewDestinationButton
import com.raytrack.ui.screens.homescreen.components.RayTracHeader
import com.raytrack.ui.screens.homescreen.components.RayTracRadar
import com.raytrack.ui.screens.homescreen.components.RayTracSearchBar
import com.raytrack.ui.screens.homescreen.components.RecentList
import com.raytrack.ui.screens.homescreen.components.SectionHeader
import com.raytrack.ui.screens.homescreen.components.StatusPanel
import com.raytrack.ui.screens.homescreen.model.BatteryUtils
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun HomeScreen(
    onSearch: (String) -> Unit,
    viewModel: HomeViewModel,
    onSelectRoute: (String) -> Unit,
) {

    val context = LocalContext.current

    val batteryLevel = remember {
        BatteryUtils.getBatteryLevel(context)
    }

    val query = remember { mutableStateOf("") }

    val results = viewModel.searchDestinations(
        query.value
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RayTracColors.Background)
    ) {

        FuturisticBackground()

        RayTracRadar(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .align(Alignment.BottomCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {

            RayTracHeader()

            RayTracSearchBar(
                query = query.value,
                onQueryChange = {
                    query.value = it
                }
            )

            SectionHeader("FAVORITOS")

            FavoriteList(
                favorites = viewModel.favorites,
                onSelectRoute = onSelectRoute
            )

            SectionHeader("RECIENTES")

            RecentList(
                recents = viewModel.recents,
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
            NewDestinationButton {
                onSearch(query.value)
            }

            StatusPanel(
                batteryLevel = batteryLevel
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onSearch = {
        },
        viewModel = HomeViewModel(),
        onSelectRoute = {

        },
    )
}
