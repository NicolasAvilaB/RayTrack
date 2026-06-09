package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery6Bar
import androidx.compose.material.icons.outlined.Battery6Bar
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ViewInAr
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun StatusPanel(
    batteryLevel: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = RayTracColors.Surface
        ),
        border = BorderStroke(
            1.dp,
            RayTracColors.Border
        )
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF071521).copy(alpha = 0.75f),
                            Color(0xFF000000).copy(alpha = 0.30f),                        )
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 12.dp
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                StatusItem(
                    icon = Icons.Outlined.LocationOn,
                    title = "GPS",
                    value = "ACTIVO"
                )

                VerticalDivider(
                    modifier = Modifier.height(40.dp),
                    color = RayTracColors.PrimaryGlow.copy(alpha = 0.18f),
                    thickness = 1.dp
                )

                StatusItem(
                    icon = Icons.Outlined.ViewInAr,
                    title = "AR",
                    value = "LISTO"
                )

                VerticalDivider(
                    modifier = Modifier.height(40.dp),
                    color = RayTracColors.PrimaryGlow.copy(alpha = 0.18f),
                    thickness = 1.dp
                )

                BatteryStatusItem(
                    batteryLevel = batteryLevel
                )
            }
        }
    }
}

@Preview
@Composable
fun StatusPanelPreview() {
    StatusPanel(100)
}
