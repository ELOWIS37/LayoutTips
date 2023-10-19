package com.elowis.layouttips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elowis.layouttips.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    fun calculateTip() {
        val stringInTextField = binding.editTextText.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isSelected

        if (roundUp) {
            tip = ceil(tip)
        }

        val nFormat = NumberFormat.getCurrencyInstance()
        val formattedTip = nFormat.format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}