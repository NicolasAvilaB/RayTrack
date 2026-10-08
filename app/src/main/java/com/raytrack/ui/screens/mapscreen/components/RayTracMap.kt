package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.R
import com.raytrack.presentation.maps.model.MapSearchResult
import com.raytrack.ui.theme.RayTracColors
import kotlinx.coroutines.launch
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.CameraUpdate
import org.maplibre.compose.expressions.dsl.const
import org.maplibre.compose.expressions.dsl.image
import org.maplibre.compose.layers.SymbolLayer
import org.maplibre.compose.map.AndroidRenderMode
import org.maplibre.compose.map.MapUiOptions
import org.maplibre.compose.map.MaplibreMap
import org.maplibre.compose.map.StyleLoadState
import org.maplibre.compose.map.rememberMapState
import org.maplibre.compose.map.renderMode
import org.maplibre.compose.sources.GeoJsonData
import org.maplibre.compose.sources.rememberGeoJsonSource
import org.maplibre.compose.style.BaseStyle
import org.maplibre.spatialk.geojson.Feature
import org.maplibre.spatialk.geojson.Point
import org.maplibre.spatialk.geojson.Position

@Composable
internal fun RayTracMap(
    modifier: Modifier,
    currentLocation: Position?,
    selectedPlace: MapSearchResult?,
    onMapLoaded: () -> Unit,
    onMapError: () -> Unit,
) {
    if (currentLocation == null) return

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val styleJson = remember {
        context.resources
            .openRawResource(R.raw.raytrac_map_style)
            .bufferedReader()
            .use { it.readText() }
    }

    val mapState = rememberMapState(
        baseStyle = BaseStyle.Json(styleJson),
        initialCameraPosition = CameraPosition(
            target = currentLocation,
            zoom = 16.0
        )
    ) {
        val locationSource = rememberGeoJsonSource(
            data = GeoJsonData.Features(
                Feature(
                    geometry = Point(
                        coordinates = currentLocation
                    ),
                    properties = null
                )
            )
        )

        val infiniteTransition = rememberInfiniteTransition(
            label = "location-pulse"
        )

        val pulseScale = infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.8f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1400,
                    easing = EaseInOut
                ),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulse-scale"
        )

        SymbolLayer(
            id = "raytrac-current-location-pulse",
            source = locationSource,
            iconImage = image(
                painterResource(R.drawable.raytrac_location_pulse)
            ),
            iconSize = const(pulseScale.value),
            iconAllowOverlap = const(true),
            iconIgnorePlacement = const(true)
        )

        SymbolLayer(
            id = "raytrac-current-location-core",
            source = locationSource,
            iconImage = image(
                painterResource(R.drawable.raytrac_location_core)
            ),
            iconSize = const(1.4f),
            iconAllowOverlap = const(true),
            iconIgnorePlacement = const(true)
        )
    }

    MaplibreMap(
        modifier = modifier,
        state = mapState,
        uiOptions = MapUiOptions(
            from = MapUiOptions.Standard
        ) {
            renderMode = AndroidRenderMode.Texture
        }
    ) {
        if (selectedPlace == null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        end = 20.dp,
                        bottom = 20.dp
                    )
                    .size(56.dp)
                    .border(
                        1.dp,
                        RayTracColors.Border,
                        CircleShape
                    )
                    .background(
                        RayTracColors.DestinationCardDark.copy(alpha = 0.75f),
                        CircleShape
                    )
                    .clickable {
                        scope.launch {
                            mapState.animateCamera(
                                CameraUpdate(
                                    target = currentLocation,
                                    zoom = 16.0
                                )
                            )
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MyLocation,
                    contentDescription = "Mi ubicación",
                    tint = RayTracColors.PrimaryGlow
                )
            }
        }
    }

    LaunchedEffect(mapState.style.loadState) {
        when (mapState.style.loadState) {
            StyleLoadState.Ready -> onMapLoaded()
            is StyleLoadState.Failed -> onMapError()
            else -> Unit
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    RayTracMap(
        modifier = Modifier.fillMaxSize(),
        selectedPlace = MapSearchResult(
             title = "",
             address = "",
             latitude = 1.0,
             longitude = 1.0,
        ),
        currentLocation = Position(1.0, 1.0),
        onMapLoaded = {},
        onMapError = {},
    )
}
