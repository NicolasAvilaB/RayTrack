package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.ui.screens.mapscreen.model.MapSearchResult
import kotlin.collections.take

@Composable
internal fun MapSearchResults(
    results: List<MapSearchResult>,
    onResultClick: (MapSearchResult) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        results
            .take(3)
            .forEachIndexed { index, result ->

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
