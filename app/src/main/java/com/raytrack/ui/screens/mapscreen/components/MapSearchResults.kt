package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.presentation.maps.model.MapSearchResult
import com.raytrack.ui.theme.RayTracColors.Background

@Composable
internal fun MapSearchResults(
    results: List<MapSearchResult>,
    onResultClick: (MapSearchResult) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .height(230.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(
                        0f to Background,
                        0.85f to Background,
                        1f to Transparent
                    ),
                    blendMode = BlendMode.DstIn
                )
            },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(results) { index, result ->
            MapSearchResultItem(
                index = index + 1,
                result = result,
                onClick = {
                    onResultClick(result)
                }
            )
        }
    }
}

@Preview
@Composable
fun MapSearchResultsPreview() {
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
        onResultClick = { },
    )
}
