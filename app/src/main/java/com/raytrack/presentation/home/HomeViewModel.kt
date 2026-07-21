package com.raytrack.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.raytrack.data.localdb.entity.destination.DestinationEntity
import com.raytrack.data.models.DestinationType
import com.raytrack.data.repository.destination.usecase.DestinationCommandUseCase
import com.raytrack.data.repository.destination.usecase.GetDestinationUseCase
import com.raytrack.data.repository.destination.usecase.DestinationSearchUseCase
import com.raytrack.ui.screens.homescreen.model.DestinationItem
import com.raytrack.ui.screens.homescreen.model.SearchFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

internal class HomeViewModel(
    private val searchDestUseCase: DestinationSearchUseCase,
    private val getDestUseCase: GetDestinationUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _filter = MutableStateFlow(SearchFilter.FAVORITES)
    val filter = _filter.asStateFlow()

    val favorites =
        query.flatMapLatest { text ->
            if (text.isBlank()) {
                getDestUseCase.getFavorites()
            } else {
                searchDestUseCase.searchFavorites(text)
            }
        }.map { list ->
            list.map { it.toDestinationItem() }
        }

    val recents =
        query.flatMapLatest { text ->
            if (text.isBlank()) {
                getDestUseCase.getRecents()
            } else {
                searchDestUseCase.searchRecents(text)
            }
        }.map { list ->
            list.map { it.toDestinationItem() }
        }

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun onFilterChange(filter: SearchFilter) {
        _filter.value = filter
    }

    internal fun DestinationEntity.toDestinationItem() =
        DestinationItem(
            title = title,
            subtitle = address,
            icon = icon.toIcon()
        )

     internal fun DestinationType.toIcon(): ImageVector =
        when (this) {
            DestinationType.HOME -> Icons.Default.Home
            DestinationType.WORK -> Icons.Default.Work
            DestinationType.PARK -> Icons.Default.Park
            DestinationType.LANDSCAPE -> Icons.Default.Landscape
            DestinationType.BEACH -> Icons.Default.BeachAccess
            DestinationType.OTHER -> Icons.Default.Place
            else -> Icons.Default.Place
        }
}
