package com.raytrack.ui.screens.homescreen.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

internal enum class SearchFilter {
    FAVORITES,
    RECENTS
}

internal fun SearchFilter.toFilter(): SearchFilter =
    when (this) {
        SearchFilter.FAVORITES -> SearchFilter.RECENTS
        SearchFilter.RECENTS -> SearchFilter.FAVORITES
    }

internal fun SearchFilter.toIconFilter(): ImageVector =
    when (this) {
        SearchFilter.FAVORITES -> Icons.Default.Star
        SearchFilter.RECENTS -> Icons.Default.History
    }
