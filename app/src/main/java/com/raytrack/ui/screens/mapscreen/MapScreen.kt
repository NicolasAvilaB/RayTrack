package com.raytrack.ui.screens.mapscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.presentation.maps.MapsViewModel
import com.raytrack.ui.screens.homescreen.components.FuturisticBackground
import com.raytrack.ui.screens.mapscreen.components.DestinationBottomPanel
import com.raytrack.ui.screens.mapscreen.components.MapHeader
import com.raytrack.ui.screens.mapscreen.components.MapSearchBar
import com.raytrack.ui.screens.mapscreen.components.MapSearchResults
import com.raytrack.ui.screens.mapscreen.components.RayTracMap
import com.raytrack.ui.screens.mapscreen.model.MapSearchResult
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun MapScreen(
    onNavBack: () -> Unit,
    onNavToAr: () -> Unit
) {

    var query by remember { mutableStateOf("") }

    var destinationSelected by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RayTracColors.Background)
    ) {

        FuturisticBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(horizontal = 10.dp)
        ) {

            MapHeader(
                onNavBack = onNavBack
            )

            MapSearchBar(
                query = query,
                onQueryChange = {
                    query = it
                }
            )

            if (query.isNotBlank()) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                    val fakeResults = listOf(
                        MapSearchResult(
                            title = "Costanera Center",
                            address = "Av. Andrés Bello 2425, Providencia",
                            latitude = -33.4172,
                            longitude = -70.6067
                        ),
                        MapSearchResult(
                            title = "Parque Bicentenario",
                            address = "Av. Bicentenario 3800, Vitacura",
                            latitude = -33.3895,
                            longitude = -70.5824
                        ),
                        MapSearchResult(
                            title = "Cerro San Cristóbal",
                            address = "Parque Metropolitano, Santiago",
                            latitude = -33.4255,
                            longitude = -70.6358
                        )
                    )
                    MapSearchResults(
                        results = fakeResults,
                        onResultClick = { }
                    )

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                RayTracMap(modifier = Modifier.fillMaxSize())
            }

            DestinationBottomPanel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                title = "Costanera Center",
                address = "Av. Andrés Bello 2425\nProvidencia, Santiago",
                isFavorite = false,
                onToggleFavorite = {},
                onStartAr = onNavToAr,
                visible = destinationSelected
            )
        }
    }
}


@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(onNavBack = { }, onNavToAr = { })
}
