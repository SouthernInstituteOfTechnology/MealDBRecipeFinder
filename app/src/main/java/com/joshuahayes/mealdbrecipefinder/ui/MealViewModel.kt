package com.joshuahayes.mealdbrecipefinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joshuahayes.mealdbrecipefinder.MealApi
import com.joshuahayes.mealdbrecipefinder.data.Meal
import kotlinx.coroutines.launch

class MealViewModel : ViewModel() {

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals

    private val _selectedMeal = MutableLiveData<Meal?>()
    val selectedMeal: LiveData<Meal?> = _selectedMeal

    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> = _statusMessage

    fun searchMeals(searchTerm: String){
        if(searchTerm.isBlank()){
            _statusMessage.value = "Enter a recipe name to search."
            _meals.value = emptyList()
            return
        }

        viewModelScope.launch{
            try {
                _statusMessage.value = "Loading recipies..."
                val response = MealApi.service.searchMeals(searchTerm)
                val results = response.meals ?: emptyList()

                _meals.value = results
                _statusMessage.value = if (results.isEmpty()) "No recipes found." else ""
            }
            catch (ex: Exception){
                _statusMessage.value = "Could not load recipes."
                _meals.value = emptyList()
            }
        }
    }

    fun loadMealDetails(id: String){
        viewModelScope.launch{
            try {
                val response = MealApi.service.getMealById(id)
                _selectedMeal.value = response.meals?.firstOrNull()
            }
            catch (ex: Exception){
                _selectedMeal.value = null
            }
        }
    }
}