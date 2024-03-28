package com.ontop.inputapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ontop.inputapp.R
import com.ontop.inputapp.shared_ui.AgeView
import com.ontop.inputapp.shared_ui.ContentWithTitle
import com.ontop.inputapp.shared_ui.GenderView
import com.ontop.inputapp.shared_ui.HeightView
import com.ontop.inputapp.shared_ui.NumberPickers
import com.ontop.inputapp.shared_ui.RoundedCardView
import com.ontop.inputapp.shared_ui.TitleScreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BmiInputScreen(
    modifier: Modifier = Modifier,
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel()
) {
    val (height, setHeight) = remember { mutableStateOf(160) }
    var pickerValue by remember { mutableStateOf(50) }
    var context = LocalContext.current

    Column(
        modifier = modifier
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
            RoundedCardView(modifier = modifier.padding(10.dp)) {
                var lists =
                    listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 23, 45, 12, 12, 12, 14, 13, 14)
                val state = rememberLazyListState()


                LazyRow(
                    modifier = modifier.fillMaxWidth(),
                    state = state,
                    flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
                ) {
                    itemsIndexed(lists) { index, item ->
                        HeightView(modifier, item, state, index)
                    }

                }
            }

        }

        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            ContentWithTitle(modifier = modifier, title = stringResource(R.string.age_title)) {
                RoundedCardView(modifier = modifier) {
                    AgeView(
                        modifier = modifier,
                        minusIcon = R.drawable.minus,
                        plusIcon = R.drawable.plus
                    )
                }

            }
            ContentWithTitle(title = "Weight", modifier = modifier.padding(horizontal = 10.dp)) {
                RoundedCardView(modifier = modifier) {

                    NumberPickers(
                        modifier = modifier,
                        numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 23, 45),
                        onNumberSelected = { number ->
                        })

                }
            }

        }
    }
}


@Preview
@Composable
fun ShowBMiInputScreen() {
    BmiInputScreen()
}

fun LazyListLayoutInfo.normalizedItemPosition(key: Any): Float =
    visibleItemsInfo
        .firstOrNull { it.key == key }
        ?.let {
            val center = (viewportEndOffset + viewportStartOffset - it.size) / 2F
            (it.offset.toFloat() - center) / center
        }
        ?: 0F
