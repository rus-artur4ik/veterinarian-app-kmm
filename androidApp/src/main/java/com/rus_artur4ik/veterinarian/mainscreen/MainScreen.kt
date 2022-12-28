package com.rus_artur4ik.veterinarian.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.rus_artur4ik.veterinarian.common.BigCarousel
import com.rus_artur4ik.veterinarian.common.ColumnWithHeaderTemplate
import com.rus_artur4ik.veterinarian.common.mvvm.CoreScreen

class MainScreen : CoreScreen<MainScreenState, MainViewModel>(
    MainViewModel::class.java
) {

    @Composable
    override fun Content(viewModel: MainViewModel, navHostController: NavHostController?) {
        ColumnWithHeaderTemplate(title = "Здравствуйте, Екатерина!") {

            Text(
                text = "Мои питомцы",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = Dp(24f))
            )

            BigCarousel(itemsCount = 5) {
                Image(
                    painter = ColorPainter(Color.Gray),
                    contentDescription = null,
                    modifier = Modifier
                        .width(Dp(300f))
                        .height(Dp(200f))
                        .padding(horizontal = Dp(8f))
                )
            }

            Spacer(modifier = Modifier.height(Dp(24f)))

            Text(
                text = "Запись",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = Dp(24f))
            )

            Image(
                painter = ColorPainter(Color.Gray),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dp(24f))
                    .height(Dp(200f))
            )

            Spacer(modifier = Modifier.height(Dp(24f)))

            Text(
                text = "Последнее посещение",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = Dp(24f))
            )

            Image(
                painter = ColorPainter(Color.Gray),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dp(24f))
                    .height(Dp(200f))
            )
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content()
    }
}