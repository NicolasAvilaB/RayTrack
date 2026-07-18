package com.raytrack.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.raytrack.data.models.DestinationType
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase
import com.raytrack.data.repository.destination.usecase.GetDestinationUseCase
import com.raytrack.data.repository.destination.usecase.DestinationSearchUseCase
import com.raytrack.ui.screens.homescreen.model.DestinationItem
import kotlinx.coroutines.flow.map

internal class HomeViewModel(
    private val searchDestUseCase: DestinationSearchUseCase,
    private val getDestUseCase: GetDestinationUseCase,
    private val commandDestUseCase: DestinationCommandUseCase
) : ViewModel() {

    val favorites = listOf(
        getDestUseCase.getFavorites().map {
            it.forEach {
                DestinationItem(
                    title = it.title,
                    subtitle = it.address,
                    icon = it.icon.toIcon()
                )
            }
        }
    )

    val recents = listOf(
        getDestUseCase.getRecents().map {
            it.forEach {
                DestinationItem(
                    title = it.title,
                    subtitle = it.address,
                    icon = it.icon.toIcon()
                )
            }
        }
    )

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

     fun DestinationType.toIcon(): ImageVector =
        when (this) {
            DestinationType.HOME -> Icons.Default.Home
            DestinationType.WORK -> Icons.Default.Work
            DestinationType.PARK -> Icons.Default.Park
            DestinationType.LANDSCAPE -> Icons.Default.Landscape
            DestinationType.BEACH -> Icons.Default.BeachAccess
            DestinationType.OTHER -> Icons.Default.Place
            else -> {}
        }
}
