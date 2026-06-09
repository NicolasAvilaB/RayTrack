package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier.padding(end = 8.dp),
            color = RayTracColors.PrimaryGlow,
            fontSize = 14.sp
        )

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(1.dp)
                .background(
                    RayTracColors.PrimaryGlow.copy(alpha = 0.4f)
                )
        )
    }
}

@Preview
@Composable
fun SectionHeaderPreview() {
    SectionHeader(title = "Favoritos")
}
