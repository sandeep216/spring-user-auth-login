package com.example.login.user.auth.login.controller

import com.example.login.user.ApiResponse
import com.example.login.user.ResponsePojo
import com.example.login.user.auth.login.ILogin
import com.example.login.user.auth.login.service.LoginService
import com.example.login.user.user.model.UserModel
import com.fasterxml.jackson.databind.util.JSONPObject
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@RestController
@RequestMapping("user/login")
@EnableWebMvc
class LoginController : ILogin.ServiceToController{

    companion object {
        val KEY_EMAIL  = "email"
        val KEY_PASSWORD = "password"
    }

    @Autowired
    lateinit var loginService: LoginService

    @PostMapping("/")
    fun authenticate(@RequestBody userModel: UserModel) : ResponseEntity<ResponsePojo>{
        return try{
            loginService.authenticateUser(userModel)
        } catch (e : Exception){
            ApiResponse.getInternalServerErrorResponse()
        }
    }
}