package com.example.calcapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Calculator() {
    var display by remember { mutableStateOf("0") }
    var previousValue by remember { mutableStateOf(0.0) }
    var operation by remember { mutableStateOf<String?>(null) }
    var waitingForValue by remember { mutableStateOf(false) }    // iOS Calculator Colors
    val darkGray = Color(0xFF333333)
    val lightGray = Color(0xFFA6A6A6)
    val orange = Color(0xFFFF9500)
    val backgroundColor = Color.Black

    fun onEqualsClick() {
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
    }

    fun onNumberClick(number: String) {
        if (waitingForValue) {
            display = number
            waitingForValue = false
        } else {
            display = if (display == "0") number else display + number
        }
    }

    fun onOperationClick(op: String) {
        if (operation != null && !waitingForValue) {
            onEqualsClick()
        }
        previousValue = display.toDoubleOrNull() ?: 0.0
        operation = op
        waitingForValue = true
    }

    fun onClearClick() {
        display = "0"
        previousValue = 0.0
        operation = null
        waitingForValue = false
    }

    fun onPlusMinusClick() {
        val value = display.toDoubleOrNull() ?: 0.0
        display = (-value).let {
            if (it == it.toInt().toDouble()) it.toInt().toString() else it.toString()
        }
    }

    fun onPercentClick() {
        val value = display.toDoubleOrNull() ?: 0.0
        display = (value / 100).toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Display
        Text(
            text = display,
            color = Color.White,
            fontSize = 80.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            maxLines = 1
        )

        // Button rows
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CalculatorButton(
                text = "AC",
                backgroundColor = lightGray,
                textColor = Color.Black,
                modifier = Modifier.weight(1f),
                onClick = { onClearClick() }
            )
            CalculatorButton(
                text = "±",
                backgroundColor = lightGray,
                textColor = Color.Black,
                modifier = Modifier.weight(1f),
                onClick = { onPlusMinusClick() }
            )
            CalculatorButton(
                text = "%",
                backgroundColor = lightGray,
                textColor = Color.Black,
                modifier = Modifier.weight(1f),
                onClick = { onPercentClick() }
            )
            CalculatorButton(
                text = "÷",
                backgroundColor = if (operation == "÷") Color.White else orange,
                textColor = if (operation == "÷") orange else Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onOperationClick("÷") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CalculatorButton(
                text = "7",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("7") }
            )
            CalculatorButton(
                text = "8",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("8") }
            )
            CalculatorButton(
                text = "9",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("9") }
            )
            CalculatorButton(
                text = "×",
                backgroundColor = if (operation == "×") Color.White else orange,
                textColor = if (operation == "×") orange else Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onOperationClick("×") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CalculatorButton(
                text = "4",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("4") }
            )
            CalculatorButton(
                text = "5",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("5") }
            )
            CalculatorButton(
                text = "6",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("6") }
            )
            CalculatorButton(
                text = "-",
                backgroundColor = if (operation == "-") Color.White else orange,
                textColor = if (operation == "-") orange else Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onOperationClick("-") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CalculatorButton(
                text = "1",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("1") }
            )
            CalculatorButton(
                text = "2",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("2") }
            )
            CalculatorButton(
                text = "3",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onNumberClick("3") }
            )
            CalculatorButton(
                text = "+",
                backgroundColor = if (operation == "+") Color.White else orange,
                textColor = if (operation == "+") orange else Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onOperationClick("+") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Zero button (takes up 2 columns)
            Box(
                modifier = Modifier
                    .weight(2f)
                    .height(80.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(darkGray)
                    .clickable { onNumberClick("0") },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "0",
                    color = Color.White,
                    fontSize = 36.sp,
                    modifier = Modifier.padding(start = 32.dp)
                )
            }
            CalculatorButton(
                text = ".",
                backgroundColor = darkGray,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { 
                    if (!display.contains(".")) {
                        onNumberClick(".")
                    }
                }
            )
            CalculatorButton(
                text = "=",
                backgroundColor = orange,
                textColor = Color.White,
                modifier = Modifier.weight(1f),
                onClick = { onEqualsClick() }
            )
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 36.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
`