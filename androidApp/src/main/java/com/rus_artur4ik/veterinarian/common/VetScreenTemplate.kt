package com.rus_artur4ik.veterinarian.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rus_artur4ik.veterinarian.R

@Composable
fun VetScreenTemplate(
    name: String = "Наталья",
    navController: NavController?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            name = name
        )

        Box(
            modifier = modifier.weight(1f)
        ) {
            content()
        }

        BottomNavBar(
            navController = navController
        )
    }
}

@Composable
private fun TopBar(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Image(
            painter = ColorPainter(Color.Gray),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .align(CenterVertically)
        )

        Text(
            text = stringResource(id = R.string.top_bar_title, name),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
                .align(CenterVertically)
                .weight(1f)
        )

        Image(
            imageVector = Icons.Rounded.Notifications,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(CenterVertically)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun VetScreenTemplatePreview() {
    VetScreenTemplate(name = "Артур", navController = null) {
    }
}