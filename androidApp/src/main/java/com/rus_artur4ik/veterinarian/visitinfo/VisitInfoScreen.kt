package com.rus_artur4ik.veterinarian.visitinfo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.mvvm.BaseScreen
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.coroutines.launch

class VisitInfoScreen : BaseScreen<VisitInfoScreenState, VisitInfoViewModel>(
    VisitInfoViewModel::class.java
) {

    private val tabs = listOf(R.string.diagnosis, R.string.research)

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content(content: VisitInfoScreenState, viewModel: VisitInfoViewModel) {
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, titleRes ->
                Tab(
                    text = {
                        Text(stringResource(id = titleRes))
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            pageCount = 2,
            state = pagerState
        ) {
            when (it) {
                0 -> DiagnosisPage(content.visit)
                1 -> ResearchPage(content.visit)
            }
        }
    }

    @Composable
    private fun DiagnosisPage(visit: VisitEntity) {
        Text(text = "diagnosis page")
    }

    @Composable
    private fun ResearchPage(visit: VisitEntity) {
        Text(text = "research page")
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content(VisitInfoViewModel())
    }

    @Composable
    @Preview(showBackground = true)
    private fun DiagnosisPreview() {
        DiagnosisPage(VisitEntity.generate())
    }

    @Composable
    @Preview(showBackground = true)
    private fun ResearchPreview() {
        ResearchPage(VisitEntity.generate())
    }

    companion object {
        const val VISIT_ID_KEY = "visitId"
    }
}