package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors
import com.raytrack.ui.theme.RayTrackDimensions.SearchBarShape
import com.raytrack.ui.theme.RayTrackDimensions.SearchBarTopPadding

@Composable
internal fun MapSearchBar(
    query: String,
    showResults: Boolean,
    useSystemKeyboard: Boolean,
    onKeyboardToggle: () -> Unit,
    onKeyboardHide: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onToggleResults: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource, useSystemKeyboard) {
        if (!useSystemKeyboard) {
            interactionSource.interactions.collect { interaction ->
                if (interaction is PressInteraction.Press) {
                    onKeyboardToggle()
                }
            }
        }
    }

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = SearchBarTopPadding)
            .onFocusChanged { focusState ->
                if (!focusState.isFocused && !useSystemKeyboard) {
                    onKeyboardHide()
                }
            },
        readOnly = !useSystemKeyboard,
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp),
        placeholder = {
            Text(
                text = "Buscar lugares",
                fontSize = 18.sp,
                color = RayTracColors.TextSecondary
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = RayTracColors.PrimaryGlow
            )
        },
        trailingIcon = {
            IconButton(onClick = onToggleResults) {
                AnimatedContent(
                    targetState = showResults,
                    transitionSpec = {
                        (fadeIn() + scaleIn(initialScale = 0.7f))
                            .togetherWith(fadeOut() + scaleOut(targetScale = 0.7f))
                    },
                    label = "searchModeTransition"
                ) { currentShowResults ->
                    val icon = if (currentShowResults) Icons.AutoMirrored.Filled.ViewList else Icons.Default.Map
                    val description = if (currentShowResults) "Ocultar resultados" else "Mostrar resultados"

                    Icon(
                        imageVector = icon,
                        contentDescription = description,
                        tint = RayTracColors.PrimaryGlow
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(query) }),
        interactionSource = interactionSource,
        shape = SearchBarShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = RayTracColors.PrimaryGlow,
            unfocusedBorderColor = RayTracColors.Border,
            focusedContainerColor = RayTracColors.Surface,
            unfocusedContainerColor = RayTracColors.Surface,
            focusedTextColor = RayTracColors.TextPrimary,
            unfocusedTextColor = RayTracColors.TextPrimary
        )
    )
}

@Preview
@Composable
private fun MapSearchBarPreview() {
    MapSearchBar(
        query = "",
        useSystemKeyboard = false,
        showResults = true,
        onKeyboardHide = {},
        onKeyboardToggle = {},
        onQueryChange = {},
        onSearch = {},
        onToggleResults = {}
    )
}
