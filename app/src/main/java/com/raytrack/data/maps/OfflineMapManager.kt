package com.raytrack.data.maps

import android.util.Log
import kotlinx.coroutines.flow.first
import org.maplibre.compose.map.DefaultMapRuntime
import org.maplibre.compose.offline.OfflineManager
import org.maplibre.compose.offline.OfflineManagerState
import org.maplibre.compose.offline.OfflinePackDefinition
import org.maplibre.spatialk.geojson.BoundingBox

internal class OfflineMapManager {

    private companion object {
        const val TEST_REGION_ID = "santiago-test"
    }

    private val offlineManager: OfflineManager =
        DefaultMapRuntime.instance.offlineManager

    suspend fun ensureTestRegion() {
        val state = offlineManager.state.first {
            it !is OfflineManagerState.Loading
        }

        when (state) {
            is OfflineManagerState.Ready -> {
                val existingPack = state.packs.firstOrNull {
                    it.metadata.value?.contentEquals(
                        TEST_REGION_ID.encodeToByteArray()
                    ) == true
                }

                if (existingPack != null) {
                    Log.d(
                        "OfflineMapManager",
                        "Pack already exists: $TEST_REGION_ID"
                    )
                    return
                }
            }

            is OfflineManagerState.Failed -> {
                throw state.cause
            }

            OfflineManagerState.Loading -> {
                error("Offline manager is still loading")
            }
        }

        val pack = offlineManager.create(
            definition = OfflinePackDefinition.TilePyramid(
                styleUrl = "https://tiles.openfreemap.org/styles/liberty",
                bounds = BoundingBox(
                    west = -70.75,
                    south = -33.55,
                    east = -70.60,
                    north = -33.35
                ),
                pixelRatio = 1f,
                minZoom = 0,
                maxZoom = 16
            ),
            metadata = TEST_REGION_ID.encodeToByteArray()
        )

        offlineManager.resume(pack)
    }
}