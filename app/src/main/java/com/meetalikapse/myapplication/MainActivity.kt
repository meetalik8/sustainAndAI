package com.meetalikapse.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val didYouKnowCard = findViewById<LinearLayout>(R.id.tvDidYouKnow)
        val closeIcon = findViewById<ImageView>(R.id.ivClose)
        val spinnerLLM = findViewById<Spinner>(R.id.spinnerLLM)
        val calculateButton = findViewById<Button>(R.id.btnCalculate)
        val resultTextView = findViewById<TextView>(R.id.tvResult)

        // Get the Newspaper icon (ivReports)
        val ivReports = findViewById<ImageView>(R.id.ivReports)

        // Set a click listener for the newspaper icon
        ivReports.setOnClickListener {
            // Navigate to the ArticlesActivity when the icon is clicked
            val intent = Intent(this, ArticlesActivity::class.java)
            startActivity(intent)
        }

        // Populate the Spinner
        val llmOptions = arrayOf("GPT-4.0", "GPT-3.5 Turbo", "Claude 3.5 Sonnet", "Gemini Pro", "Grok")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, llmOptions)
        spinnerLLM.adapter = adapter

        // Close 'Did you know' card
        closeIcon.setOnClickListener {
            didYouKnowCard.visibility = LinearLayout.GONE
        }

        // Calculate button click
        calculateButton.setOnClickListener {
            val query = findViewById<EditText>(R.id.etQueryInput).text.toString()
            val selectedLLM = spinnerLLM.selectedItem.toString()

            if (query.isNotEmpty()) {
                val queryLength = query.length

                // Energy consumption per query in kWh (approximate)
                val energyConsumptionMap = mapOf(
                    "GPT-4.0" to 0.5,
                    "GPT-3.5 Turbo" to 0.3,
                    "Claude 3.5 Sonnet" to 0.25,
                    "Gemini Pro" to 0.2,
                    "Grok" to 0.15
                )

                // Water usage per kWh in liters (approximate)
                val waterPerKWh = 1.8

                val energyConsumption = energyConsumptionMap[selectedLLM] ?: 0.0
                val waterRequired = energyConsumption * waterPerKWh * (queryLength / 100.0)

                // Display the result in the TextView
                resultTextView.text = "Query: $query\nModel: $selectedLLM\nWater Required: %.2f liters".format(waterRequired)
            } else {
                resultTextView.text = "Please enter a query!"
            }
        }
    }
}
