package com.example.mortgagecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var IAmount = findViewById<EditText>(R.id.EditText)
        var IRate = findViewById<EditText>(R.id.EditText2)
        val button_10 = findViewById<RadioButton>(R.id.radioButton)
        val button_20 = findViewById<RadioButton>(R.id.radioButton2)
        val button_30 = findViewById<RadioButton>(R.id.radioButton3)
        var Done = findViewById<Button>(R.id.button2)
        val IBudget  = findViewById<EditText>(R.id.budgetamount)
        var calculator = Calculator()
        val intent = Intent(this,MainActivity::class.java)



        button_10.setOnClickListener {
            v: View ->
            calculator.Year = 10
        }

        button_20.setOnClickListener {
                v: View ->
            calculator.Year = 20
        }

        button_30.setOnClickListener {
                v: View ->
            calculator.Year = 30
        }

        IAmount.setOnFocusChangeListener { view, b ->
            calculator.PLA = IAmount.text.toString().toDouble()
        }

        IRate.setOnFocusChangeListener { view, b ->
            calculator.SetRate(IRate.text.toString().toDouble())
        }

        IBudget.setOnFocusChangeListener { view, b ->
            calculator.SetBudget(IBudget.text.toString().toDouble())
        }

            //when user hit done button
        Done.setOnClickListener { v: View ->

            println("Year is " + calculator.Year)
            println("PLA is " + calculator.PLA)
            println("rate is " + calculator.GetRate())
            println("budget is " + calculator.GetBudget())
            //intent is used for sharing data betwen to pages
            intent.putExtra("year", calculator.Year)
            intent.putExtra("pla", calculator.PLA)
            intent.putExtra("rate", calculator.GetRate())
            intent.putExtra("budgetAmount", calculator.GetBudget())
            calculator.Totaltotal()
            calculator.saveToDb()
            setResult(1234, intent)

            this.finish()

        }
    }
}



