package com

import scala.io.StdIn

class CLI {

    

    def printWelcome(): Unit = {
        println("*************************************")
        println("Welcome to Dylan's Banking app")
        println("*************************************")
        print("Do you already have an account with us? yes/no: ")
        val accountCheck = StdIn.readLine()
        println(" ")

        if(accountCheck == "no"){
            println("Do you want to import your data or type it in?")
            print("Press 1 to import, press 2 to type it in:")
             val importortype = StdIn.readInt()
             println(" ")
             if(importortype== 1){
                 SQL.makeAccountImport()

             }
             else{
                   SQL.makeAccount()

             }
          

        }

      
     

    }

    def menu(): Unit = {
  
       
        
         printWelcome()
        
         println("Would you like to access your checking or savings? ")
         print("Press 1 for checking or 2 savings: ")
         val choice = StdIn.readInt()
         println(" ")
        println("Please enter your account number and 1st name to log in")
        print("Please enter your Account Number: ")
        val Accountnum = StdIn.readInt()
        println(" ")
        print("Please enter your 1st name: ")
        val fname = StdIn.readLine()
        println(" ")
        
var loopcon = true
while(loopcon == true){
    
        
        if(SQL.checkLogin(fname, Accountnum) == true){
        
        println(" ")
        printmenu()
        var input = StdIn.readInt()
        input match{
            case 1 => {
                // calls the method that handles withdraw
                withdraw()
            }

            case 2 => {
                // calls the method that handles deposit
                depo()
               }

            case 3 => {
                // calls the method that will show the balance
                val B = SQL.displayBalance(Accountnum, choice)
                 println(s"Your current account balance is $B")
              
            }

            case 4 => {
                // calls the method that will handle transfer between checking and savvings account
                transfer()
            }

            case 5 => {
                SQL.deleteAccount(Accountnum)
                loopcon = false

            }

            case 6 => {
                // exits the program
                println("Exiting...")
                loopcon = false
            }

            case _=> {
                //error checking
                println("Please choose a correct menu option")
            }
        }

    }
    else {
        //error checking
        println("Please enter valid Login details")
        loopcon = false
    }

}


    def printmenu():  Unit = {
        List("Menu Options", "1. Withdraw from account" , "2. Deposit Money into account", "3. Check balance", "4. Transfer Money to Another Account" , "5. Close Account", "6. Exit").foreach(println)
    }


    def depo(): Unit = {
        var B = SQL.displayBalance(Accountnum, choice)
                println(s"Your current account balance is $B")
                println("How much would you like to deposit?")
             var A = StdIn.readDouble()
             var c = A + B
             SQL.changeBalance(c, Accountnum, choice)

             println(s"Your new Account balance is $c")
             println(" ")

    }


    def withdraw(): Unit = {
        var B = SQL.displayBalance(Accountnum, choice)
        println(s"Your current account balance is $B")
        println("How much would you like to withdraw?")
          var A = StdIn.readDouble()
          while(A > B){
              println(s"Please pick an amount lower or equal to your current balance of $B")
              var D = StdIn.readDouble()
              A = D
          }
          var c = B - A
          SQL.changeBalance(c, Accountnum, choice)
          println(s"Your new Account balance is $c")
          println(" ")


    }


    def transfer(): Unit = {

        if(choice == 1){
        var B = SQL.displayBalance(Accountnum, 1)
         println(s"Your current account balance is $B")
        println("How much do you wish to transfer to your savings account?")
        var A = StdIn.readDouble()
        while(A > B){
              println(s"Please pick an amount lower or equal to your current balance of $B")
              var D = StdIn.readDouble()
              A = D
          }
           var c = B - A
          SQL.changeBalance(c, Accountnum, 1)
    
          var f =  SQL.displayBalance(Accountnum, 2)
          var h = f+ A
          SQL.changeBalance(h, Accountnum, 2)

          println(s"The tranfers is complete your new savings account balance is $h")
        }
        else{
             var B = SQL.displayBalance(Accountnum, 2)
         println(s"Your current account balance is $B")
         println(" ")
        println("How much do you wish to transfer to your Checking account?")
        var A = StdIn.readDouble()
        while(A > B){
              println(s"Please pick an amount lower or equal to your current balance of $B")
              var D = StdIn.readDouble()
              A = D
          }
           var c = B - A
          SQL.changeBalance(c, Accountnum, 2)
    
          var f =  SQL.displayBalance(Accountnum, 1)
          var h = f+ A
          SQL.changeBalance(h, Accountnum, 1)

          println(s"The tranfers is complete your new checking account balance is $h")
          println(" ")

        }
    }
  
}
}

