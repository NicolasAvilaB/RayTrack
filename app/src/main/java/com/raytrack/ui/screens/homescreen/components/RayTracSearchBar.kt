package com.raytrack.ui.screens.homescreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors

@Composable
fun RayTracSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {

    Column {
        Text(
            modifier = Modifier.padding(
                top = 12.dp,
                bottom = 12.dp
            ),
            text = "DESTINO",
            color = RayTracColors.PrimaryGlow,
            fontSize = 14.sp
        )

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            placeholder = {
                Text(
                    fontSize = 18.sp,
                    text = "Buscar destino...",
                    color = RayTracColors.TextSecondary
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = RayTracColors.TextSecondary
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = null,
                    tint = RayTracColors.PrimaryGlow
                )
            },
            shape = RoundedCornerShape(18.dp),
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = RayTracColors.PrimaryGlow,
                unfocusedBorderColor = RayTracColors.Border,
                focusedContainerColor = RayTracColors.Surface,
                unfocusedContainerColor = RayTracColors.Surface,
                focusedTextColor = RayTracColors.TextPrimary,
                unfocusedTextColor = RayTracColors.TextPrimary
            ),
            singleLine = true
        )
    }
}

@Preview
@Composable
fun RayTracSearchBarPreview() {
    RayTracSearchBar(
        query = "",
        onQueryChange = { }
    )
}
