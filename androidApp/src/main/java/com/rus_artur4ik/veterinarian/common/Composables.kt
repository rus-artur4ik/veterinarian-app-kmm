package com.rus_artur4ik.veterinarian.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ColumnWithHeaderTemplate(
    title: String,
    content: @Composable () -> Unit
) {
    val scrollableState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = Dp(24f))
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Image(
                painter = ColorPainter(Color.Gray),
                contentDescription = null,
                modifier = Modifier
                    .size(Dp(36f))
                    .align(Alignment.CenterEnd)
            )
        }

        Column(modifier = Modifier.verticalScroll(scrollableState)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ColumnWithHeaderTemplatePreview() {
    ColumnWithHeaderTemplate(title = "Title title title") { }
}

@Composable
fun BigCarousel(itemsCount: Int, content: @Composable () -> Unit) {
    val scrollState = rememberScrollState()

    BoxWithConstraints {
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            repeat(itemsCount) { index ->
                Layout(
                    content = {
                        content()
                    },
                    measurePolicy = { measurables, constraints ->
                        // I'm assuming you'll declaring just one root
                        // composable in the content function above
                        // so it's measuring just the Box
                        val placeable = measurables.first().measure(constraints)
                        // maxWidth is from the BoxWithConstraints
                        val maxWidthInPx = this@BoxWithConstraints.maxWidth.roundToPx()
                        // Box width
                        val itemWidth = placeable.width
                        // Calculating the space for the first and last item
                        val startSpace =
                            if (index == 0) (maxWidthInPx - itemWidth) / 2 else 0
                        val endSpace =
                            if (index == itemsCount-1) (maxWidthInPx - itemWidth) / 2 else 0
                        // The width of the box + extra space
                        val width = startSpace + placeable.width + endSpace
                        layout(width, placeable.height) {
                            // Placing the Box in the right X position
                            val x = if (index == 0) startSpace else 0
                            placeable.place(x, 0)
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BigCarouselPreview() {
    BigCarousel(5) {
        Box(
            Modifier
                .size(200.dp)
                .padding(8.dp)
                .background(Color.Gray)
        ) {
            Text(text = "item", Modifier.align(Alignment.Center))
        }
    }
}