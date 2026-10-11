package com.raytrack.ui.screens.homescreen.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.screens.homescreen.model.SearchFilter
import com.raytrack.ui.screens.homescreen.model.toFilter
import com.raytrack.ui.screens.homescreen.model.toIconFilter
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun RayTracSearchBar(
    query: String,
    filter: SearchFilter,
    useSystemKeyboard: Boolean,
    onKeyboardToggle: () -> Unit,
    onKeyboardHide: () -> Unit,
    onQueryChange: (String) -> Unit,
    onFilterChange: (SearchFilter) -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    LaunchedEffect(interactionSource, useSystemKeyboard) {
        if (!useSystemKeyboard) {
            interactionSource.interactions.collect { interaction ->
                if (interaction is PressInteraction.Press) {
                    onKeyboardToggle()
                }
            }
        }
    }

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
            readOnly = !useSystemKeyboard,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && !useSystemKeyboard) {
                        onKeyboardHide()
                    }
                },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onKeyboardHide()
                }
            ),
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
                IconButton(
                    onClick = {
                        onFilterChange(filter.toFilter())
                    }
                ) {
                    AnimatedContent(
                        targetState = filter,
                        transitionSpec = {
                            (fadeIn() + scaleIn(initialScale = 0.7f))
                                .togetherWith(
                                    fadeOut() + scaleOut(targetScale = 0.7f)
                                )
                        },
                        label = "filterTransition"
                    ) { currentFilter ->

                        Icon(
                            imageVector = currentFilter.toIconFilter(),
                            contentDescription = null,
                            tint = RayTracColors.PrimaryGlow
                        )
                    }
                }
            },
            interactionSource = interactionSource,
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
    val filter = remember {
        mutableStateOf(SearchFilter.FAVORITES)
    }

    RayTracSearchBar(
        query = "",
        filter = filter.value,
        useSystemKeyboard = false,
        onKeyboardToggle = {},
        onKeyboardHide = {},
        onFilterChange = {
            filter.value = it
        },
        onQueryChange = { }
    )
}
