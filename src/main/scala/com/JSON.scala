package com


object JSON {

val jsonString = os.read(os.pwd/"Account.json")
val data = ujson.read(jsonString)
data.value 

def getDob(): String = {
data("dob").str
}

def getFname(): String = {
data("fname").str
}

def getLname(): String = {
data("lname").str
}

def getAccount_Number(): String = {
data("account_number").str
}

def getEmail(): String = {
data("email").str

}

def getAddress(): String = {
data("address").str
}




}

