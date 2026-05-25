package com.joshuahayes.mealdbrecipefinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joshuahayes.mealdbrecipefinder.data.Meal
import com.joshuahayes.mealdbrecipefinder.databinding.ItemMealBinding

class MealAdapter(private val onMealClicked: (Meal) -> Unit)
    : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private var meals: List<Meal> = emptyList()

    fun submitList(newMeals: List<Meal>){
        meals = newMeals
        notifyDataSetChanged()
    }

    class MealViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]

        holder.binding.mealNameTextView.text = meal.name

        Glide.with(holder.binding.mealImageView)
            .load(meal.thumbnailUrl)
            .centerCrop()
            .into(holder.binding.mealImageView)

        holder.binding.root.setOnClickListener {
            onMealClicked(meal)
        }
    }

    override fun getItemCount(): Int = meals.size
}