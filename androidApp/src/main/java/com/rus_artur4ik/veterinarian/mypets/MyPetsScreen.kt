package com.rus_artur4ik.veterinarian.mypets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.mvvm.BaseEmptyableScreen
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity

class MyPetsScreen : BaseEmptyableScreen<MyPetsScreenState, MyPetsViewModel>(
    MyPetsViewModel::class.java
) {

    @Composable
    override fun Wrapper(viewModel: () -> MyPetsViewModel, content: @Composable () -> Unit) {
        super.Wrapper(viewModel) {
            ShowAllModeWrapper(viewModel = viewModel()) {
                content()
            }
        }
    }

    @Composable
    override fun NotEmpty(content: MyPetsScreenState, viewModel: MyPetsViewModel) {
        List(content, rememberLazyListState(), viewModel)
    }

    @Composable
    override fun Empty(content: MyPetsScreenState, viewModel: MyPetsViewModel) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = stringResource(id = R.string.you_have_no_pets),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Composable
    private fun List(
        content: MyPetsScreenState,
        listState: LazyListState,
        viewModel: MyPetsViewModel,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(
            state = listState,
            horizontalAlignment = CenterHorizontally,
            modifier = modifier
        ) {

            items(count = content.items.size) { index ->
                val item = content.items[index]
                PetCard(
                    item = item,
                    modifier = Modifier
                        .clickable { viewModel.openPetInfo(item) }
                )
            }
        }
    }

    @Composable
    private fun ShowAllModeWrapper(
        viewModel: MyPetsViewModel,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Column(Modifier.fillMaxSize()) {

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.pets),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(vertical = 22.dp, horizontal = 16.dp)
                        .align(CenterStart)
                )

                Image(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .align(CenterEnd)
                        .padding(end = 28.dp)
                        .size(24.dp)
                        .clickable {
                            viewModel.goToSearchMode()
                        }
                )
            }

            content()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun ShowAllPreview() {
        NotEmpty(
            MyPetsScreenState(listOf(PetEntity.generate())),
            MyPetsViewModel()
        )
    }

    @Preview
    @Composable
    private fun PetItemPreview() {
        PetCard(
            PetEntity.generate()
        )
    }
}