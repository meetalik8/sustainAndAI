package com.meetalikapse.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // Get reference to the button
        val getStartedButton: Button = findViewById(R.id.getStartedButton)

        // Set click listener for the button
        getStartedButton.setOnClickListener {
            // Navigate to the next activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}