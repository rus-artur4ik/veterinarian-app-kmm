package com.rus_artur4ik.veterinarian.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun HighlightedDescriptionCard(
    title: String,
    subtitle: String,
    iconPainter: Painter,
    modifier: Modifier = Modifier,
    cardBackgroundColor: Color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
    iconBackgroundColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    RoundIconCard(
        leftTitle = title,
        leftTitleStyle = MaterialTheme.typography.bodySmall,
        leftTitleColor = MaterialTheme.colorScheme.onSurfaceVariant,
        leftSubtitle = subtitle,
        leftSubtitleStyle = MaterialTheme.typography.titleMedium,
        leftSubtitleColor = MaterialTheme.colorScheme.secondary,
        cardBackgroundColor = cardBackgroundColor,
        modifier = modifier
    ) {
        RoundedBox(
            modifier = Modifier.size(40.dp),
            backgroundColor = iconBackgroundColor
        ) {
            Image(
                painter = iconPainter,
                contentDescription = null
            )
        }
    }
}