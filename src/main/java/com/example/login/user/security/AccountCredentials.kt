package com.example.login.user.security

class AccountCredentials {

    private var userName:String? =null

    private var password : String? = null

    fun getUserName() : String?{
        return userName
    }

    fun getPassword() : String?{
        return password
    }

    fun setUserName(userName : String){
        this.userName = userName
    }

    fun setPassword(password : String){
        this.password = password
    }
}