package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery6Bar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun StatusItem(
    icon: ImageVector,
    title: String,
    value: String
) {
        Column {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = RayTracColors.PrimaryGlow,
                    modifier = Modifier.size(32.dp)
                )

                Column {
                    Text(
                        modifier = Modifier.padding(
                            start = 4.dp),
                        text = title,
                        color = RayTracColors.TextSecondary,
                        fontSize = 11.sp
                    )

                    Text(
                        text = value,
                        color = RayTracColors.PrimaryGlow,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(
                            start = 4.dp,
                        )
                    )
                }

            }

        }
    }

@Preview
@Composable
fun StatusItemPreview() {
    StatusItem(
        icon = Icons.Default.Battery6Bar,
        title = "BATERÍA",
        value = "89%"
    )
}
