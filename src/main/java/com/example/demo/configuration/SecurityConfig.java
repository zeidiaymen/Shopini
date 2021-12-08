package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.filter.JwtFilter;
import com.example.demo.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
    	return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
								.antMatchers("/authenticate").permitAll()
								.antMatchers("/testX").permitAll()
								.antMatchers("/users").permitAll()
								.antMatchers("/addUser").permitAll()
								.antMatchers("/savePicture").permitAll()
								.antMatchers("/getUserByEmail").permitAll()
								.antMatchers("/isEmailExist").permitAll()
								.antMatchers("/getUserByToken").permitAll()
								.antMatchers("/getPictureByEmail").permitAll()
								.antMatchers("/updateUser").permitAll()
								.antMatchers("/updateUserPicture").permitAll()
								.antMatchers("/changePasswordUser").permitAll()
								.antMatchers("/forgetPasswordRequest").permitAll()
								.antMatchers("/getAddress").permitAll()
								.antMatchers("/checkUserPasswordRequestToken").permitAll()
								.antMatchers("/addPictureBase64").permitAll()
								.antMatchers("/addUserX").permitAll()
								.antMatchers("/sendSmsTwoFactorAuthentication").permitAll()
								.antMatchers("/sendSms").permitAll()
								.antMatchers("/client/addClient").permitAll()
								.antMatchers("/fournisseur/addFournisseur").permitAll()





								

                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
}
