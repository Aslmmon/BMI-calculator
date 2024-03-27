package com.ontop.inputapp.shared_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
        shape = RoundedCornerShape(16.dp), // Set the corner radius
    ) {

        content()

    }
}


@Composable
fun GenderView(modifier: Modifier, icon: Int, genderText: String) {
    Column(
        modifier = modifier.padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        SVGloader(modifier = modifier.size(50.dp, 50.dp), iconResource = icon)
        _gap(20)
        Text(text = genderText, style = MaterialTheme.typography.titleSmall)
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
        modifier = modifier
            .padding(horizontal = 25.dp, vertical = 10.dp)
            .size(80.dp, 50.dp),
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
    val center = remember {   scrollState.layoutInfo  }.viewportEndOffset / 2

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
fun LazyListState.animateScrollAndCentralizeItem(index: Int, scope: CoroutineScope) {
    val itemInfo = this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
    scope.launch {
        if (itemInfo != null) {
            val center = this@animateScrollAndCentralizeItem.layoutInfo.viewportEndOffset / 2
            val childCenter = itemInfo.offset + itemInfo.size / 2
            this@animateScrollAndCentralizeItem.animateScrollBy((childCenter - center).toFloat())
        } else {
            this@animateScrollAndCentralizeItem.animateScrollToItem(index)
        }
    }
}
