package com.ontop

import androidx.compose.ui.platform.LocalUriHandler
import com.ontop.inputapp.R


data class gender(var name: String, var icon: Int)

var genders = mutableListOf(
    gender("Male", icon = R.drawable.male),
    gender(name = "Female", icon = R.drawable.female)
)
var heightVariants = mutableListOf(
    Variants.CM, Variants.Inches
)
var weightVariants = mutableListOf(
    Variants.KG, Variants.Pounds
)

enum class Variants(val value: String) {
    CM("cm"), Inches("inches"), KG("kg"), Pounds("pounds")
}


data class Person(var name: String, var age: Int)

var ageList = (10..100).toMutableList()
var heightList = (120..220).toMutableList()
var weightList = (25..220).toMutableList()


const val BMIIndexUrl = "https://my.clevelandclinic.org/health/articles/9464-body-mass-index-bmi"
