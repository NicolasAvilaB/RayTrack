package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun MapHeader(
    onNavBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clickable { onNavBack() }
                .border(
                    1.dp,
                    RayTracColors.Border,
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = RayTracColors.TextPrimary
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = "NUEVO DESTINO",
                color = RayTracColors.TextPrimary,
                fontSize = 16.sp,
                letterSpacing = 2.sp
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "SELECCIONA TU DESTINO",
                color = RayTracColors.PrimaryGlow,
                fontSize = 12.sp
            )
        }

        Box(
            modifier = Modifier
                .size(56.dp)
                .border(
                    1.dp,
                    RayTracColors.Border,
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.MyLocation,
                contentDescription = null,
                tint = RayTracColors.PrimaryGlow
            )
        }
    }
}

@Preview
@Composable
fun MapHeaderPreview() {
    MapHeader(onNavBack = { })
}
