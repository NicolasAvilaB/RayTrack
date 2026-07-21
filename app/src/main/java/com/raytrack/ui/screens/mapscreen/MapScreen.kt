package com.raytrack.ui.screens.mapscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.presentation.maps.MapsViewModel
import com.raytrack.ui.screens.homescreen.components.FuturisticBackground
import com.raytrack.ui.screens.homescreen.components.RayTracHeader
import com.raytrack.ui.screens.homescreen.components.RayTracSearchBar
import com.raytrack.ui.screens.mapscreen.components.MapHeader
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun MapScreen(
    onNavBack: () -> Unit,
    onNavToAr: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RayTracColors.Background)
    ) {

        FuturisticBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {

            MapHeader(
                onNavBack = onNavBack
            )
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(onNavBack = { }, onNavToAr = { })
}