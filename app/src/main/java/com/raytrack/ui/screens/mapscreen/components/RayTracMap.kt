package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.foundation.layout.fillMaxSize
import com.google.android.gms.maps.model.LatLng
import androidx.compose.runtime.Composable
import com.google.maps.android.compose.GoogleMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.raytrack.ui.screens.mapscreen.model.RayTracMapStyle

@Composable
internal fun RayTracMap(
    modifier: Modifier,
    currentLocation: LatLng?,
    onMapLoaded: () -> Unit
) {
    val cameraPositionState = rememberCameraPositionState {
        currentLocation?.let { location ->
            position = CameraPosition.fromLatLngZoom(
                location,
                16f
            )
        }
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,

        properties = MapProperties(
            isBuildingEnabled = true,
            isIndoorEnabled = false,
            isTrafficEnabled = false,
            mapStyleOptions = RayTracMapStyle()
        ),

        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = false,
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false,
            rotationGesturesEnabled = true,
            scrollGesturesEnabled = true,
            tiltGesturesEnabled = true,
            zoomGesturesEnabled = true
        ),
        onMapLoaded = onMapLoaded
    )
}

@Preview
@Composable
fun MapScreenPreview() {
    RayTracMap(
        modifier = Modifier.fillMaxSize(),
        currentLocation = LatLng(1.2,1.3),
        onMapLoaded = { }
    )
}
