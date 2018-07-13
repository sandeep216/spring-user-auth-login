package com.example.login.user.user.controller

import com.example.login.user.ApiResponse
import com.example.login.user.ResponsePojo
import com.example.login.user.security.TokenAuthenticationService
import com.example.login.user.user.IUser
import com.example.login.user.user.model.UserModel
import com.example.login.user.user.service.UserService
import com.example.login.user.utilities.Utils
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import sun.rmi.runtime.Log
import java.security.Key
import javax.servlet.http.HttpServletRequest
import kotlin.Exception
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@RestController
@EnableWebMvc
@RequestMapping(value = "/user")
class UserController : IUser.ServiceToController {

    companion object {
        val KEY_EMAIL_ID = "email"
        val KEY_PASSWORD = "password"
        val KEY_FIRST_NAME = "f_name"
        val KEY_LAST_NAME = "l_name"
        val KEY_MOBILE_NUMBER = "m_number"
    }

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/create-user",consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createUser(request: HttpServletRequest, @RequestBody userModel: UserModel) : ResponseEntity<ResponsePojo> {
        try {
            return userService.createUser(request, userModel)
        } catch (e: Exception) {
            return ApiResponse.getUnAuthorizedResponse()
        }
    }

    @GetMapping("/")
    fun getAllUser(request: HttpServletRequest): ResponseEntity<ResponsePojo> {
        try {
            val authentication: Authentication? = TokenAuthenticationService.getAuthentication(request)
            if (authentication != null) {
                Log.getLog("UserController", "" + authentication.credentials, 0)
                val allUsers: ArrayList<UserModel> = userService.getAllUser()
                return ApiResponse.getSuccessfulResponse(Utils.convertPojoArraysToHashMap(allUsers))
            } else {
                return ApiResponse.getUnAuthorizedResponse()
            }
        } catch (e: Exception) {
            return ApiResponse.getInternalServerErrorResponse()
        }
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<Any> {
        val resp: HashMap<String, Any> = HashMap()
        try {
            val userModel = userService.getUser(id)
            if (userModel != null) {
                resp.put("success", true)
                resp.put("data", userModel)
                val response: ResponseEntity<Any> = ResponseEntity(resp, HttpStatus.OK)
                return response
            } else {
                resp.put("success", false)
                resp.put("data", UserModel())
                val response: ResponseEntity<Any> = ResponseEntity(resp, HttpStatus.OK)
                return response
            }
        } catch (e: Exception) {
            resp.put("success", false)
            resp.put("data", UserModel())
            val response: ResponseEntity<Any> = ResponseEntity(resp, HttpStatus.OK)
            return response
        }
    }
}