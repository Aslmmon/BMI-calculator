package com.ontop.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    var page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)