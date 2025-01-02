package com.ontop.inputapp.shared

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ontop.BMIIndexUrl
import com.ontop.ageList
import com.ontop.gender
import com.ontop.genders
import com.ontop.heightList
import com.ontop.inputapp.R
import com.ontop.weightList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun TitleScreen(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun ContentWithTitle(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
    showContentVariants: Boolean = false,
    variants: @Composable () -> Unit? = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(vertical = 15.dp),
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            if (showContentVariants) {
                variants.invoke()
            }
        }
        content.invoke()
    }
}

@Composable
fun _Gap(height: Int = 10) = Spacer(modifier = Modifier.height(height.dp))


@Composable
fun GenderView(
    modifier: Modifier,
    gender: gender,
    onGenderClicked: () -> Unit,
    isGenderSelected: Boolean
) {

    Box(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = if (isGenderSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(indication = null, interactionSource = remember {
                MutableInteractionSource()
            }) {
                onGenderClicked.invoke()
            }
            .clip(RoundedCornerShape(11.dp))
            .background(if (isGenderSelected) MaterialTheme.colorScheme.secondary else Color.White)

        // Apply rounded corners
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(60.dp)
                .width(150.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                gender.name,
                color = if (isGenderSelected) Color.White else MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp)) // Add some space between icon and text
            SVGloader(
                modifier = modifier.size(20.dp, 20.dp),
                iconResource = gender.icon,
                tintColor = if (isGenderSelected) Color.White else MaterialTheme.colorScheme.primary
            )

        }
    }


}

@Composable
fun Variant(
    variantName: String,
    onVariantClicked: () -> Unit,
    isVariantChosen: Boolean
) {

    Spacer(modifier = Modifier.width(5.dp))
    Box(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = if (isVariantChosen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(indication = null, interactionSource = remember {
                MutableInteractionSource()
            }) {
                onVariantClicked.invoke()
            }
            .clip(RoundedCornerShape(11.dp))
            .background(if (isVariantChosen) MaterialTheme.colorScheme.primary else Color.White)
            .width(50.dp),
        contentAlignment = Alignment.Center // Align to the left (start)
        // Apply rounded corners
    ) {
        Text(
            variantName,
            color = if (isVariantChosen) Color.White else MaterialTheme.colorScheme.primary,
            fontSize = 10.sp,
        )
    }


}


@Composable
fun SVGloader(
    modifier: Modifier = Modifier,
    iconResource: Int,
    iconDesc: String = "",
    tintColor: Color = Color.White
) {
    Icon(
        tint = tintColor,
        painter = painterResource(id = iconResource),
        contentDescription = iconDesc,
        modifier = modifier,
    )
}


@Composable
fun GenderContent(genderChosen: (Int) -> Unit) {
    var selectedGender by remember { mutableStateOf<Int>(0) }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround // Distribute items evenly
    ) {
        itemsIndexed(genders) { index, item ->
            GenderView(
                modifier = Modifier, gender = item, onGenderClicked = {
                    selectedGender = index
                    genderChosen(selectedGender)
                }, selectedGender == index
            )
        }
    }
}

@Composable
fun VariantContent(listToPopulateVariants: MutableList<String>) {
    var selectdVariant by remember { mutableStateOf<Int>(0) }
    LazyRow(

        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.aligned(Alignment.End),
        contentPadding = PaddingValues(10.dp)
    ) {
        itemsIndexed(listToPopulateVariants) { index, item ->
            Variant(item, onVariantClicked = {
                selectdVariant = index
            }, isVariantChosen = selectdVariant == index)
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgeContent(modifier: Modifier = Modifier, onAgeChosen: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .clip(RoundedCornerShape(11.dp)),
        contentAlignment = Alignment.Center // Center content of the Box

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ArrowIcon(modifier)
            LazyWrapperDetectingCenter(ageList, onChosenCenterItem = { centerItem ->
                onAgeChosen(centerItem)
            }, onContentDrawn = { highlightedItemIndex, listState, padding ->


                LazyColumn(
                    modifier = Modifier.height(100.dp),
                    state = listState,
                    contentPadding = PaddingValues(top = 35.dp, bottom = 35.dp),
                    flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
                ) {
                    itemsIndexed(ageList) { index, numbers ->
                        val isHighlighted = highlightedItemIndex == index
                        val color =
                            if (isHighlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.2f
                            )
                        Text(
                            "$numbers",
                            color = color,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            })

        }
    }

}


@OptIn(FlowPreview::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun LazyWrapperDetectingCenter(
    listToGetCenterItemFrom: List<Int>,
    onChosenCenterItem: (Int) -> Unit,
    onContentDrawn: @Composable (Int?, LazyListState, Dp) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var highlightedItemIndex by remember { mutableStateOf<Int?>(null) }
    val configuration = LocalConfiguration.current
    val itemWidth = 50.dp // Adjust based on your item width
    val startPaddings = (configuration.screenWidthDp.dp - itemWidth) / 2
    LaunchedEffect(listState) {

        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            val closestItem = visibleItems.minByOrNull { abs(it.offset + it.size / 2) }
            closestItem?.index
        }.debounce(300).collectLatest { index ->
            coroutineScope.launch {
                highlightedItemIndex = index
            }
        }
    }

    if (!listState.isScrollInProgress) {
        coroutineScope.launch {
            delay(600)
            onChosenCenterItem(listToGetCenterItemFrom[highlightedItemIndex ?: 0])
        }
    }
    onContentDrawn(highlightedItemIndex, listState, startPaddings)
}

@Composable
private fun ArrowIcon(modifier: Modifier) {
    Icon(
        imageVector = Icons.Filled.PlayArrow,
        contentDescription = "Play",
        tint = MaterialTheme.colorScheme.surface,
        modifier = modifier.size(24.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeightContent(onHeightChosen: (Int) -> Unit) {
    LazyWrapperDetectingCenter(heightList, onChosenCenterItem = { centerItem ->
        onHeightChosen(centerItem)
    }, onContentDrawn = { highlightedItemIndex, listState, padding ->

        LazyRow(
            modifier = Modifier.height(100.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            state = listState,
            contentPadding = PaddingValues(start = padding, end = padding),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
        ) {
            itemsIndexed(heightList) { index, numbers ->
                val isHighlighted = highlightedItemIndex == index
                val color =
                    if (isHighlighted) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.2f
                    )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "$numbers",
                            color = color,
                            fontWeight = FontWeight.Bold,
                        )
                        Divider(
                            color = color,
                            modifier = Modifier
                                .height(50.dp)  //fill the max height
                                .width(if (isHighlighted) 4.dp else 2.dp)
                        )
                    }
                    Divider(
                        color = MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.2f
                        ),
                        thickness = 0.1.dp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .height(20.dp)
                            .width(1.dp)
                    )
                }


            }
        }
    })


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeightContent(modifier: Modifier = Modifier, onWeightChosen: (Int) -> Unit) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArrowIcon(modifier = modifier.rotate(90f))
        LazyWrapperDetectingCenter(weightList, onChosenCenterItem = { centerItem ->
            onWeightChosen(centerItem)
        }, onContentDrawn = { highlightedItemIndex, listState, padding ->
            LazyRow(
                modifier = Modifier.height(50.dp),
                state = listState,
                contentPadding = PaddingValues(start = padding, end = padding),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
            ) {
                itemsIndexed(weightList) { index, numbers ->
                    val isHighlighted = highlightedItemIndex == index
                    Text(
                        "$numbers",
                        color = if (isHighlighted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.2f
                        ),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(5.dp)
                    )

                }
            }
        })
    }


}


@Composable
fun ButtonWithHyperLinkContent(
    buttonText: Int,
    isLoading: Boolean = false,
    onCalculateClicked: () -> Unit,

    urlToBeOpend: String = BMIIndexUrl,
) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(id = R.string.learn_text), fontSize = 10.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    try {
                        uriHandler.openUri(urlToBeOpend)
                    } catch (e: Exception) {
                        Log.e("error", "ButtonWithHyperLinkContent: ${e.message}")
                    }
                },
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
            _Gap()
            BMIButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End),
                stringResource(buttonText),
                isLoading = isLoading,
                onClick = onCalculateClicked
            )
        }
    }
}

@Composable
fun BMIButton(modifier: Modifier, text: String, onClick: () -> Unit, isLoading: Boolean = false) {
    Button(
        modifier = modifier.height(55.dp),
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(35.dp)
    ) {
        when (isLoading) {
            false -> Text(text = text, fontWeight = FontWeight.Normal)
            true -> CircularProgressIndicator(
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}