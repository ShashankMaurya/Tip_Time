package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        if (cost == null) {
            displayTip()
            return
        }
        val tip_perc = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_perc -> 0.20
            R.id.eighteen_perc -> 0.18
            else -> 0.15
        }
        val tip = if (binding.roundUpSwitch.isChecked) ceil(cost * tip_perc) else cost * tip_perc
        displayTip(tip)
    }

    private fun displayTip(tip: Double =0.0)
    {
        val currency_tip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, currency_tip)
    }
}