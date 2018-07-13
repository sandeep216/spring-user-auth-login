package com.example.login.user

object StatusCodes {

    //API Status codes
    object ApiStatusCode {
        val KEY_SUCCESS_CODE = 200
        val KEY_BAD_REQUEST = 400
        val KEY_UNAUTHORIZED = 401
        val KEY_FORBIDDEN = 403
        val KEY_SERVER_ERROR = 500
    }

    //Validation Status Codes
    object ValidationStatusCodes {
        val KEY_MOBILE_STATUS_CODE = 1000
        val KEY_EMAIL_STATUS_CODE = 1001
        val KEY_FIRST_NAME_STATUS_CODE = 1002
        val KEY_LAST_NAME_STATUS_CODE = 1003
        val KEY_PASSWORD_VALIDATION_STATUS_CODE = 1004
        val KEY_OLD_PASSWORD_VALIDATION_STATUS_CODE = 1011
        val KEY_GENDER_VALIDATION = 1005
        val KEY_DOB_VALIDATION = 1016
        val KEY_INVALID_OTP_VALIDATION = 1006
        val KEY_EXPIRE_OTP_VALIDATION = 1007
        val KEY_INVALID_CREDENTIAL_VALIDATION = 1008
        val KEY_DUPLICATE_EMAIL_ID = 4000
        val KEY_DUPLICATE_MOBILE_NUMBER = 4001
    }

    object UserError{
        val KEY_USER_DOES_NOT_EXIST = 2000
        val KEY_WRONG_CREDENTIALS = 2001
    }

    /*{
        "VALIDATION": {
        "MOBILE": {
        "code": 1000,
        "type": "MOBILE_VALIDATION",
        "message": "Invalid Mobile Number"
    },
        "EMAIL": {
        "code": 1001,
        "type": "EMAIL_VALIDATION",
        "message": "Invalid Email"
    },
        "FIRST_NAME": {
        "code": 1002,
        "type": "FIRST_NAME_VALIDATION",
        "message": "Invalid First Name"
    },
        "LAST_NAME": {
        "code": 1003,
        "type": "LAST_NAME_VALIDATION",
        "message": "Invalid Last Name"
    },
        "PASSWORD": {
        "code": 1004,
        "type": "PASSWORD_VALIDATION",
        "message": "Invalid Password. Must be at least 8 characters"
    },
        "OLD_PASSWORD": {
        "code": 1011,
        "type": "OLD_PASSWORD_VALIDATION",
        "message": "Incorrect old password"
    },
        "GENDER": {
        "code": 1005,
        "type": "GENDER_VALIDATION",
        "message": "Invalid Gender"
    },
        "DOB":{
        "code": 1016,
        "type": "DOB_VALIDATION",
        "message": "Invalid Date of birth. Should be in YYYY-MM-DD format"
    },
        "INVALID_OTP": {
        "code": 1006,
        "type": "OTP_VALIDATION",
        "message": "You seem to have entered an incorrect or expired OTP. Hit resend for a new one."
    },
        "EXPIRE_OTP": {
        "code": 1007,
        "type": "OTP_EXPIRE_VALIDATION",
        "message": "Your OTP has expired. Hit resend for a different OTP."
    },
        "INVALID_CREDENTIAL": {
        "code": 1008,
        "type": "INVALID_CREDENTIAL",
        "message": "Invalid Credential"
    }
    },
        "DUPLICATE": {
        "MOBILE": {
        "code": 4000,
        "type": "DUPLICATE_MOBILE",
        "message": "Mobile Already Exists"
    },
        "EMAIL": {
        "code": 4001,
        "type": "DUPLICATE_EMAIL",
        "message": "Email Already Exists"
    }
    }
    }*/
}