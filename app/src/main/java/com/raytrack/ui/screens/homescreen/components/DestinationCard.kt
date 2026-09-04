package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.screens.homescreen.model.DestinationItem
import com.raytrack.ui.theme.RayTracColors

@Composable
fun DestinationCard(
    modifier: Modifier = Modifier,
    destination: DestinationItem,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = RayTracColors.DestinationCardDark.copy(alpha = 0.5f)
        ),
        border = BorderStroke(
            1.dp,
            RayTracColors.Border
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        RayTracColors.PrimaryGlow.copy(alpha = 0.08f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    modifier = Modifier.size(28.dp),
                    imageVector = destination.icon,
                    contentDescription = null,
                    tint = RayTracColors.PrimaryGlow
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {

                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = destination.title,
                    color = RayTracColors.TextPrimary,
                    fontSize = 18.sp
                )

                Text(
                    text = destination.subtitle,
                    color = RayTracColors.TextSecondary,
                    fontSize = 14.sp
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = RayTracColors.TextSecondary
            )
        }
    }
}

@Preview
@Composable
fun DestinationCardPreview(){
    val favorites = listOf(
        DestinationItem(
            "Casa",
            "Los Leones 123",
            Icons.Default.Home
        ),
        DestinationItem(
            "Oficina",
            "Apoquindo 4501",
            Icons.Default.Work
        ),
        DestinationItem(
            "Mirador",
            "Farellones",
            Icons.Default.Landscape
        )
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        favorites.forEach {
            DestinationCard(

                destination = it,
                onClick = { }
            )
        }
    }
}
