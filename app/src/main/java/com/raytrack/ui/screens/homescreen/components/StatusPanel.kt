package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            StatusItem(
                icon = Icons.Outlined.LocationOn,
                "GPS",
                "ACTIVO"
            )

            VerticalDivider(
                modifier = Modifier.height(32.dp),
                color = Color(0xFF00D1FF).copy(alpha = 0.15f),
                thickness = 1.dp
            )

            StatusItem(
                icon = Icons.Outlined.ViewInAr,
                "AR",
                "LISTO"
            )

            VerticalDivider(
                modifier = Modifier.height(32.dp),
                color = Color(0xFF00D1FF).copy(alpha = 0.15f),
                thickness = 1.dp
            )

            StatusItem(
                icon = Icons.Outlined.Battery6Bar,
                "BATERÍA",
                "$batteryLevel%"
            )
        }
    }
}

@Preview
@Composable
fun StatusPanelPreview() {
    StatusPanel(100)
}
