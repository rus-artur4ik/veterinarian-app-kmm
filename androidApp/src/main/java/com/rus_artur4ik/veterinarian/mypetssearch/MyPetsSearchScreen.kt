package com.rus_artur4ik.veterinarian.mypetssearch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.petcore.mvvm.MvvmScreen
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.ErrorIndicator
import com.rus_artur4ik.veterinarian.common.composables.LoadingIndicator

class MyPetsSearchScreen : MvvmScreen<MyPetsSearchScreenState, MyPetsSearchViewModel>(
    MyPetsSearchViewModel::class.java
) {
    @Composable
    override fun Content(viewModel: MyPetsSearchViewModel) {
        val listState = rememberLazyListState()

        SearchModeWrapper(contentState = viewModel.state, viewModel = viewModel) {
            when (viewModel.state.state) {
                MyPetsSearchScreenState.State.LOADING -> LoadingIndicator()
                MyPetsSearchScreenState.State.CONTENT -> {
                    List(
                        content = viewModel.state,
                        listState = listState,
                        viewModel = viewModel
                    )
                }

                MyPetsSearchScreenState.State.ERROR -> ErrorIndicator(t = Exception("Ошибка"))
                MyPetsSearchScreenState.State.EMPTY -> Empty()
            }
        }
    }

    @Composable
    fun Empty() {
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.nothing_found),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }


    @Composable
    private fun SearchModeWrapper(
        contentState: MyPetsSearchScreenState,
        viewModel: MyPetsSearchViewModel,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Column(Modifier.fillMaxSize()) {

            Text(
                text = stringResource(id = R.string.back),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 28.dp)
                    .clickable {
                        viewModel.popBack()
                    }
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                value = contentState.petNameFilter,
                label = {
                    Text(
                        text = stringResource(id = R.string.search_pet)
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

            content()
        }
    }


    @Composable
    private fun List(
        content: MyPetsSearchScreenState,
        listState: LazyListState,
        viewModel: MyPetsSearchViewModel,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {

            items(count = content.items.size) { index ->
                val item = content.items[index]
                Text(
                    text = item.name,
                    modifier = Modifier
                        .clickable { viewModel.openPetInfo(item) }
                        .padding(16.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                if (index < content.items.lastIndex) {
                    Divider(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun SearchModePreview() {
        Content(
            MyPetsSearchViewModel()
        )
    }
}