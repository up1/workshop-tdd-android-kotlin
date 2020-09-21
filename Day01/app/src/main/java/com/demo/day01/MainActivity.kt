package com.demo.day01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.day01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        binding.hello.text = "Demo"
    }
}