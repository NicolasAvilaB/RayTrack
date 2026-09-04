package com.raytrack.ui.screens.mapscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raytrack.presentation.maps.MapsUiState
import com.raytrack.presentation.maps.MapsViewModel
import com.raytrack.ui.screens.homescreen.components.FuturisticBackground
import com.raytrack.ui.screens.mapscreen.components.DestinationBottomPanel
import com.raytrack.ui.screens.mapscreen.components.MapHeader
import com.raytrack.ui.screens.mapscreen.components.MapSearchBar
import com.raytrack.ui.screens.mapscreen.components.MapSearchResults
import com.raytrack.ui.screens.mapscreen.components.RayTracMap
import com.raytrack.ui.screens.mapscreen.model.MapSearchResult
import com.raytrack.ui.screens.mapscreen.stateview.ErrorMapView
import com.raytrack.ui.screens.mapscreen.stateview.LoadingMapView
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun MapScreen(
    onNavBack: () -> Unit,
    onNavToAr: () -> Unit,
    viewModel: MapsViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val query = viewModel.inputQuery.value
    val showSearchResults = viewModel.showSearchResults.value

    val selectedPlace = viewModel.selectedPlace

    val keyboardController = LocalSoftwareKeyboardController.current

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
                onNavBack = {
                    onNavBack.invoke()
                }
            )

            MapSearchBar(
                query = query,
                showResults = showSearchResults,
                onToggleResults = {
                    viewModel.toggleSearchResults()
                    viewModel.clearSelectedPlace()
                    viewModel.clearQuery()
                },
                onQueryChange = viewModel::onQueryChange
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp
                    )
            ) {

                RayTracMap(
                    modifier = Modifier.fillMaxSize(),
                    currentLocation = viewModel.currentLocation.value,
                    onMapLoaded = viewModel::onMapLoaded

                )

                when (uiState) {

                    is MapsUiState.LoadingUiState -> {
                        LoadingMapView(
                            visible = true
                        )
                    }

                    is MapsUiState.DisplayUiState -> {
                        LoadingMapView(
                            visible = false
                        )
                    }
                }

                if (showSearchResults && query.isNotBlank()) {

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
                        modifier = Modifier
                            .fillMaxWidth(),
                        results = fakeResults,
                        onResultClick = { result ->
                            keyboardController?.hide()
                            viewModel.selectPlace(result)
                            viewModel.clearQuery()
                        }
                    )
                }
            }

            DestinationBottomPanel(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                title = selectedPlace?.title.orEmpty(),
                address = selectedPlace?.address.orEmpty(),
                visible = selectedPlace != null,
                isFavorite = false,
                onToggleFavorite = {},
                onStartAr = onNavToAr,
            )
        }
    }
}


@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(onNavBack = { }, onNavToAr = { },)
}
