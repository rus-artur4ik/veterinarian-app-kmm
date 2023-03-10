package com.rus_artur4ik.veterinarian.mypets

import androidx.compose.foundation.clickable
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
import com.rus_artur4ik.petcore.mvvm.MvvmScreen
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.VetCard
import com.rus_artur4ik.veterinarian.common.VetScreenTemplate
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.Sex

class MyPetsScreen : MvvmScreen<MyPetsScreenState, MyPetsViewModel>(
    MyPetsViewModel::class.java
) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(viewModel: MyPetsViewModel) {
        val lazyListState = rememberLazyListState()

        VetScreenTemplate(
            name = "Екатерина",
            navController = viewModel.navHostController
        ) {
            Column(Modifier.fillMaxSize()) {

                TextField(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth(),
                    value = viewModel.state.petNameFilter,
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
                    onValueChange = { viewModel.petNameFilterChanged(it) }
                )

                LazyColumn(
                    state = lazyListState,
                    contentPadding = PaddingValues(
                        dimensionResource(id = R.dimen.default_horizontal_padding)
                    ),
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {

                    items(count = viewModel.state.items.size) { index ->
                        val item = viewModel.state.items[index]
                        PetCard(
                            item = item,
                            modifier = Modifier
                                .clickable { viewModel.openPetInfo(item) }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun PetCard(item: PetEntity, modifier: Modifier = Modifier) {
        VetCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
        ) {
            Text(
                text = item.name,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    end = 16.dp,
                    bottom = 12.dp
                )
            )

            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            ) {
                Text(
                    text = "Вид:",
                    fontSize = 12.sp
                )

                Text(
                    text = item.kind,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        start = 16.dp
                    )
                )
            }

            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            ) {
                Text(
                    text = "Порода:",
                    fontSize = 12.sp,
                )

                Text(
                    text = item.breed,
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
        Content(MyPetsViewModel())
    }

    @Preview
    @Composable
    private fun PetItemPreview() {
        PetCard(
            PetEntity(
                name = "Кеша",
                breed = "Ориентальная кошка",
                sex = Sex.MALE,
                birthday = null,
                kind = "Кот",
                lastVisit = null
            )
        )
    }
}