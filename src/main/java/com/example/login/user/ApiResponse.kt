package com.example.login.user

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

object ApiResponse {
    fun getUnsuccessfulResponse(errorCode : Int,message : String, success : Boolean) : ResponseEntity<ResponsePojo>{

        //Error ApiResponse
        val errorResponse = Error(errorCode,message)
        val errors = arrayListOf<Error>()
        errors.add(errorResponse)
        val responsePojo = ResponsePojo(success,errors, arrayListOf())

        return ResponseEntity(responsePojo, HttpStatus.BAD_REQUEST)
    }

    fun getValidationUnsuccessful(arrayOfErrors : ArrayList<Error>) : ResponseEntity<ResponsePojo>{
        val response = ResponsePojo(false,arrayOfErrors, arrayListOf())
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    fun getSuccessfulResponse(dataArray : ArrayList<HashMap<String,Any>>) : ResponseEntity<ResponsePojo>{
        //ApiResponse
        val response = ResponsePojo(true, arrayListOf(),dataArray)
        return ResponseEntity(response,HttpStatus.OK)
    }

    fun getUnAuthorizedResponse() : ResponseEntity<ResponsePojo>{
        val error = Error(StatusCodes.ApiStatusCode.KEY_UNAUTHORIZED,"Authorization header not found. Please check your implementation")
        val errorResponse = arrayListOf<Error>()
        errorResponse.add(error)
        //ApiResponse
        val responsePojo = ResponsePojo(false,errorResponse, arrayListOf())

        return ResponseEntity(responsePojo,HttpStatus.UNAUTHORIZED)
    }

    fun getInternalServerErrorResponse() : ResponseEntity<ResponsePojo>{
        val error = Error(StatusCodes.ApiStatusCode.KEY_SERVER_ERROR,"Internal server error")
        val errorResponse = arrayListOf<Error>()
        errorResponse.add(error)
        //ApiResponse
        val responsePojo = ResponsePojo(false,errorResponse, arrayListOf())

        return ResponseEntity(responsePojo,HttpStatus.INTERNAL_SERVER_ERROR)
    }
}