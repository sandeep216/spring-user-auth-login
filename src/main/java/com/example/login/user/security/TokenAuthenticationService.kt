package com.example.login.user.security

import org.apache.tomcat.jni.User.username
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class TokenAuthenticationService {
    companion object {

        val EXPIRATIONTIME: Long = 864000000 //10 days
        val SECRET = "ThisIsASecret"
        val TOKEN_PREFIX = "bearer"
        val HEADER_STRING = "Authorization"

        fun addAuthentication( userName : String) : String{
            val JWT = Jwts.builder()
                    .setSubject(userName)
                    .setExpiration(Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact()
           // res.addHeader(HEADER_STRING, "$TOKEN_PREFIX $JWT")
            return "$TOKEN_PREFIX $JWT"
        }

        fun getAuthentication(request : HttpServletRequest) : Authentication?{
            val token = request.getHeader(HEADER_STRING)
            if (token != null) {
                // parse the token.
                val user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .body
                        .subject

                return if((user != null)) UsernamePasswordAuthenticationToken(user, null, emptyList()) else null
            }
            return null
        }
    }
}