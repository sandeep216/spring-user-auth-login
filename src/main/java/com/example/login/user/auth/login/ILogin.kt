package com.example.login.user.auth.login

import com.example.login.user.ResponsePojo
import com.example.login.user.user.model.UserModel
import com.fasterxml.jackson.databind.util.JSONPObject
import org.json.JSONObject
import org.springframework.http.ResponseEntity

interface ILogin {
    interface ControllerToService{
        fun authenticateUser(userModel: UserModel): ResponseEntity<ResponsePojo>
    }

    interface ServiceToController{

    }
}