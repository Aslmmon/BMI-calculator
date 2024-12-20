package com.ontop.inputapp.shared_ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ontop.ageList
import com.ontop.gender
import com.ontop.heightList
import com.ontop.weightList
import kotlin.math.abs

@Composable
fun TitleScreen(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
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
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(vertical = 15.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleSmall
        )
        content.invoke()
    }
}

@Composable
fun _gap(height: Int = 10) = Spacer(modifier = Modifier.height(height.dp))

@Composable
fun RoundedCardView(modifier: Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        border = BorderStroke(0.1.dp, Color.Gray.copy(alpha = 0.8f)),
        shape = RoundedCornerShape(10.dp), // Set the corner radius
    ) {
        content.invoke()
    }
}


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
fun AgeView(modifier: Modifier, minusIcon: Int, plusIcon: Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SVGloader(modifier = modifier.size(20.dp), iconResource = minusIcon)
        Spacer(modifier = modifier.width(10.dp))
        Text(text = "22", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = modifier.width(10.dp))
        SVGloader(modifier = modifier.size(20.dp), iconResource = plusIcon)

    }
}


@Composable
fun HeightViewNew(
    modifier: Modifier,
    isSameIndex: Boolean,
    item: Int,
) {
    Column(modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = item.toString(),
            textAlign = TextAlign.Start,
            fontWeight = if (isSameIndex) FontWeight.Bold else FontWeight.Light
        )
        _gap(height = 30)
        Divider(
            color = if (isSameIndex) Color.Blue else Color.Gray,
            modifier = Modifier
                .height(if (isSameIndex) 30.dp else 20.dp)
                .width(if (isSameIndex) 3.dp else 1.dp)
        )
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> ScrollableRowList(
    modifier: Modifier,
    content: @Composable (Boolean, T) -> Unit,
    lists: List<T>
) {
    val state = rememberLazyListState()
    val centerItemIndex by remember {
        derivedStateOf {
            val layoutInfo = state.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            visibleItems.minByOrNull { abs((it.offset + it.size / 2) - viewportCenter) }?.index ?: 0
        }
    }
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = state,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state)
    ) {
        itemsIndexed(lists) { index, item ->
            val isItemCenterd = centerItemIndex == index
            content.invoke(isItemCenterd, item)
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgeContent(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        listState.scrollToItem(0, 10)
    }
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
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = modifier.size(24.dp)
            )
            LazyColumn(
                modifier = Modifier.height(100.dp),
                state = listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
            ) {
                itemsIndexed(ageList) { index, numbers ->
                    Text(
                        "$numbers",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun HeightContent(modifier: Modifier = Modifier) {

    LazyRow(
        modifier = Modifier.height(100.dp),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(heightList) { index, numbers ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "$numbers",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )
                // if (index < heightList.lastIndex) {// Vertical divider
                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxHeight()  //fill the max height
                        .width(0.5.dp)
                )
                //}
            }

        }
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeightContent(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Play",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = modifier
                .size(24.dp)
                .rotate(90f) //Rotate by 45 degrees

        )
        LazyRow(
            modifier = Modifier.height(50.dp),
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
        ) {
            itemsIndexed(weightList) { index, numbers ->
                Text(
                    "$numbers",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp)
                )

            }
        }
    }


}


@Composable
fun BMIButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier.height(60.dp),
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.Normal)
    }
}