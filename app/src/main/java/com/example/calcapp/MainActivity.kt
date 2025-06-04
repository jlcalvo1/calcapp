package com.example.calcapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calcapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    
    private var display = "0"
    private var previousValue = 0.0
    private var operation: String? = null
    private var waitingForValue = false
    private var currentOperationButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupClickListeners()
        updateDisplay()
    }

    private fun setupClickListeners() {
        // Number buttons
        binding.btn0.setOnClickListener { onNumberClick("0") }
        binding.btn1.setOnClickListener { onNumberClick("1") }
        binding.btn2.setOnClickListener { onNumberClick("2") }
        binding.btn3.setOnClickListener { onNumberClick("3") }
        binding.btn4.setOnClickListener { onNumberClick("4") }
        binding.btn5.setOnClickListener { onNumberClick("5") }
        binding.btn6.setOnClickListener { onNumberClick("6") }
        binding.btn7.setOnClickListener { onNumberClick("7") }
        binding.btn8.setOnClickListener { onNumberClick("8") }
        binding.btn9.setOnClickListener { onNumberClick("9") }
        binding.btnDecimal.setOnClickListener { onDecimalClick() }

        // Operation buttons
        binding.btnPlus.setOnClickListener { onOperationClick("+", binding.btnPlus) }
        binding.btnMinus.setOnClickListener { onOperationClick("-", binding.btnMinus) }
        binding.btnMultiply.setOnClickListener { onOperationClick("×", binding.btnMultiply) }
        binding.btnDivide.setOnClickListener { onOperationClick("÷", binding.btnDivide) }
        
        // Function buttons
        binding.btnEquals.setOnClickListener { onEqualsClick() }
        binding.btnClear.setOnClickListener { onClearClick() }
        binding.btnPlusMinus.setOnClickListener { onPlusMinusClick() }
        binding.btnPercent.setOnClickListener { onPercentClick() }
    }

    private fun onNumberClick(number: String) {
        if (waitingForValue) {
            display = number
            waitingForValue = false
        } else {
            display = if (display == "0") number else display + number
        }
        updateDisplay()
    }

    private fun onDecimalClick() {
        if (!display.contains(".")) {
            onNumberClick(".")
        }
    }

    private fun onOperationClick(op: String, button: Button) {
        if (operation != null && !waitingForValue) {
            onEqualsClick()
        }
        
        // Clear previous operation button selection
        currentOperationButton?.isSelected = false
        
        // Set new operation and button selection
        previousValue = display.toDoubleOrNull() ?: 0.0
        operation = op
        waitingForValue = true
        currentOperationButton = button
        button.isSelected = true
    }

    private fun onEqualsClick() {
        val currentValue = display.toDoubleOrNull() ?: 0.0
        val result = when (operation) {
            "+" -> previousValue + currentValue
            "-" -> previousValue - currentValue
            "×" -> previousValue * currentValue
            "÷" -> if (currentValue != 0.0) previousValue / currentValue else 0.0
            else -> currentValue
        }
        
        display = if (result == result.toInt().toDouble()) {
            result.toInt().toString()
        } else {
            result.toString()
        }
        
        operation = null
        waitingForValue = true
        currentOperationButton?.isSelected = false
        currentOperationButton = null
        updateDisplay()
    }

    private fun onClearClick() {
        display = "0"
        previousValue = 0.0
        operation = null
        waitingForValue = false
        currentOperationButton?.isSelected = false
        currentOperationButton = null
        updateDisplay()
    }

    private fun onPlusMinusClick() {
        val value = display.toDoubleOrNull() ?: 0.0
        display = (-value).let {
            if (it == it.toInt().toDouble()) it.toInt().toString() else it.toString()
        }
        updateDisplay()
    }

    private fun onPercentClick() {
        val value = display.toDoubleOrNull() ?: 0.0
        display = (value / 100).toString()
        updateDisplay()
    }

    private fun updateDisplay() {
        binding.displayText.text = display
    }
}