package com.ontop.moviesapp.ui.shared.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.ontop.moviesapp.R


@Composable
fun LoadImageWidget(
    modifier: Modifier = Modifier,
    url: String,
    errorImage: Painter? = null,
    contextDescription: String? = null,
    contentScale: ContentScale
) {
    AsyncImage(
        model = url,
        error = errorImage,
        contentScale = contentScale,
        contentDescription = contextDescription,
        modifier = modifier,
        placeholder = painterResource(R.drawable.dob),
    )
}
