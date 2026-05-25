package com.joshuahayes.mealdbrecipefinder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.joshuahayes.mealdbrecipefinder.databinding.ActivityMealDetailsBinding
import com.joshuahayes.mealdbrecipefinder.ui.MealViewModel

class MealDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealDetailsBinding
    private val viewModel: MealViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val mealId = intent.getStringExtra("MEAL_ID")
        if(mealId == null){
            finish()
            return
        }

        viewModel.selectedMeal.observe(this) {meal ->
            if(meal == null) return@observe

            binding.detailNameTextView.text = meal.name
            binding.detailMetaTextView.text = "${meal.category ?: "Unknown Category"} - ${meal.area ?: "Unknown Area"}."
            binding.detailInstructionsTextView.text = meal.instructions ?: "No instructions available."

            Glide.with(binding.detailImageView)
                .load(meal.thumbnailUrl)
                .centerCrop()
                .into(binding.detailImageView)
        }

        viewModel.loadMealDetails(mealId)


    }
}