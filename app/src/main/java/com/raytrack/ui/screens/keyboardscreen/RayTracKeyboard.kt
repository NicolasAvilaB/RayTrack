package com.raytrack.ui.screens.keyboardscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors
import com.raytrack.ui.theme.RayTrackDimensions.KeyShape
import com.raytrack.ui.theme.RayTrackDimensions.KeyboardTopShape
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.milliseconds

@Composable
internal fun RayTracKeyboard(
    modifier: Modifier = Modifier,
    showKeyboard: Boolean,
    onCharacter: (String) -> Unit,
    onBackspace: () -> Unit,
    onSpace: () -> Unit,
    onSearch: () -> Unit
) {
    val language = rememberSystemLanguage()
    val useSystemKeyboard = language == "ja"

    if (!showKeyboard || useSystemKeyboard) return

    var uppercase by remember { mutableStateOf(false) }
    var symbolsMode by remember { mutableStateOf(false) }
    var activeKeyPreview by remember { mutableStateOf<String?>(null) }

    val rows = remember(symbolsMode, language) {
        if (symbolsMode) {
            listOf(
                listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
                listOf("@", "#", "$", "%", "&", "-", "+", "(", ")", "/"),
                listOf("*", "\"", "'", ":", ";", "!", "?", ",", ".")
            )
        } else {
            getKeyboardLanguagesLettersRows(language)
        }
    }

    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(KeyboardTopShape)
                .background(RayTracColors.Surface.copy(alpha = 0.7f))
                .border(
                    width = 1.dp,
                    color = RayTracColors.PrimaryGlow.copy(alpha = 0.3f),
                    shape = KeyboardTopShape
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { }
                )
                .padding(horizontal = 7.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            rows.forEachIndexed { index, row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    if (index == 2 && !symbolsMode) {
                        KeyboardKey(
                            icon = Icons.Filled.ArrowUpward,
                            contentDescription = "Mayúsculas",
                            modifier = Modifier.weight(1.25f),
                            highlighted = uppercase,
                            onPressChanged = { pressed ->
                                activeKeyPreview = if (pressed) "⇧" else null
                            },
                            onClick = { uppercase = !uppercase }
                        )
                    }

                    row.forEach { letter ->
                        val character = if (symbolsMode || uppercase) letter else letter.lowercase()

                        KeyboardKey(
                            label = character,
                            contentDescription = character,
                            modifier = Modifier.weight(1f),
                            onPressChanged = { pressed ->
                                activeKeyPreview = if (pressed) character else null
                            },
                            onClick = { onCharacter(character) }
                        )
                    }

                    if (index == 2) {
                        BackspaceKey(
                            modifier = Modifier.weight(1.25f),
                            onPressChanged = { pressed ->
                                activeKeyPreview = if (pressed) "⌫" else null
                            },
                            onBackspace = onBackspace
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                val toggleLabel = if (symbolsMode) "ABC" else "?123"
                KeyboardKey(
                    label = toggleLabel,
                    contentDescription = toggleLabel,
                    modifier = Modifier.weight(1.2f),
                    highlighted = symbolsMode,
                    onPressChanged = { pressed ->
                        activeKeyPreview = if (pressed) toggleLabel else null
                    },
                    onClick = { symbolsMode = !symbolsMode }
                )
                KeyboardKey(
                    label = "Espacio",
                    contentDescription = "Espacio",
                    modifier = Modifier.weight(4f),
                    onPressChanged = { pressed ->
                        activeKeyPreview = if (pressed) "Espacio" else null
                    },
                    onClick = onSpace
                )
                KeyboardKey(
                    icon = Icons.Default.Search,
                    contentDescription = "Buscar",
                    modifier = Modifier.weight(1.4f),
                    highlighted = true,
                    onPressChanged = { pressed ->
                        activeKeyPreview = if (pressed) "⌕" else null
                    },
                    onClick = onSearch
                )
            }
        }

        if (activeKeyPreview != null && activeKeyPreview !in listOf("⇧", "?123", "ABC")) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp)
                    .border(
                        1.5.dp,
                        RayTracColors.PrimaryGlow.copy(alpha = 0.9f),
                        RoundedCornerShape(12.dp)
                    )
                    .background(
                        RayTracColors.GlassBlue.copy(alpha = 0.6f),
                        RoundedCornerShape(15.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = activeKeyPreview ?: "",
                    color = RayTracColors.SecondaryGlow,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun KeyboardKey(
    modifier: Modifier = Modifier,
    label: String = "",
    contentDescription: String? = null,
    icon: ImageVector? = null,
    highlighted: Boolean = false,
    onPressChanged: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
        onPressChanged(isPressed)
    }

    val isHighlightedOrPressed = isPressed || highlighted

    Box(
        modifier = modifier
            .height(60.dp)
            .clip(KeyShape)
            .background(if (isHighlightedOrPressed) RayTracColors.GlassBlue else RayTracColors.DestinationCardDark)
            .border(
                width = if (isPressed) 1.5.dp else 1.dp,
                color = RayTracColors.PrimaryGlow.copy(alpha = if (isHighlightedOrPressed) 0.8f else 0.20f),
                shape = KeyShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = if (isHighlightedOrPressed) RayTracColors.SecondaryGlow else RayTracColors.TextPrimary
            )
        } else {
            Text(
                text = label,
                color = if (isHighlightedOrPressed) RayTracColors.SecondaryGlow else RayTracColors.TextPrimary,
                fontSize = if (label == "Espacio") 14.sp else 17.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun BackspaceKey(
    modifier: Modifier = Modifier,
    onPressChanged: (Boolean) -> Unit,
    onBackspace: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
        onPressChanged(isPressed)
        if (isPressed) {
            onBackspace()
            delay(200.milliseconds)
            while (isActive) {
                onBackspace()
                delay(10.milliseconds)
            }
        }
    }

    Box(
        modifier = modifier
            .height(60.dp)
            .clip(KeyShape)
            .background(if (isPressed) RayTracColors.GlassBlue else RayTracColors.Surface)
            .border(width = 1.dp, color = RayTracColors.Border, shape = KeyShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {}
            )
            .padding(horizontal = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Backspace,
            contentDescription = "Borrar",
            tint = if (isPressed) RayTracColors.SecondaryGlow else RayTracColors.TextPrimary
        )
    }
}

@Preview
@Composable
internal fun PreviewKeyboard() {
    var inputText by remember { mutableStateOf("") }
    RayTracKeyboard(
        showKeyboard = true,
        onCharacter = { character -> inputText += character },
        onBackspace = {
            if (inputText.isNotEmpty()) {
                inputText = inputText.dropLast(1)
            }
        },
        onSpace = { inputText += " " },
        onSearch = { inputText = "" }
    )
}
