package com.rus_artur4ik.veterinarian.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rus_artur4ik.veterinarian.R
import com.rus_artur4ik.veterinarian.common.Carousel
import com.rus_artur4ik.veterinarian.common.VetScreenTemplate
import com.rus_artur4ik.veterinarian.common.mvvm.CoreScreen
import com.rus_artur4ik.veterinarian.domain.entity.PetEntity
import com.rus_artur4ik.veterinarian.domain.entity.Sex
import com.rus_artur4ik.veterinarian.domain.entity.VisitEntity
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

class HomeScreen : CoreScreen<HomeScreenState, HomeViewModel>(
    HomeViewModel::class.java
) {

    @Composable
    override fun Content(viewModel: HomeViewModel, navHostController: NavHostController?) {
        VetScreenTemplate(name = "Екатерина", navHostController) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = "Мои питомцы",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = Dp(24f))
                )

                Carousel(viewModel.state.value.pets) {
                    PetCard(
                        pet = it,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { viewModel.goToPetInfo(it) }
                    )
                }

                Spacer(modifier = Modifier.height(Dp(24f)))

                Text(
                    text = stringResource(id = R.string.last_visit),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = Dp(24f))
                )

                LastVisitCard(visit = viewModel.state.value.lastVisit)

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    onClick = { viewModel.makeAnAppointment() }
                ) {
                    Text(text = stringResource(id = R.string.make_an_appointment))
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = { viewModel.notifyAboutVisit() }
                ) {
                    Text(text = stringResource(id = R.string.notify_about_visit))
                }
            }
        }
    }

    @Composable
    private fun PetCard(pet: PetEntity, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier
                .width(134.dp)
                .height(112.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = ColorPainter(Color.Gray),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(36.dp)
                )

                Text(
                    text = pet.name,
                    fontWeight = FontWeight.Black
                )

                Text(
                    text = pet.breed,
                    textAlign = TextAlign.Center
                )
            }

        }
    }

    @Composable
    private fun LastVisitCard(visit: VisitEntity) {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM, HH:mm")
        val formattedDate = visit.date.toJavaLocalDateTime().format(dateTimeFormatter)

        Card(
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = formattedDate,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 12.dp
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp
                )
            ) {
                Text(
                    text = "Питомец:",
                    fontSize = 12.sp
                )

                Text(
                    text = visit.pet.name,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        start = 16.dp
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 8.dp
                )
            ) {
                Text(
                    text = "Диагнозы:",
                    fontSize = 12.sp,
                )

                Text(
                    text = visit.diagnoses.reduce { acc, s -> "$acc; $s" },
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        start = 16.dp
                    )
                )
            }
        }
    }

    @Composable
    @Preview(showBackground = true)
    private fun Preview() {
        Content()
    }

    @Composable
    @Preview(showBackground = true)
    private fun PetCardPreview() {
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

    @Composable
    @Preview(showBackground = true)
    private fun LastVisitCardPreview() {
        LastVisitCard(
            VisitEntity(
                date = LocalDateTime(
                    LocalDate.fromEpochDays(10),
                    LocalTime(1, 1, 1)
                ),
                diagnoses = listOf(
                    "Диагноз 1",
                    "Диагноз 2",
                ),
                pet = PetEntity(
                    name = "Кеша",
                    breed = "Ориентальная кошка",
                    sex = Sex.MALE,
                    birthday = null,
                    kind = "Кот",
                    lastVisit = null
                )
            )
        )
    }
}