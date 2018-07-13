package com.example.login.user.utilities

import com.example.login.user.user.controller.UserController
import com.example.login.user.user.model.UserModel
import java.util.*

class Utils {
    companion object {
        fun getUserId() : Long{
            val rand  =  Random()
            rand.longs(10,1000)
            return rand.nextLong()
        }

        fun convertPojoArraysToHashMap(arrayOfUser : ArrayList<UserModel>) : ArrayList<HashMap<String,Any>>{
            val arrayHashMap = arrayListOf<HashMap<String,Any>>()
            for (i in arrayOfUser.indices){
                val hashMap = HashMap<String,Any>()
                hashMap[UserController.KEY_EMAIL_ID] = arrayOfUser[i].getEmailId()
                hashMap[UserController.KEY_FIRST_NAME] = arrayOfUser[i].getFirstName()
                hashMap[UserController.KEY_LAST_NAME] = arrayOfUser[i].getLastName()
                hashMap[UserController.KEY_MOBILE_NUMBER] = arrayOfUser[i].getMobileNumber()
                hashMap[UserController.KEY_PASSWORD] = arrayOfUser[i].getPassword()

                arrayHashMap.add(hashMap)
            }
            return arrayHashMap
        }
    }
}