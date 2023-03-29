package com.example.mortgagecalculator

import java.sql.Connection
import java.sql.Statement


class Calculator {
    var M : Double = 0.00 //Total monthly Mortgage Payment
    var PLA : Double = 0.00 //Principle Loan Amount
    var n : Double = 0.00 //num of payments over Loan's LifeTime
    var Rate : Double = 0.00 //Monthly interest rate -> Annual rate/12
    var Year : Int = 0 //Total year of loan
    var Total : Double = 0.00 //Total amount Pay
    var BudgetAmount : Double = 0.00//user budget

    fun SetRate(r: Double) {
        Rate = (r/100)
    }

    fun GetRate() : Double {
        return Rate*100
    }

    fun SetBudget(b:Double) {
        BudgetAmount = (b + 0.0)
    }

    fun GetBudget() : Double {
        return BudgetAmount
    }
    fun Budget() : Double {
        return BudgetAmount
    }

    fun MortgageCalculator() : Double
    {
        n = (Year * 12.0)
        val mRate: Double = Rate / 12.0 // monthly interest rate
        val temp = Math.pow((1 / (1 + mRate)).toDouble(), Year * 12.0)
        return PLA * mRate / (1 - temp).toFloat()
    }

    fun Totaltotal() : Double
    {
        n = (Year * 12).toDouble()
        M = MortgageCalculator()
        Total = M * n
        return Total
    }



    fun saveToDb() {
        val con = ConnectionHelper() //creats the connection
        val connection = con.CreateConnection() //and help us to send data to database
        // INSERT INTO `userdb`.`userinput` (`id`, `year`, `amount`, `intrestRate`, `montlyPayment`, `totalPayment`) VALUES ('1', '2022', '1000', '3', '130', '1220');
        var query = "INSERT INTO `userdb`.`userinput` (`year`, `amount`, `intrestRate`, `montlyPayment`, `totalPayment`, `budgetAmount`) VALUES ("
        query = query + "'" + Year + "', "
        query = query + "'" + PLA + "', "
        query = query + "'" + Rate + "', "
        query = query + "'" + MortgageCalculator() + "', "
        query = query + "'" + Totaltotal() + "', "
        query = query + "'" + BudgetAmount + "');"
        println("query"+query)
        val statement = connection!!.createStatement();//query statment
        val result = statement.execute(query);//execute inserting query into db
        print(result.toString());
    }
}