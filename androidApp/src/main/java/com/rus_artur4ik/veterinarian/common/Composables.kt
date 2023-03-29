package com.rus_artur4ik.veterinarian.common

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rus_artur4ik.veterinarian.R

@Composable
fun KeyValueTab(
    key: String,
    value: String
) {
    Row {
        Text(text = "$key: ")
        Text(text = value, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
private fun KeyValueTabPreview() {
    KeyValueTab("Key", "Value")
}

@Composable
fun <T> Carousel(items: List<T>, content: @Composable (T) -> Unit) {
    val scrollState = rememberScrollState()

    BoxWithConstraints {
        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            val itemsCount = items.size

            repeat(itemsCount) { index ->       //TODO replace repeat to lazyRow
                content(items[index])
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CarouselPreview() {
    Carousel(listOf(1,2,3,4,5)) {
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

@Composable
@Preview(showBackground = true)
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorIndicator(t: Throwable) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.error, t.localizedMessage ?: ""),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ErrorPreview() {
    Error(Throwable("Internal server error"))
}