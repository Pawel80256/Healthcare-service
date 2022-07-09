package com.healthcare_service.config;

import com.healthcare_service.authentication.CustomAuthenticationFilter;
import com.healthcare_service.authentication.CustomAuthorizationFilter;
import com.healthcare_service.authentication.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**").permitAll();

//        http.authorizeRequests().antMatchers("/api/visit").authenticated();
//        http.authorizeRequests().antMatchers("/api/visit/book").hasAnyAuthority("CLIENT");
//        http.authorizeRequests().antMatchers("/api/visit/complete").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers("/api/visit/{id}/cancel").hasAnyAuthority("CLIENT","DOCTOR");
//        http.authorizeRequests().antMatchers(DELETE,"/api/visit/{id}").hasAnyAuthority("DOCTOR","ADMIN");
//        http.authorizeRequests().antMatchers(POST,"/api/visit").hasAnyAuthority("DOCTOR","ADMIN");
//
//        http.authorizeRequests().antMatchers("/api/doctor/personalData").hasAnyAuthority("DOCTOR");
//        http.authorizeRequests().antMatchers("/api/doctor/aboutMe").hasAnyAuthority("DOCTOR");
//        http.authorizeRequests().antMatchers("/api/doctor/visitType").hasAnyAuthority("DOCTOR","ADMIN");
//        http.authorizeRequests().antMatchers("/api/doctor/editEverything").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(DELETE,"/api/doctor").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(POST,"/api/doctor").hasAnyAuthority("ADMIN");
//
//        http.authorizeRequests().antMatchers("/api/client/username/").authenticated();
//        http.authorizeRequests().antMatchers("/api/client/editEverything").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(DELETE, "/api/client").hasAnyAuthority("ADMIN");
//
//
//
//        http.authorizeRequests().antMatchers(POST,"/api/opinion").hasAnyAuthority("CLIENT","ADMIN");
//        http.authorizeRequests().antMatchers(DELETE,"/api/opinion/{id}").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(PUT,"/api/opinion/{id}").hasAnyAuthority("ADMIN");

        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
