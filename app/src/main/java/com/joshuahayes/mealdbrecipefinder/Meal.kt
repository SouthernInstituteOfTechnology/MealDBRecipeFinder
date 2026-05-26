package com.joshuahayes.mealdbrecipefinder.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class MealResponse(
    val meals: List<Meal>?
)

/* this is what will map the json
to an actual class in Kotlin
param:Json is the name that
exists in the JSON data already
and is important for the conversion */
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
