package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.raytrack.R
import org.maplibre.compose.camera.CameraPosition
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
    onMapLoaded: () -> Unit,
    onMapError: () -> Unit
) {
    if (currentLocation == null) return

    val context = LocalContext.current

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
            iconSize = const(1f),
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
    )

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
        currentLocation = Position(1.0, 1.0),
        onMapLoaded = {},
        onMapError = {}
    )
}
