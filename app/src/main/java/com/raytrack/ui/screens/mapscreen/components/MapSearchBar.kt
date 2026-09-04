package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.ViewList
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
    showResults: Boolean,
    onQueryChange: (String) -> Unit,
    onToggleResults: () -> Unit,
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
        trailingIcon = {
            IconButton(
                onClick = onToggleResults
            ) {
                AnimatedContent(
                    targetState = showResults,
                    transitionSpec = {
                        (fadeIn() + scaleIn(initialScale = 0.7f))
                            .togetherWith(
                                fadeOut() + scaleOut(targetScale = 0.7f)
                            )
                    },
                    label = "searchModeTransition"
                ) { currentShowResults ->
                    Icon(
                        imageVector = if (currentShowResults) {
                            Icons.AutoMirrored.Filled.ViewList
                        } else {
                            Icons.Default.Map
                        },
                        contentDescription = if (currentShowResults) {
                            "Ocultar resultados"
                        } else {
                            "Mostrar resultados"
                        },
                        tint = RayTracColors.PrimaryGlow
                    )
                }
            }
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
        showResults = true,
        onToggleResults = {}
    )
}