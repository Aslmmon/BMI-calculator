package com.ontop.moviesapp.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ontop.moviesapp.R
import com.ontop.moviesapp.ui.SharedViewModel
import com.ontop.moviesapp.ui.shared.components.LoadImageWidget
import com.ontop.moviesapp.utils.prePathImageForMovieDB
import com.ontop.network.model.Movie

@Composable
fun DetailScreen(modifier: Modifier = Modifier, sharedViewModel: SharedViewModel<Movie>) {
    val movie = sharedViewModel.data.value
    val overlayImage = "$prePathImageForMovieDB${movie?.backdrop_path}"
    Column {
        LoadImageWidget(
            modifier = modifier
                .fillMaxWidth()
                .size(200.dp), url = overlayImage,contentScale = ContentScale.Fit
        )
        movie?.overview?.let { Text(text = it) }
        Spacer(modifier = modifier.height(10.dp))
        movie?.release_date?.let { Text(text = stringResource(id = R.string.release_date)+ it) }


    }

}