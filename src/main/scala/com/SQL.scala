package com
import java.sql.DriverManager
import org.postgresql.util.PSQLException
import scala.io.StdIn

object SQL{
    
    classOf[org.postgresql.Driver].newInstance()
   val conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "303548")
//test

   def changeBalance(A: Double, B: Int, C: Int): Unit ={
     if(C==1){
       val tt = conn.prepareStatement("UPDATE Checking_Account SET Account_Balance = ? WHERE Account_number_CA = ?;")
       tt.setDouble(1, A)
       tt.setInt(2, B)
       
        tt.execute()
     }
     else{
         val tt = conn.prepareStatement("UPDATE savings_Account SET Account_Balance = ? WHERE Account_number_SA = ?;")
       tt.setDouble(1, A)
       tt.setInt(2, B)
       
        tt.execute()

     }
   }


    


   def displayBalance(A: Int, B: Int ): Double ={
      
      if(B == 1){
       val tt = conn.prepareStatement("select account_balance from Checking_account where account_number_CA =?;")
        tt.setInt(1, A)
        tt.execute()
        val rs = tt.getResultSet()
        rs.next()
        rs.getDouble("account_balance")

      }
      else{
          val tt = conn.prepareStatement("select account_balance from savings_account where account_number_SA =?;")
           tt.setInt(1, A)
        tt.execute()
        val rs = tt.getResultSet()
        rs.next()
        rs.getDouble("account_balance")


      }

      

   }


   def checkLogin(A: String, B: Int): Boolean = {
      try{
      val tt = conn.prepareStatement("select fname from Customer where account_number = ?;")

       tt.setInt(1, B)
       tt.execute()
       val rs = tt.getResultSet()
       rs.next()
      val d =  rs.getString("fname")

      if(d == A){
         return true
      }
      else{
         return false
      }

   }

   catch{
      case psql: PSQLException => return false
   }

   }

   def makeAccount(): Unit = {
       val tt = conn.prepareStatement("insert into Customer values (default, ?, ?, ?, ?, ?);")
       println("Please enter your 1st name")
       val fname = StdIn.readLine()
        tt.setString(1, fname)
        println("Please enter your last name")
       val lname = StdIn.readLine()
        tt.setString(2, lname)
        println("Please enter your address")
       val ad = StdIn.readLine()
        tt.setString(3, ad)
        println("Please enter your email")
       val email = StdIn.readLine()
        tt.setString(4, email)
        println("Please enter your DOB")
       val DOB = StdIn.readLine()
        tt.setString(5, DOB)
         tt.execute()
      val qt = conn.prepareStatement("insert into Checking_Account values (default, default);")
      qt.execute()
  
      val ft = conn.prepareStatement("insert into savings_Account values (default, default);")
      ft.execute()
      
         

       


   }

   def makeAccountImport(): Unit = {
       val tt = conn.prepareStatement("insert into Customer values (default, ?, ?, ?, ?, ?);")
       tt.setString(1, JSON.getFname())
       tt.setString(2, JSON.getLname)
       tt.setString(3, JSON.getAddress())
       tt.setString(4, JSON.getEmail())
       tt.setString(5, JSON.getDob())

        tt.execute()
         val qt = conn.prepareStatement("insert into Checking_Account values (default, default);")
      qt.execute()
  
      val ft = conn.prepareStatement("insert into savings_Account values (default, default);")
      ft.execute()

      val bb = conn.prepareStatement("select account_number from Customer where fname = ?;") 
       bb.setString(1, JSON.getFname())
       bb.execute()
       val rs = bb.getResultSet()
       rs.next()
        val d =  rs.getInt("account_number")
        println(s"Thank you for Joining Dylan's Banking app. Your account number is $d")
        println(" ")
       



   }

def deleteAccount(A: Int): Unit = {
   val tt = conn.prepareStatement("delete from customer where account_number = ?;")
    tt.setInt(1, A)
   tt.execute()
   println("Your account has been closed")
}
    
    
    

}