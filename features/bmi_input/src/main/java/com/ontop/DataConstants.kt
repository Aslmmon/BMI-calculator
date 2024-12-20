package com.ontop

import com.ontop.inputapp.R


data class gender(var name: String, var icon: Int)

var genders = mutableListOf(
    gender("Male", icon = R.drawable.male),
    gender(name = "Female", icon = R.drawable.female)
)

data class Person(var name: String, var age: Int)

var ageList = (25..80).toMutableList()
var heightList = (120..220).toMutableList()
var weightList = (25..220).toMutableList()

