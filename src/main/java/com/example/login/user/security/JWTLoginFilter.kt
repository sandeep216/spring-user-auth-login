package com.example.login.user.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/*
class JWTLoginFilter : AbstractAuthenticationProcessingFilter {

    constructor(url : String, authManager : AuthenticationManager) : super(AntPathRequestMatcher(url)){
        authenticationManager = authManager
    }

    @Throws(AuthenticationException :: class, IOException :: class, ServletException :: class)
    override fun attemptAuthentication(p0: HttpServletRequest?, p1: HttpServletResponse?): Authentication  {
       val acctCredentials : AccountCredentials = ObjectMapper().readValue(p0!!.inputStream, AccountCredentials :: class.java)
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(acctCredentials.getUserName(),acctCredentials.getPassword(), arrayListOf()))
    }

    @Throws(IOException :: class, ServletException :: class)
    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        TokenAuthenticationService.addAuthentication(response!!,authResult!!.name)
    }

}*/
