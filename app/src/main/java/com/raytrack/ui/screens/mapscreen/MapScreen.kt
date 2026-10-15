package com.raytrack.ui.screens.mapscreen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raytrack.presentation.maps.MapsViewModel
import com.raytrack.presentation.maps.effects.DisplayUiEffects
import com.raytrack.presentation.maps.events.MapsUiState.DisplayUiState
import com.raytrack.presentation.maps.events.MapsUiState.ErrorUiState
import com.raytrack.presentation.maps.events.MapsUiState.LoadingUiState
import com.raytrack.ui.screens.homescreen.components.FuturisticBackground
import com.raytrack.ui.screens.keyboardscreen.RayTracKeyboard
import com.raytrack.ui.screens.keyboardscreen.rememberSystemLanguage
import com.raytrack.ui.screens.mapscreen.components.DestinationBottomPanel
import com.raytrack.ui.screens.mapscreen.components.MapHeader
import com.raytrack.ui.screens.mapscreen.components.MapSearchBar
import com.raytrack.ui.screens.mapscreen.components.MapSearchResults
import com.raytrack.ui.screens.mapscreen.components.RayTracMap
import com.raytrack.ui.screens.mapscreen.stateview.ErrorMapView
import com.raytrack.ui.screens.mapscreen.stateview.LoadingMapView
import com.raytrack.ui.theme.RayTracColors
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
internal fun MapScreen(
    onNavBack: () -> Unit,
    onNavToAr: () -> Unit,
    viewModel: MapsViewModel = viewModel(),
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    val systemLanguage = rememberSystemLanguage()
    val useSystemKeyboard = systemLanguage == "ja"

    val uiState by viewModel.uiState().collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val currentLocation by viewModel.currentLocation
    val selectedPlace = viewModel.selectedPlace

    var showRayTracKeyboard by remember { mutableStateOf(false) }
    var query by viewModel.inputQuery
    val showSearchResults by viewModel.showSearchResults

    val hasLocationPermission = remember(context) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (granted) {
            viewModel.startLocationUpdates()
        }
    }

    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            viewModel.startLocationUpdates()
        } else {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(horizontal = 10.dp)
        ) {
            MapHeader(onNavBack = onNavBack)

            MapSearchBar(
                query = query,
                showResults = showSearchResults,
                useSystemKeyboard = useSystemKeyboard,
                onToggleResults = {
                    showRayTracKeyboard = false
                    keyboardController?.hide()
                    viewModel.toggleSearchResults()
                    viewModel.clearSelectedPlace()
                    viewModel.clearQuery()
                    viewModel.updateSearchResults(emptyList())
                },
                onKeyboardToggle = {
                    showRayTracKeyboard = true
                },
                onKeyboardHide = {
                    showRayTracKeyboard = false
                    keyboardController?.hide()
                },
                onQueryChange = { text ->
                    query = text
                    if (showSearchResults) {
                        viewModel.updateSearchResults(emptyList())
                    }
                },
                onSearch = { searchQuery ->
                    showRayTracKeyboard = false
                    viewModel.clearSelectedPlace()
                    keyboardController?.hide()
                    if (showSearchResults) {
                        scope.launch {
                            viewModel.searchListGlassResults(searchQuery).collect()
                        }
                    }
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                RayTracMap(
                    modifier = Modifier.fillMaxSize(),
                    currentLocation = currentLocation,
                    selectedPlace = selectedPlace,
                    onMapLoaded = viewModel::onMapLoaded,
                    onMapError = viewModel::onMapError
                )

                when (uiState) {
                    is LoadingUiState -> LoadingMapView()
                    is DisplayUiState -> {}
                    is ErrorUiState -> ErrorMapView()
                }

                if (showSearchResults && searchResults.isNotEmpty()) {
                    MapSearchResults(
                        modifier = Modifier.fillMaxWidth(),
                        results = searchResults,
                        onResultClick = { result ->
                            if (result.latitude != 0.0){
                                showRayTracKeyboard = false
                                keyboardController?.hide()
                                viewModel.selectPlace(result)
                                viewModel.updateSearchResults(emptyList())
                                viewModel.clearQuery()
                            }
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

        if (showRayTracKeyboard && !useSystemKeyboard) {
            RayTracKeyboard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                showKeyboard = true,
                onCharacter = { character ->
                    query += character
                    viewModel.updateSearchResults(emptyList())
                },
                onBackspace = {
                    if (query.isNotEmpty()) {
                        query = query.dropLast(1)
                        viewModel.updateSearchResults(emptyList())
                    }
                },
                onSpace = {
                    query += " "
                    viewModel.updateSearchResults(emptyList())
                },
                onSearch = {
                        showRayTracKeyboard = false
                        keyboardController?.hide()
                        viewModel.clearSelectedPlace()
                    if (showSearchResults) {

                        scope.launch {
                            viewModel.searchListGlassResults(query).collect()
                        }
                    }
                }
            )
        }
    }

    DisplayUiEffects(
        uiEffects = viewModel.uiEffect(),
        showResults = viewModel::updateSearchResults
    )
}

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(onNavBack = {}, onNavToAr = {})
}