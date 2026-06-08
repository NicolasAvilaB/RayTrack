package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.raytrack.ui.screens.homescreen.model.DestinationItem
import com.raytrack.ui.theme.RayTracColors

@Composable
fun FavoriteList(
    favorites: List<DestinationItem>,
    onSelectRoute: (String) -> Unit
) {
    val visibleFavorites = favorites.take(2)
    val hiddenCount = favorites.size - visibleFavorites.size

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        visibleFavorites.forEach {
            DestinationCard(
                destination = it
            ) {
                onSelectRoute(it.title)
            }
        }
        if (hiddenCount > 0) {

            Text(
                text = "+$hiddenCount destinos guardados",
                color = RayTracColors.TextSecondary
            )
        }
    }
}