package com.example.login.user.configuration

//import com.example.login.user.security.JWTAuthenticationFilter
//import com.example.login.user.security.JWTLoginFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity


@EnableWebSecurity
@Configuration
open class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Throws(Exception :: class)
    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
               /* .and()
                // We filter the api/login requests
                .addFilterBefore(JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter::class.java)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter::class.java)*/
    }

    @Throws(Exception :: class)
    override fun configure(auth: AuthenticationManagerBuilder?) {

        auth!!.inMemoryAuthentication()
                .withUser("admin")
                .password("password")
                .roles("ADMIN")
    }
}