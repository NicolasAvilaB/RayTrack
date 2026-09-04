package com.raytrack.ui.screens.mapscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.theme.RayTracColors

@Composable
internal fun DestinationBottomPanel(
    visible: Boolean,
    title: String,
    address: String,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onStartAr: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = spring(
                dampingRatio = 0.75f,
                stiffness = 350f
            )
        ) + scaleIn(
            initialScale = 0.92f,
            animationSpec = spring(
                dampingRatio = 0.70f,
                stiffness = 400f
            )
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = RayTracColors.Border
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(18.dp)
        ) {

            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "DESTINO SELECCIONADO",
                color = RayTracColors.PrimaryGlow,
                fontSize = 14.sp,
                letterSpacing = 2.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .align(Alignment.Top)
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = RayTracColors.PrimaryGlow
                            ),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = RayTracColors.TextPrimary
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = title,
                        color = RayTracColors.TextPrimary,
                        fontSize = 20.sp
                    )

                    Text(
                        modifier = Modifier.padding(bottom = 20.dp),
                        text = address,
                        color = RayTracColors.TextSecondary,
                        fontSize = 18.sp,
                        lineHeight = 26.sp
                    )
                }

                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Top)
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = RayTracColors.Border
                            ),
                            shape = RoundedCornerShape(18.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = if (isFavorite) {
                            RayTracColors.PrimaryGlow
                        } else {
                            RayTracColors.PrimaryWhite
                        }
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 24.dp
                    )
                    .padding(
                        horizontal = 2.dp
                    ),
                thickness = 1.dp,
                color = RayTracColors.Border
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(82.dp)
                        .border(
                            border = BorderStroke(
                                width = 1.dp,
                                color = RayTracColors.Border
                            ),
                            shape = RoundedCornerShape(14.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = { }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = RayTracColors.PrimaryGlow
                        )

                        Text(
                            modifier = Modifier.padding(start = 7.dp),
                            text = "GUARDAR",
                            color = RayTracColors.PrimaryGlow,
                            fontSize = 16.sp,
                            letterSpacing = 1.sp
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(76.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF00AFCB),
                                    Color(0xFF18DFFF)
                                )
                            ),
                            shape = RoundedCornerShape(14.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = onStartAr
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = RayTracColors.TextThirdary
                        )

                        Text(
                            modifier = Modifier.padding(start = 7.dp),
                            text = "INICIAR AR",
                            color = RayTracColors.TextThirdary,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            letterSpacing = 1.sp
                        )

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DestinationBottomPanelPreview() {
    DestinationBottomPanel(
        visible = true,
        modifier = Modifier,
        title = "Costanera Center",
        address = "Av. Andrés Bello 2425\nProvidencia, Santiago",
        isFavorite = false,
        onToggleFavorite = {},
        onStartAr = {}
    )
}
