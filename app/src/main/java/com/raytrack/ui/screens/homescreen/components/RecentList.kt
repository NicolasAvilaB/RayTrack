package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Park
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.ui.screens.homescreen.model.DestinationItem
import com.raytrack.ui.theme.RayTracColors

@Composable
fun RecentList(
    recents: List<DestinationItem>,
    onSelectRoute: (String) -> Unit
) {

    val visibleFavorites = recents.take(2)
    val hiddenCount = recents.size - visibleFavorites.size

    Column {

        SectionHeader(
            modifier = Modifier.padding(
                bottom = 8.dp
            ),
            title = "RECIENTES"
        )

        visibleFavorites.forEach {
            DestinationCard(
                modifier = Modifier.padding(bottom = 8.dp),
                destination = it
            ) {
                onSelectRoute(it.title)
            }
        }

        if (hiddenCount > 0) {

            Text(
                modifier = Modifier.align(Alignment.End),
                text = "+$hiddenCount destinos guardados",
                color = RayTracColors.TextSecondary
            )
        }
    }
}

@Preview
@Composable
fun RecentListPreview() {
    val recents = listOf(
        DestinationItem(
            "Parque Bicentenario",
            "Vitacura",
            Icons.Default.Park
        ),
        DestinationItem(
            "Costanera Center",
            "Providencia",
            Icons.Default.Apartment
        ),
        DestinationItem(
            "Bucalemu",
            "Playa",
            Icons.Default.BeachAccess
        )
    )
    RecentList(
        recents = recents,
        onSelectRoute = { }
    )
}
