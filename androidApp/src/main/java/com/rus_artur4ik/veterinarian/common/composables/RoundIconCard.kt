package com.rus_artur4ik.veterinarian.common.composables

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R

@Composable
fun RoundIconCard(
    modifier: Modifier = Modifier,
    leftTitle: String = "",
    leftTitleStyle: TextStyle = MaterialTheme.typography.titleSmall,
    leftTitleColor: Color = MaterialTheme.colorScheme.onSurface,
    leftSubtitle: String = "",
    leftSubtitleStyle: TextStyle = MaterialTheme.typography.bodySmall,
    leftSubtitleColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    rightTitle: String = "",
    rightSubtitle: String = "",
    icon: @Composable () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            icon()

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = leftTitle,
                    style = leftTitleStyle,
                    color = leftTitleColor,
                )

                Text(
                    text = leftSubtitle,
                    style = leftSubtitleStyle,
                    color = leftSubtitleColor,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = rightTitle,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Text(
                    text = rightSubtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    RoundIconCard(
        leftTitle = "left title",
        leftSubtitle = "left Subtitle",
        rightTitle = "right title",
        rightSubtitle = "right subtitle"
    ) {
        Image(painter = painterResource(id = R.drawable.pet1), null)
    }
}