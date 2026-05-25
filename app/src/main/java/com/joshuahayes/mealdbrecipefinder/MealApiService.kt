package com.joshuahayes.mealdbrecipefinder

import com.joshuahayes.mealdbrecipefinder.data.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("search.php")
    suspend fun searchMeals(
        @Query("s") searchTerm: String
    ): MealResponse

    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") id: String
    ): MealResponse
}