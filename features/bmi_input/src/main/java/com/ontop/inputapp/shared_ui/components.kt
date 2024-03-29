package com.ontop.inputapp.shared_ui

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun TitleScreen(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        modifier = modifier.padding(16.dp),
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium
        )
        _gap()
        content.invoke()
    }
}

@Composable
fun _gap(height: Int = 10) = Spacer(modifier = Modifier.height(height.dp))

@Composable
fun RoundedCardView(modifier: Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier.wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        border = BorderStroke(0.1.dp, Color.Gray.copy(alpha = 0.8f)),
        shape = RoundedCornerShape(10.dp), // Set the corner radius
    ) {

        content()

    }
}


@Composable
fun GenderView(modifier: Modifier, icon: Int, genderText: String) {
    Column(
        modifier = modifier.padding(horizontal = 50.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        SVGloader(modifier = modifier.size(30.dp, 50.dp), iconResource = icon)
        Text(text = genderText)
    }

}

@Composable
fun SVGloader(modifier: Modifier = Modifier, iconResource: Int, iconDesc: String = "") {
    Icon(
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NumberPickers(
    modifier: Modifier,
    numbers: List<Int>,
    initialIndex: Int = 5,
    onNumberSelected: (Int) -> Unit
) {
    val scrollState = rememberLazyListState(initialIndex)
    val coroutineScope = rememberCoroutineScope()
    val center = remember { scrollState.layoutInfo }.viewportEndOffset / 2

    Column {
        LazyRow(
            modifier = modifier.padding(20.dp),
            state = scrollState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = scrollState)

        ) {
            itemsIndexed(numbers) { index, number ->
                Text(
                    text = number.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clickable {
                            //                        onNumberSelected(number)
                            //                        coroutineScope.launch {
                            //                            scrollState.animateScrollAndCentralizeItem(index , this)
                            //                        }
                        }
                )
            }
        }
        Text(text = "${scrollState.firstVisibleItemIndex}")
    }

}


@Composable
fun HeightView(
    modifier: Modifier,
    height: Boolean,
    state: LazyListState,
    index: Int,
) {
    val centerItemIndex by remember {
        derivedStateOf {
            val layoutInfo = state.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

            visibleItems.minByOrNull { abs((it.offset + it.size / 2) - viewportCenter) }?.index ?: 0
        }
    }
    var isItemCenterd = centerItemIndex == index

    Column(modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = height.toString(),
            textAlign = TextAlign.Start,
            fontWeight = if (isItemCenterd) FontWeight.Bold else FontWeight.Light
        )
        _gap(height = 30)
        Divider(
            color = if (isItemCenterd) Color.Blue else Color.Gray,
            modifier = Modifier
                .height(if (isItemCenterd) 30.dp else 20.dp)
                .width(if (isItemCenterd) 3.dp else 1.dp)
        )
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


@Composable
fun BMIButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    Button(modifier=modifier,onClick = onClick,colors= ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onPrimary),
        shape = RoundedCornerShape(12.dp)) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}