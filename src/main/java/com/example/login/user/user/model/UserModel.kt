package com.example.login.user.user.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class UserModel : Serializable {

    @Column(name = "user_id")
    @Id private var userId: Long = 0

    @Column(name = "f_name")
    @JsonProperty(value = "f_name")
    var fName : String = ""

    @Column(name = "l_name")
    @JsonProperty(value = "l_name")
    var lName : String = ""

    @Column(name = "email")
    @JsonProperty(value = "email")
    private var email : String = ""

    @Column(name = "m_number")
    @JsonProperty(value = "m_number")
    private var mobileNumber : String = ""

    @Column(name = "password")
    @JsonProperty(value = "password")
    private var password : String = ""

    fun setUserId(uId: Long){
        this.userId = uId
    }

    fun setFirstName(fname : String){
        this.fName = fname
    }

    fun setLastName(lname : String){
        this.lName = lname
    }

    fun setEmailId(email : String){
        this.email = email
    }

    fun setMobileNumber(mobileNumber: String){
        this.mobileNumber = mobileNumber
    }

    fun setPassword(password: String){
        this.password = password
    }

    fun getUserId() : Long{
        return userId
    }

    fun getFirstName() : String{
        return fName
    }

    fun getLastName() : String{
        return lName
    }

    fun getEmailId() : String{
        return email
    }

    fun getMobileNumber() : String{
        return mobileNumber
    }

    fun getPassword() : String{
        return password
    }
}