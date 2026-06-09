package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun NewDestinationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(
            1.dp,
            RayTracColors.PrimaryGlow
        )
    ) {
        Icon(
            imageVector = Icons.Default.MyLocation,
            contentDescription = null,
            tint = RayTracColors.PrimaryGlow
        )

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = "NUEVO DESTINO",
            color = RayTracColors.PrimaryGlow,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun NewDestinationButtonPreview() {
    NewDestinationButton(
        onClick = { }
    )
}
