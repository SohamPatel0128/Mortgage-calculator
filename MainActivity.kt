package com.example.mortgagecalculator

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ActionMode
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

var INFO_ITEM = 1234

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Activity_1
        val Amount = findViewById<TextView>(R.id.textView)
        val Years = findViewById<TextView>(R.id.textView2)
        val IntrestRate = findViewById<TextView>(R.id.textView3)
        var EAmount = findViewById<TextView>(R.id.TextView4)
        var EYears = findViewById<TextView>(R.id.TextView5)
        var EIntrestRate = findViewById<TextView>(R.id.TextView6)
        val MonthlyPayment = findViewById<TextView>(R.id.textView7)
        val TotalPayment = findViewById<TextView>(R.id.textView8)
        var ModifyData = findViewById<Button>(R.id.button)
        val budget = findViewById<TextView>(R.id.budget)
        var EbudgetAmount = findViewById<TextView>(R.id.budgetamount)
        var lay: ConstraintLayout = findViewById<ConstraintLayout>(R.id.constraintlay)

        //before moving to second page
        // Building calculator object from the intent received from another page.
        var calculator = Calculator()
        calculator.Year = getIntent().getIntExtra("year",0)
        calculator.PLA = getIntent().getDoubleExtra("pla",0.0)
        calculator.Rate = getIntent().getDoubleExtra("rate", 0.0)
        calculator.BudgetAmount = getIntent().getDoubleExtra("budgetAmount",0.0)
        calculator.MortgageCalculator()

        // Printing values for debugging.
        println("Year is " + calculator.Year)
        println("PLA is " + calculator.PLA)
        println("rate is " + calculator.Rate)
        println("budget is " + calculator.BudgetAmount)

        // Updating UI to display information on the page.
        EAmount.text = "${calculator.PLA}"
        EYears.text = "${calculator.Year}"
        EIntrestRate.text = "${calculator.Rate}"
        EbudgetAmount.text = "${calculator.BudgetAmount}"
        MonthlyPayment.text = "Monthly Payment ${calculator.MortgageCalculator()}"
        MonthlyPayment.text = "Total Payment ${calculator.Totaltotal()}"
        ModifyData.setOnClickListener { v: View ->
            val intent = Intent(this,MainActivity2::class.java)
            startActivityForResult(intent,1)
        }

        //checking user budget -> if budget fits background will turn green else red, (with a toast message)
        var mp = calculator.MortgageCalculator()
        if(mp == 0.0){
            lay.setBackgroundColor(Color.WHITE)
        }else if(EbudgetAmount.text.toString().toDouble() > calculator.MortgageCalculator()){
            lay.setBackgroundColor(Color.GREEN)
            Toast.makeText(this,"Your budget is in Range",Toast.LENGTH_SHORT).show()
        }else{
            lay.setBackgroundColor(Color.RED)
            Toast.makeText(this,"Your budget is out of Range",Toast.LENGTH_SHORT).show()
        }
    }
        //after hitting done button
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){  // Checking the activity code for the Modify Data button
            if(resultCode == 1234) {  // Checking the result code for receiving data
                var calculator = Calculator()
                var EAmount = findViewById<TextView>(R.id.TextView4)
                var EYears = findViewById<TextView>(R.id.TextView5)
                var EIntrestRate = findViewById<TextView>(R.id.TextView6)
                val MonthlyPayment = findViewById<TextView>(R.id.textView7)
                val TotalPayment = findViewById<TextView>(R.id.textView8)
                val EbudgetAmount = findViewById<TextView>(R.id.budgetamount)
                var lay: ConstraintLayout = findViewById<ConstraintLayout>(R.id.constraintlay)
                if (data != null) {
                    // If data was received then set values inside Calculator object
                    calculator.Year = data.getIntExtra("year", 0)
                    calculator.PLA = data.getDoubleExtra("pla", 0.0)
                    calculator.SetRate(data.getDoubleExtra("rate", 0.0))
                    calculator.SetBudget(data.getDoubleExtra("budgetAmount",0.0))
                }

                // Priting information for debugging.
                println("Activity 1 Year is " + calculator.Year)
                println("PLA is " + calculator.PLA)
                println("rate is " + calculator.GetRate())
                println("budget is " + calculator.GetBudget())

                // Update the UI to show the newly calculated values to useres.
                EAmount.text = "${String.format("$%.2f", calculator.PLA)}"
                EYears.text = "${{String.format("%d", calculator.Year)}}"
                EIntrestRate.text = "${String.format("%.2f", calculator.GetRate())}"
                EbudgetAmount.text = "${String.format("%.2f", calculator.GetBudget())}"
                MonthlyPayment.text = "Monthly Payment ${calculator.MortgageCalculator()}"
                TotalPayment.text = "Total Payment ${calculator.Totaltotal()}"

                // Depending on the budget, change background color.
                var mp = calculator.MortgageCalculator()
                if(mp == 0.0){
                    lay.setBackgroundColor(Color.WHITE)
                }else if(EbudgetAmount.text.toString().toDouble() > calculator.MortgageCalculator()){
                    lay.setBackgroundColor(Color.GREEN)
                    Toast.makeText(this,"Your budget is in Range",Toast.LENGTH_SHORT).show()
                }else{
                    lay.setBackgroundColor(Color.RED)
                    Toast.makeText(this,"Your budget is out of Range",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



