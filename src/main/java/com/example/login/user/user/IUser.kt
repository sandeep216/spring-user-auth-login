package com.example.login.user.user

import com.example.login.user.ResponsePojo
import com.example.login.user.user.model.UserModel
import com.fasterxml.jackson.databind.util.JSONPObject
import org.json.JSONObject
import org.springframework.http.ResponseEntity
import javax.servlet.http.HttpServletRequest

interface IUser {
    interface ServiceToController{

    }


    interface ControllerToService{
        fun getAllUser() : ArrayList<UserModel>
        fun getUser(id : Long) : UserModel
        fun updateUserDetail(jsonpObject: JSONPObject) : Boolean
        fun createUser(request: HttpServletRequest, jsonObject: UserModel): ResponseEntity<ResponsePojo>
    }
}