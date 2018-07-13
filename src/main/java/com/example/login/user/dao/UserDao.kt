package com.example.login.user.dao

import com.example.login.user.user.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserDao : JpaRepository<UserModel,Long> {

    fun findByEmail(email : String) : List<UserModel>
    fun findByMobileNumber(mobilenumber : String) : List<UserModel>
}