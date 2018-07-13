package com.example.login.user.auth.login.service

import com.example.login.user.ApiResponse
import com.example.login.user.ResponsePojo
import com.example.login.user.StatusCodes
import com.example.login.user.auth.login.ILogin
import com.example.login.user.auth.login.controller.LoginController
import com.example.login.user.dao.UserDao
import com.example.login.user.security.TokenAuthenticationService
import com.example.login.user.user.model.UserModel
import com.example.login.user.utilities.Utils
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service
class LoginService : ILogin.ControllerToService {

    @Autowired
    lateinit var userDao : UserDao

    override fun authenticateUser(userModel: UserModel) : ResponseEntity<ResponsePojo> {
        try{
            if (!userModel.getEmailId().equals("")){
                val userModelList = userDao.findByEmail(userModel.getEmailId())
                if (userModelList.isEmpty()){
                    return ApiResponse.getUnsuccessfulResponse(StatusCodes.UserError.KEY_USER_DOES_NOT_EXIST,"User does not exists.",false)
                } else if (!userModelList[0].getPassword().equals(userModel.getPassword())){
                    return ApiResponse.getUnsuccessfulResponse(StatusCodes.UserError.KEY_WRONG_CREDENTIALS,"Invalid Password.",false)
                } else {
                    val arrayHashMap = Utils.convertPojoArraysToHashMap(userModelList as ArrayList<UserModel>)
                    arrayHashMap.get(0).put("access_token", TokenAuthenticationService.addAuthentication(userModelList[0].getEmailId()))
                    return ApiResponse.getSuccessfulResponse(arrayHashMap)
                }
            } else {
                return ApiResponse.getUnsuccessfulResponse(StatusCodes.ValidationStatusCodes.KEY_EMAIL_STATUS_CODE,"Email id required.",false)
            }
        }catch (e : Exception){
            return ApiResponse.getInternalServerErrorResponse()
        }
    }
}