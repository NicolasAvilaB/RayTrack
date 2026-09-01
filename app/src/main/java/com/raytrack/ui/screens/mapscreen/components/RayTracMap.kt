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

@Composable
internal fun RayTracMap(
    modifier: Modifier
) {
    val santiago = LatLng(
        -33.4489,
        -70.6693
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            santiago,
            13f
        )
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,

        properties = MapProperties(
            isBuildingEnabled = true,
            isIndoorEnabled = false,
            isTrafficEnabled = false
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
        )
    )
}

@Preview
@Composable
fun MapScreenPreview() {
    RayTracMap(modifier = Modifier.fillMaxSize())
}
