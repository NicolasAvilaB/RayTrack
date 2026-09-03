package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
internal fun MapSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(54.dp),
        placeholder = {
            Text(
                fontSize = 18.sp,
                text = "Buscar lugares",
                color = RayTracColors.TextSecondary
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
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

@Preview
@Composable
private fun MapSearchBarPreview() {
    MapSearchBar(
        query = "",
        onQueryChange = {},
    )
}