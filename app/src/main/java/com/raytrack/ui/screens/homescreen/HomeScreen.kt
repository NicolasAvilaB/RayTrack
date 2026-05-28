package com.raytrack.ui.screens.homescreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raytrack.ui.screens.homescreen.model.FuturisticMotion
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    onSearch: (String) -> Unit,
    onSelectRoute: (String) -> Unit
) {

    val query = remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF050B10))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "HOME",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Icon(
                        Icons.Default.MyLocation,
                        contentDescription = null,
                        tint = Color(0xFF7CF7D4)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        OutlinedTextField(
                            value = query.value,
                            onValueChange = { query.value = it },
                            singleLine = true,
                            placeholder = {
                                Text(
                                    "Buscar destino...",
                                    fontSize = 18.sp,
                                    color = Color.White.copy(alpha = 0.4f)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = Color.White.copy(alpha = 0.5f)
                                )
                            },
                            trailingIcon = {
                                AnimatedContent(
                                    targetState = query.value.isNotEmpty(),
                                    transitionSpec = {
                                        (fadeIn(tween(20)) + scaleIn(
                                            initialScale = 0.5f,
                                            transformOrigin = TransformOrigin.Center
                                        )) togetherWith
                                                (fadeOut(tween(20)) + scaleOut(
                                                    targetScale = 0.5f,
                                                    transformOrigin = TransformOrigin.Center
                                                ))
                                    },
                                    label = "MorphIcon"
                                ) { hasText ->

                                    if (hasText) {
                                        IconButton(onClick = { query.value = "" }) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Limpiar",
                                                tint = Color(0xFF7CF7D4)
                                            )
                                        }
                                    } else {
                                        Icon(
                                            imageVector = Icons.Default.Tune,
                                            contentDescription = null,
                                            tint = Color(0xFF7CF7D4).copy(alpha = 0.5f)
                                        )
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 10.dp,
                                    shape = RoundedCornerShape(100),
                                    ambientColor = Color(0xFF7CF7D4),
                                    spotColor = Color(0xFF7CF7D4)
                                ),
                            textStyle = TextStyle(
                                fontSize = 18.sp
                            ),
                            shape = RoundedCornerShape(100),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF7CF7D4),
                                unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                                cursorColor = Color(0xFF7CF7D4),
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedContainerColor = Color(0xFF101010),
                                unfocusedContainerColor = Color(0xFF101010)
                            )
                        )
                    }


                Spacer(modifier = Modifier.height(24.dp))

                SectionHeader("FAVORITOS")

                listOf(
                    "Casa" to "Los Leones 123",
                    "Oficina" to "Apoquindo 4501",
                    "Mirador" to "Farellones"
                ).forEachIndexed { i, item ->
                    FuturisticRouteItem(
                        index = i,
                        title = item.first,
                        subtitle = item.second,
                        onClick = { onSelectRoute(item.first) }
                    )
                }

                Spacer(Modifier.height(16.dp))

                SectionHeader("RECIENTES")

                listOf(
                    "Parque Bicentenario" to "Vitacura",
                    "Costanera Center" to "Providencia"
                ).forEachIndexed { i, item ->
                    FuturisticRouteItem(
                        index = i,
                        title = item.first,
                        subtitle = item.second,
                        onClick = { onSelectRoute(item.first) }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF00FFC3).copy(alpha = 0.3f),
                                    Color.Transparent
                                )
                            ),
                            shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onSearch(query.value) },
                    shape = RoundedCornerShape(35),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        )
                        .height(53.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, Color(0xFF00FFC3))
                ) {
                    Text(
                        text = "NUEVO DESTINO",
                        fontSize = 16.sp,
                        color = Color(0xFF7CF7D4)
                    )
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        color = Color(0xFF7CF7D4),
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun FuturisticRouteItem(
    index: Int,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {

    var active by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(index * 100L)
        active = true
    }

    val transition = updateTransition(active, label = "node")

    val scale by transition.animateFloat(
        transitionSpec = { FuturisticMotion.enterFloatSpring },
        label = "scale"
    ) { if (it) 1f else 0.92f }

    val alpha by transition.animateFloat(
        transitionSpec = { FuturisticMotion.fadeFloat },
        label = "alpha"
    ) { if (it) 1f else 0f }

    val lift by transition.animateDp(
        transitionSpec = { FuturisticMotion.enterDpSpring },
        label = "lift"
    ) { if (it) 0.dp else 16.dp }

    val glow by transition.animateFloat(
        transitionSpec = { FuturisticMotion.fadeFloat },
        label = "glow"
    ) { if (it) 0.18f else 0.05f }

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.04f)
        ),
        border = BorderStroke(
            1.dp,
            Color(0xFF7CF7D4).copy(alpha = glow)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
                translationY = lift.toPx()
            }
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = subtitle,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.55f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onSearch = {

        },
        onSelectRoute = {

        }
    )
}
