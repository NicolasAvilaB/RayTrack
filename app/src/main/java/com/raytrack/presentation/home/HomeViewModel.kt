package com.raytrack.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.raytrack.ui.screens.homescreen.model.DestinationItem
import com.raytrack.ui.screens.homescreen.model.SearchFilter

internal class HomeViewModel() : ViewModel() {

    val query = mutableStateOf("")
    val filter = mutableStateOf(SearchFilter.FAVORITES)

    val favorites = listOf(
        DestinationItem(
            "Casa",
            "Los Leones 123",
            Icons.Default.Home
        ),
        DestinationItem(
            "Casa Mi Polola",
            "Los Leones 123",
            Icons.Default.Home
        ),
        DestinationItem(
            "Casa Vecino",
            "Los Leones 123",
            Icons.Default.Home
        ),
        DestinationItem(
            "Casa Perro",
            "Los Leones 123",
            Icons.Default.Home
        ),
        DestinationItem(
            "Oficina",
            "Apoquindo 4501",
            Icons.Default.Work
        ),
        DestinationItem(
            "Mirador",
            "Farellones",
            Icons.Default.Landscape
        )
    )

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
        )
    )

    fun filteredFavorites(): List<DestinationItem> =
        if (filter.value == SearchFilter.FAVORITES)
            searchFavorites(query.value)
        else
            favorites

    fun filteredRecents(): List<DestinationItem> =
        if (filter.value == SearchFilter.RECENTS)
            searchRecents(query.value)
        else
            recents

    fun searchFavorites(
        query: String
    ): List<DestinationItem> {

        return favorites.filter {

            query.isBlank() ||

                    it.title.contains(
                        query,
                        ignoreCase = true
                    ) ||

                    it.subtitle.contains(
                        query,
                        ignoreCase = true
                    )
        }
    }

    fun searchRecents(
        query: String
    ): List<DestinationItem> {

        return recents.filter {

            query.isBlank() ||

                    it.title.contains(
                        query,
                        ignoreCase = true
                    ) ||

                    it.subtitle.contains(
                        query,
                        ignoreCase = true
                    )
        }
    }
}
