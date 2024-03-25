package com.ontop.moviesapp.ui.shared.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.ontop.moviesapp.utils.prePathImageForMovieDB
import com.ontop.network.model.Movie

@Composable
fun MovieItem(modifier: Modifier, movie: Movie?, onClick: (Movie?) -> Unit) {
    Row(
        modifier = modifier
            .clickable {
                onClick.invoke(movie)
            }
            .fillMaxSize()
            .padding(10.dp),
    ) {
        val overlayImage =
            "$prePathImageForMovieDB${movie?.poster_path}"
        LoadImageWidget(
            modifier = Modifier
                .clip(RectangleShape)
                .size(100.dp),
            url = overlayImage, contentScale = ContentScale.Fit
        )
        Column(verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxHeight()) {
            Log.e("image", "http://image.tmdb.org/t/p/w500${movie?.backdrop_path}")
            Text(
                text = movie?.title ?: "", color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = movie?.release_date ?: "", color = MaterialTheme.colorScheme.primary,
            )

        }
    }
}
