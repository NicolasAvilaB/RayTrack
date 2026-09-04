package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.screens.mapscreen.model.MapSearchResult
import com.raytrack.ui.theme.RayTracColors
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
internal fun MapSearchResultItem(
    index: Int,
    result: MapSearchResult,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(18.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            1.dp,
            RayTracColors.PrimaryGlow.copy(alpha = 0.18f)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        start = Offset(0f, 0f),
                        end = Offset(900f, 900f),
                        colors = listOf(
                            RayTracColors.GlassDark.copy(alpha = 0.28f),
                            RayTracColors.GlassBlue.copy(alpha = 0.14f),
                            RayTracColors.GlassGlow.copy(alpha = 0.05f),
                            Color.Transparent
                        )
                    )
                )
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "%02d".format(index),
                color = RayTracColors.PrimaryGlow,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            )

            Column(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .weight(1f)
            ) {
                Text(
                    text = result.title,
                    color = RayTracColors.TextPrimary,
                    fontSize = 18.sp
                )

                Text(
                    text = result.address,
                    color = RayTracColors.TextSecondary,
                    fontSize = 14.sp
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = RayTracColors.PrimaryGlow
            )
        }
    }
}

@Preview
@Composable
fun MapSearchResultItemPreview() {
    MapSearchResultItem(
        index = 1,
        result = MapSearchResult(
            title = "Cerro San Cristóbal",
            address = "Parque Metropolitano, Santiago",
            latitude = -33.4255,
            longitude = -70.6358
        ),
        onClick = { },
    )
}
