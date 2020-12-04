package com.ankur.mynotes.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ankur.mynotes.R
import com.ankur.mynotes.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,R.layout.activity_main)
        binding.lifecycleOwner = this
        initObject()
    }

    private fun initObject() {
        navController = findNavController(R.id.container)
    }
}