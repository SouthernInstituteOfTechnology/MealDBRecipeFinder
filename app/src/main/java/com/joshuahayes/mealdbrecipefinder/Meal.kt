package com.joshuahayes.mealdbrecipefinder.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class MealResponse(
    val meals: List<Meal>?
)

@JsonClass(generateAdapter = false)
data class Meal(
    @param:Json(name = "idMeal")
    val id: String,

    @param:Json(name = "strMeal")
    val name: String,

    @param:Json(name = "strMealThumb")
    val thumbnailUrl: String?,

    @param:Json(name = "StrCategory")
    val category: String? = null,

    @param:Json(name = "strArea")
    val area: String? = null,

    @param:Json(name = "strInstructions")
    val instructions: String? = null
)
