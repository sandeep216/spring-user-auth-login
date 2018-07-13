package com.example.login.user


data class ResponsePojo(val success : Boolean, val errors : ArrayList<Error>, val data : ArrayList<HashMap<String,Any>>)