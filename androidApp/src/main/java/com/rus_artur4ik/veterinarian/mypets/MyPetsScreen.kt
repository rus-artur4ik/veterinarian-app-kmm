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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.composables.RoundIconCard
import com.rus_artur4ik.veterinarian.common.composables.RoundedBox
import com.rus_artur4ik.veterinarian.common.getIconRes
import com.rus_artur4ik.veterinarian.common.mvvm.BaseEmptyableScreen
import com.rus_artur4ik.veterinarian.domain.entity.BreedEntity
import com.rus_artur4ik.veterinarian.domain.entity.KindEntity
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.SexEntity
import com.rus_artur4ik.veterinarian.mypets.MyPetsScreenState.SearchMode
import com.rus_artur4ik.veterinarian.mypets.MyPetsScreenState.ShowAllMode
import kotlinx.datetime.toJavaLocalDateTime

class MyPetsScreen : BaseEmptyableScreen<MyPetsScreenState, MyPetsViewModel>(
    MyPetsViewModel::class.java
) {

    @Composable
    override fun NotEmpty(content: MyPetsScreenState, viewModel: MyPetsViewModel) {
        Wrap(contentState = content, viewModel = viewModel) {
            List(content, rememberLazyListState(), viewModel, Modifier.weight(1f))
        }
    }

    @Composable
    override fun Empty(content: MyPetsScreenState, viewModel: MyPetsViewModel) {
        Wrap(contentState = content, viewModel = viewModel) {
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
    }

    @Composable
    override fun Loading(viewModel: MyPetsViewModel) {
//        Wrap(contentState = , viewModel = viewModel) { TODO
//
//        }
    }

    @Composable
    override fun Error(throwable: Throwable, viewModel: MyPetsViewModel) {
//        Wrap {    //TODO
//
//        }
    }

    @Composable
    private fun Wrap(
        contentState: MyPetsScreenState,
        viewModel: MyPetsViewModel,
        content: @Composable ColumnScope.() -> Unit
    ) {
        when (contentState) {
            is SearchMode -> SearchModeWrapper(contentState, viewModel) {
                content()
            }
            is ShowAllMode -> ShowAllModeWrapper(viewModel) {
                content()
            }
        }
    }

    @Composable
    private fun List(
        content: MyPetsScreenState,
        listState: LazyListState,
        viewModel: MyPetsViewModel,
        modifier: Modifier = Modifier
    ) {
        val items = when (content) {
            is SearchMode -> content.items
            is ShowAllMode -> content.items
        }

        LazyColumn(
            state = listState,
            horizontalAlignment = CenterHorizontally,
            modifier = modifier
        ) {

            items(count = items.size) { index ->
                val item = items[index]
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

    @Composable
    private fun SearchModeWrapper(
        contentState: SearchMode,
        viewModel: MyPetsViewModel,
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
                        viewModel.goToShowAllMode()
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
    private fun PetCard(item: PetEntity, modifier: Modifier = Modifier) {
        val age = item.birthday?.toJavaLocalDateTime()?.toLocalDate()?.until(java.time.LocalDate.now())?.years
        val ageToShow = age?.let {
            if (it < 1) {
                stringResource(id = R.string.less_than_one_year)
            } else {
                pluralStringResource(id = R.plurals.years, it)
            }
        } ?: ""

        RoundIconCard(
            leftTitle = item.name,
            leftSubtitle = ageToShow,
            rightTitle = item.kind.name,
            rightSubtitle = item.breed?.name ?: "",
            modifier = modifier
        ) {
            RoundedBox(modifier = Modifier.size(40.dp)) {
                Image(
                    painter = painterResource(id = item.kind.getIconRes()),
                    contentDescription = null
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun SearchModePreview() {
        NotEmpty(
            SearchMode("Filter", listOf(PetEntity.generate())),
            MyPetsViewModel()
        )
    }

    @Preview(showBackground = true)
    @Composable
    private fun ShowAllPreview() {
        NotEmpty(
            ShowAllMode(listOf(PetEntity.generate())),
            MyPetsViewModel()
        )
    }

    @Preview
    @Composable
    private fun PetItemPreview() {
        PetCard(
            PetEntity(
                id = 1,
                name = "Кеша",
                breed = BreedEntity(1, "Ориентальная кошка"),
                sex = SexEntity(1, "Самец"),
                birthday = null,
                kind = KindEntity(1, "Кот"),
                sterilized = false
            )
        )
    }
}