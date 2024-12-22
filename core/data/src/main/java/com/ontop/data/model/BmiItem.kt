package com.ontop.data.model

data class BmiItem(
    var status: String,
    var rangeFrom: String,
    var rangeTo: String,
    var risk: String,
    var summary: String,
    var recommendation: String
)