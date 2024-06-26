package com.ontop.inputapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.inputapp.R
import com.ontop.inputapp.shared_ui.AgeView
import com.ontop.inputapp.shared_ui.BMIButton
import com.ontop.inputapp.shared_ui.ContentWithTitle
import com.ontop.inputapp.shared_ui.GenderView
import com.ontop.inputapp.shared_ui.HeightViewNew
import com.ontop.inputapp.shared_ui.RoundedCardView
import com.ontop.inputapp.shared_ui.ScrollableRowList
import com.ontop.inputapp.shared_ui.TitleScreen
import com.ontop.inputapp.shared_ui._gap


@Composable
fun BmiInputScreen(
    modifier: Modifier = Modifier,
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel()
) {
    var scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .verticalScroll(scrollState)
    ) {
        TitleScreen(modifier, stringResource(id = R.string.bmi_calculator_title))
        ContentWithTitle(title = stringResource(R.string.gender_title)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RoundedCardView(modifier = modifier) {
                    GenderView(
                        modifier = modifier, icon = R.drawable.male, genderText = stringResource(
                            R.string.male
                        )
                    )
                }
                RoundedCardView(modifier = modifier) {
                    GenderView(
                        modifier = modifier, icon = R.drawable.female, genderText = stringResource(
                             R.string.female
                         )
                     )
                }
            }
        }

        ContentWithTitle(title = stringResource(R.string.height)) {
            RoundedCardView(modifier = modifier) {
                var lists =
                    listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 23, 45, 12, 12, 12, 14, 13, 14)

                ScrollableRowList(modifier = modifier, content = { isSameIndex, item ->
                    HeightViewNew(modifier, isSameIndex, item)

                }, lists = lists)

            }

        }

        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            ContentWithTitle(modifier = modifier, title = stringResource(R.string.age_title)) {
                RoundedCardView(modifier = modifier) {
                    AgeView(
                        modifier = modifier
                            .padding(5.dp)
                            .height(50.dp),
                        minusIcon = R.drawable.minus,
                        plusIcon = R.drawable.plus
                    )
                }

            }
            Spacer(modifier = modifier.width(50.dp))
            ContentWithTitle(
                title = stringResource(R.string.weight),
                modifier = modifier.padding(horizontal = 10.dp)
            ) {
                RoundedCardView(modifier = modifier) {
                    var lists =
                        listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 23, 45, 12, 12, 12, 14, 13, 14)

                    ScrollableRowList(
                        modifier = modifier.size(150.dp, 60.dp),
                        content = { isSameIndex, item ->
                            Text(
                                modifier = modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                                text = item.toString(),
                                fontWeight = if (isSameIndex) FontWeight.Bold else FontWeight.Light
                            )
                        },
                        lists = lists
                    )

                }
            }

        }
        _gap(20)
        BMIButton(
            modifier = modifier.fillMaxWidth(),
            stringResource(R.string.calculate_bmi),
            onClick = {

            })
    }
}


@Preview
@Composable
fun ShowBMiInputScreen() {
    BmiInputScreen()
}
