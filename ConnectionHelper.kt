package com.example.mortgagecalculator

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class ConnectionHelper {
    var conection: Connection? = null
    // variables to connnect to local MySql database
    var uname = "root"
    var pass = "1234"
    var ip = "10.0.2.2"
    var port = "3306"
    var database = "userdb"
    var table = "userinput"
    fun CreateConnection(): Connection? {
        // Since android require another thread for data access, we are changing thread
        // policy to allow sending data inside the same thread.
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        // Basic connection properties for MySQL database
        val connectionProps = Properties()
        connectionProps.put("user", uname)
        connectionProps.put("password", pass)
//        connectionProps.put("characterEncoding", "utf8")
        var con: Connection? = null
        try {
            // Using JDBC driver that will be responsible to help make connection to the DB
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            con = DriverManager.getConnection(
                "jdbc:mysql://10.0.2.2:3306/userdb?user=root&password=1234")

        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
        // Returns the connection that is ready to use.
        return con
    }
}









