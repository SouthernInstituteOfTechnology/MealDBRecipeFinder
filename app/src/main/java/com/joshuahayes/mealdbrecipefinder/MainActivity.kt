package com.joshuahayes.mealdbrecipefinder

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.joshuahayes.mealdbrecipefinder.databinding.ActivityMainBinding
import com.joshuahayes.mealdbrecipefinder.ui.MealViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MealViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val adapter = MealAdapter { meal ->
            val intent = Intent(this, MealDetailsActivity::class.java)
            intent.putExtra("MEAL_ID", meal.id)
            startActivity(intent)
        }

        binding.mealsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mealsRecyclerView.adapter = adapter

        binding.searchButton.setOnClickListener {
            val searchTerm = binding.searchEditText.text.toString()
            viewModel.searchMeals(searchTerm)
        }

        viewModel.meals.observe(this) { meals ->
            adapter.submitList(meals)
        }

        viewModel.statusMessage.observe(this){ message ->
            binding.statusTextView.text = message
        }
    }
}