package com.raytrack.ui.screens.keyboardscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun RayTracKeyboard(
    modifier: Modifier = Modifier,
    showKeyboard: Boolean,
    onCharacter: (String) -> Unit,
    onBackspace: () -> Unit,
    onSpace: () -> Unit,
    onSearch: () -> Unit
) {
    if (!showKeyboard) return
    var uppercase by remember { mutableStateOf(false) }
    var symbolsMode by remember { mutableStateOf(false) }

    val rows = if (symbolsMode) {
        listOf(
            listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
            listOf("@", "#", "$", "%", "&", "-", "+", "(", ")", "/"),
            listOf("*", "\"", "'", ":", ";", "!", "?", ",", ".")
        )
    } else {
        listOf(
            listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            listOf("A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ"),
            listOf("Z", "X", "C", "V", "B", "N", "M")
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))
            .background(RayTracColors.Surface.copy(alpha = 0.7f))
            .border(
                width = 1.dp,
                color = RayTracColors.PrimaryGlow.copy(alpha = 0.3f),
                shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp)
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
                        label = "⇧",
                        modifier = Modifier.weight(1.25f),
                        highlighted = uppercase,
                        onClick = {
                            uppercase = !uppercase
                        }
                    )
                }
                row.forEach { letter ->
                    val character = if (symbolsMode || uppercase) {
                        letter
                    } else {
                        letter.lowercase()
                    }

                    KeyboardKey(
                        label = character,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onCharacter(character)
                        }
                    )
                }

                if (index == 2) {
                    KeyboardKey(
                        label = "⌫",
                        modifier = Modifier.weight(1.25f),
                        onClick = onBackspace
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            KeyboardKey(
                label = if (symbolsMode) "ABC" else "?123",
                modifier = Modifier.weight(1.2f),
                highlighted = symbolsMode,
                onClick = {
                    symbolsMode = !symbolsMode
                }
            )
            KeyboardKey(
                label = "Espacio",
                modifier = Modifier.weight(4f),
                onClick = onSpace

            )
            KeyboardKey(
                label = "⌕",
                modifier = Modifier.weight(1.4f),
                highlighted = true,
                onClick = onSearch
            )
        }
    }
}


@Composable
private fun KeyboardKey(
    label: String,
    modifier: Modifier = Modifier,
    highlighted: Boolean = false,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val shape = RoundedCornerShape(9.dp)

    Box(
        modifier = modifier
            .height(46.dp)
            .clip(shape)
            .background(
                if (pressed || highlighted) {
                    RayTracColors.GlassBlue
                } else {
                    RayTracColors.DestinationCardDark
                }
            )
            .border(
                width = if (pressed) 1.5.dp else 1.dp,
                color = RayTracColors.PrimaryGlow.copy(
                    alpha = if (pressed) 1f
                    else if (highlighted) 0.65f
                    else 0.20f
                ),
                shape = shape
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        val released = try {
                            tryAwaitRelease()
                        } finally {
                            pressed = false
                        }

                        if (released) onClick()
                    }
                )
            }
            .padding(horizontal = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        if (pressed) {
            Popup(
                alignment = Alignment.TopCenter,
                offset = IntOffset(0, -170),
                properties = PopupProperties(
                    focusable = false,
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .border(
                            1.5.dp,
                            RayTracColors.PrimaryGlow.copy(alpha = 0.8f),
                            RoundedCornerShape(12.dp)
                        )
                        .background(
                            RayTracColors.GlassBlue.copy(alpha = 0.5f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        color = RayTracColors.SecondaryGlow,
                        fontSize = 29.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Text(
            text = label,
            color = if (pressed || highlighted) {
                RayTracColors.SecondaryGlow
            } else {
                RayTracColors.TextPrimary
            },
            fontSize = if (label == "Espacio") 12.sp else 17.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Preview
@Composable
internal fun PreviewKeyboard() {
    var inputText by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
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

        RayTracKeyboard(
            showKeyboard = true,
            onCharacter = { character ->
                inputText += character
            },
            onBackspace = {
                if (inputText.isNotEmpty()) {
                    inputText = inputText.dropLast(1)
                }
            },
            onSpace = {
                inputText += " "
            },
            onSearch = {
                inputText = ""
            }
        )
    }
}
