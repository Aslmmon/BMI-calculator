package com.ontop.inputapp.shared_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ontop.inputapp.R

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
        modifier = modifier.fillMaxWidth(),
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
        modifier = modifier
            .size(180.dp, 200.dp)
            .padding(16.dp)
            .background(Color.White),
        shape = RoundedCornerShape(16.dp), // Set the corner radius
    ) {

        content()

    }
}


@Composable
fun GenderView(modifier: Modifier, icon: Int, genderText: String) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "male",
            modifier = Modifier.size(50.dp, 50.dp),
        )
        _gap(20)
        Text(text = genderText, style = MaterialTheme.typography.titleSmall)
    }

}
