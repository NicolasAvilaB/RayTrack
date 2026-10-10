package com.raytrack.presentation.maps.effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.raytrack.presentation.maps.events.MapsUiEffects
import com.raytrack.presentation.maps.model.MapSearchResult
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun DisplayUiEffects(
    uiEffects: SharedFlow<MapsUiEffects>,
    showResults: (List<MapSearchResult>) -> Unit
) {
    LaunchedEffect(uiEffects) {
        uiEffects.collectLatest { effects ->
            when (effects) {
                is MapsUiEffects.ShowSearchResults ->
                    showResults(effects.results)

                MapsUiEffects.ClearSearchResults -> {
                    showResults(emptyList())
                }
            }
        }
    }
}
