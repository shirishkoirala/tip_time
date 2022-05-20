package com.shirishkoirala.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shirishkoirala.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{calculateTip()}
    }

    private fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_15_percent -> 0.15
            R.id.option_18_percent -> 0.18
            else -> 0.20
        }
        if(cost != null){
            var tip = cost * tipPercentage

            if (binding.roundUpSwitch.isChecked){
                tip = ceil(tip)
            }
            displayTip(tip)
        }else if(cost == 0.0){
            displayTip(0.0)
        }else{
            binding.tipResult.text = "Invalid Value"
        }

    }

    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}