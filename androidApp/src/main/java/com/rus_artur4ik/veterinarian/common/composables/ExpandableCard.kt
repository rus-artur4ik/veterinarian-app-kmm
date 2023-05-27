package com.rus_artur4ik.veterinarian.common.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R

const val EXPAND_ANIMATION_DURATION = 500
const val ALPHA_DELAY = 300

@Composable
fun ExpandableCard(
    title: String,
    description: String,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit
) {
    val transitionState = remember {
        MutableTransitionState(isExpanded)
    }
    transitionState.targetState = isExpanded

    val transition = updateTransition(transitionState, label = "transition")

    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "arrow rotation") {
        if (it) 180f else 0f
    }

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onToggleExpand() }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(arrowRotationDegree)
                )
            }

            ExpandableContent(
                isExpanded = isExpanded,
                description = description
            )
        }
    }
}

@Composable
private fun ExpandableContent(
    isExpanded: Boolean = true,
    description: String
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPAND_ANIMATION_DURATION)
        ) + fadeIn(
            initialAlpha = 0f,
            animationSpec = tween(
                durationMillis = EXPAND_ANIMATION_DURATION,
                delayMillis = ALPHA_DELAY
            )
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPAND_ANIMATION_DURATION)
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = EXPAND_ANIMATION_DURATION,
                delayMillis = ALPHA_DELAY
            )
        )
    }
    AnimatedVisibility(
        visible = isExpanded,
        enter = enterTransition,
        exit = exitTransition,
        label = "expanding"
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
@Preview
private fun Collapsed() {
    ExpandableCard(title = "Title", description = "Description", isExpanded = false) {}
}

@Composable
@Preview
private fun Expanded() {
    ExpandableCard(title = "Title", description = "Description", isExpanded = true) {}
}