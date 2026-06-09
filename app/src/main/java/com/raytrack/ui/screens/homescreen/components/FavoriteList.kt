package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.presentation.home.HomeViewModel
import com.raytrack.ui.screens.homescreen.HomeScreen
import com.raytrack.ui.screens.homescreen.model.DestinationItem
import com.raytrack.ui.theme.RayTracColors

@Composable
fun FavoriteList(
    favorites: List<DestinationItem>,
    onSelectRoute: (String) -> Unit
) {
    val visibleFavorites = favorites.take(2)
    val hiddenCount = favorites.size - visibleFavorites.size

    Column {

        SectionHeader(
            modifier = Modifier.padding(
                top = 20.dp,
                bottom = 8.dp
            ),
            title = "FAVORITOS"
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
fun FavoriteListPreview() {
    val favorites = listOf(
        DestinationItem(
            "Casa",
            "Los Leones 123",
            Icons.Default.Home
        ),
        DestinationItem(
            "Casa Perro",
            "Los Leones 123",
            Icons.Default.Home
        ),
        DestinationItem(
            "Mirador",
            "Farellones",
            Icons.Default.Landscape
        )
    )
    FavoriteList(
        favorites = favorites,
        onSelectRoute = { }
    )
}
