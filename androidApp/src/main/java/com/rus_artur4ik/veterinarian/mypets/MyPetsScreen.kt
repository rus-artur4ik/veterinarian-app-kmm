package com.rus_artur4ik.veterinarian.mypets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.VetScreenTemplate
import com.rus_artur4ik.veterinarian.common.mvvm.CoreScreen

class MyPetsScreen : CoreScreen<NewWorkoutState, MyPetsViewModel>(
    MyPetsViewModel::class.java
) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(
        viewModel: MyPetsViewModel,
        navHostController: NavHostController?
    ) {
        val lazyListState = rememberLazyListState()

        VetScreenTemplate(
            name = "Екатерина",
            navController = navHostController
        ) {
            Column(Modifier.fillMaxSize()) {

                TextField(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth(),
                    value = viewModel.state.value.petNameFilter,
                    label = {
                        Text(
                            text = "Имя питомца"
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )
                    },
                    onValueChange = { viewModel.onWorkoutNameChanged(it) }
                )

                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValues(
                        dimensionResource(id = R.dimen.default_horizontal_padding)
                    ),
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {

                    items(count = viewModel.state.value.items.size) { index ->
                        val item = viewModel.state.value.items[index]
                        Pet(item)
                    }
                }
            }
        }
    }

    @Composable
    private fun Pet(item: PetItem) {
        Card(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = item.petName,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 12.dp
                )
            )

            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp
                )
            ) {
                Text(
                    text = "Вид:",
                    fontSize = 12.sp
                )

                Text(
                    text = item.petKind,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        start = 16.dp
                    )
                )
            }

            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp
                )
            ) {
                Text(
                    text = "Порода:",
                    fontSize = 12.sp,
                )

                Text(
                    text = item.petBreed,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        start = 16.dp
                    )
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        Content()
    }

    @Preview
    @Composable
    private fun PetItemPreview() {
        Pet(
            PetItem(
                petName = "Кеша",
                petKind = "Кот",
                petBreed = "Ориентальная кошка"
            )
        )
    }
}