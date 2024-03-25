package com.ontop.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val backdrop_path: String?=null,
    val id: Int?=null,
    val original_language: String?=null,
    val original_title: String?=null,
    val overview: String?=null,
    val poster_path: String?=null,
    val release_date: String?=null,
    val title: String?=null,
)