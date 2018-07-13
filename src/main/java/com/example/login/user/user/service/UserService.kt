package com.example.login.user.user.service

import com.example.login.user.ApiResponse
import com.example.login.user.Error
import com.example.login.user.ResponsePojo
import com.example.login.user.StatusCodes
import com.example.login.user.user.IUser
import com.example.login.user.dao.UserDao
import com.example.login.user.security.TokenAuthenticationService
import com.example.login.user.user.controller.UserController
import com.example.login.user.user.model.UserModel
import com.example.login.user.utilities.Utils
import com.fasterxml.jackson.databind.util.JSONPObject
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest


@Service
class UserService : IUser.ControllerToService {

    @Autowired
    lateinit var userDao : UserDao

    override fun createUser(request : HttpServletRequest,jsonObject: UserModel) : ResponseEntity<ResponsePojo> {
        try{
            if (TokenAuthenticationService.getAuthentication(request) == null) {
                if (jsonObject != null) {
                    val userModel = UserModel()
                    val userHashMap = HashMap<String, Any>()
                    val errorArray = arrayListOf<Error>()
                    /*if (jsonObject.has(UserController.KEY_EMAIL_ID)) {
                        userModel.setEmailId(jsonObject.getString(UserController.KEY_EMAIL_ID))
                        userHashMap.put("email", jsonObject.getString(UserController.KEY_EMAIL_ID))
                    } else {
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_EMAIL_STATUS_CODE, "Email id required"))
                    }*/

                    //Email check
                    if (jsonObject.getEmailId().equals("")){
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_EMAIL_STATUS_CODE, "Email id required"))
                    } else if (userDao.findByEmail(jsonObject.getEmailId()).isNotEmpty()){
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_DUPLICATE_EMAIL_ID, "Email id already exists"))
                    } else {
                        userHashMap.put("email", jsonObject.getEmailId())
                    }
                   /*
                    if (jsonObject.has(UserController.KEY_FIRST_NAME)) {
                        userModel.setFirstName(jsonObject.getString(UserController.KEY_FIRST_NAME))
                        userHashMap.put("f_name", jsonObject.getString(UserController.KEY_FIRST_NAME))
                    } else {
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_FIRST_NAME_STATUS_CODE, "First Name Required"))
                    }*/

                    //First Name check
                    if (jsonObject.getFirstName().equals("")){
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_FIRST_NAME_STATUS_CODE, "First Name Required"))
                    } else {
                        userHashMap.put(UserController.KEY_FIRST_NAME, jsonObject.getFirstName())
                    }

                   /* if (jsonObject.has(UserController.KEY_LAST_NAME)) {
                        userModel.setLastName(jsonObject.getString(UserController.KEY_LAST_NAME))
                        userHashMap.put("l_name", jsonObject.getString(UserController.KEY_LAST_NAME))
                    } else {
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_LAST_NAME_STATUS_CODE, "Last Name Required"))
                    }*/

                    //Last Name check
                    if (jsonObject.getLastName().equals("")){
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_LAST_NAME_STATUS_CODE, "Last Name Required"))
                    } else {
                        userHashMap.put(UserController.KEY_LAST_NAME,jsonObject.getLastName())
                    }

                  /*  if (jsonObject.has(UserController.KEY_MOBILE_NUMBER)) {
                        userModel.setMobileNumber(jsonObject.getInt(UserController.KEY_MOBILE_NUMBER))
                        userHashMap.put("m_number", jsonObject.getInt(UserController.KEY_MOBILE_NUMBER))
                    } else {
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_MOBILE_STATUS_CODE, "Mobile Number Required"))
                    }*/

                    //Mobile Number check
                    if (jsonObject.getMobileNumber().equals("")){
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_MOBILE_STATUS_CODE, "Mobile Number Required"))
                    } else if(userDao.findByMobileNumber(jsonObject.getMobileNumber()).isNotEmpty()) {
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_DUPLICATE_MOBILE_NUMBER, "Mobile Number already exists"))
                    } else {
                        userHashMap.put(UserController.KEY_MOBILE_NUMBER,jsonObject.getMobileNumber())
                    }

                   /* if (jsonObject.has(UserController.KEY_PASSWORD)) {
                        userModel.setPassword(jsonObject.getString(UserController.KEY_PASSWORD))
                        userHashMap.put("password", jsonObject.getString(UserController.KEY_PASSWORD))
                    } else {
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_PASSWORD_VALIDATION_STATUS_CODE, "Password Required"))
                    }*/

                    //Password check
                    if (jsonObject.getPassword().equals("")){
                        errorArray.add(com.example.login.user.Error(StatusCodes.ValidationStatusCodes.KEY_PASSWORD_VALIDATION_STATUS_CODE, "Password Required"))
                    } else {
                        userHashMap.put(UserController.KEY_PASSWORD,jsonObject.getPassword())
                    }

                    if (errorArray.isEmpty()) {
                        jsonObject.setUserId(Utils.getUserId())
                        userDao.save(jsonObject)
                        userHashMap.put("access_token", TokenAuthenticationService.addAuthentication(jsonObject.getEmailId()))
                        val dataArray = ArrayList<HashMap<String, Any>>()
                        dataArray.add(userHashMap)
                        return ApiResponse.getSuccessfulResponse(dataArray)
                    } else {
                        return ApiResponse.getValidationUnsuccessful(errorArray)
                    }
                } else {
                    return ApiResponse.getUnsuccessfulResponse(StatusCodes.ValidationStatusCodes.KEY_EMAIL_STATUS_CODE,"Body Required",false)
                }
            } else {
                return ApiResponse.getUnAuthorizedResponse()
            }
        } catch (e : Exception){
            e.printStackTrace()
            return ApiResponse.getUnsuccessfulResponse(StatusCodes.ApiStatusCode.KEY_SERVER_ERROR,"Something went wrong",false)
        }
    }

    override fun getAllUser(): ArrayList<UserModel> {
        return userDao.findAll() as ArrayList<UserModel>
    }

    override fun getUser(id: Long): UserModel {
        return userDao.getOne(id)
    }

    override fun updateUserDetail(jsonpObject: JSONPObject): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}