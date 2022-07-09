package com.healthcare_service.config;

import com.healthcare_service.authentication.CustomAuthenticationFilter;
import com.healthcare_service.authentication.CustomAuthorizationFilter;
import com.healthcare_service.authentication.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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
        http.authorizeRequests()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/doctor/visitType").hasAnyAuthority("DOCTOR","ADMIN")
                .antMatchers("api/doctor/{id}/bookedVisits").hasAnyAuthority("DOCTOR","ADMIN")
                .antMatchers("api/doctor/{id}/notBookedVisits").authenticated()
                .antMatchers("api/doctor/aboutMe").hasAnyAuthority("DOCTOR","ADMIN")
                .antMatchers("api/doctor/personalData").hasAnyAuthority("DOCTOR","ADMIN")
                .antMatchers("/api/visit/book").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers("/api/client/bookedVisits").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers("/api/client/visitsHistory").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers("/api/client/visitsHistory").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers(HttpMethod.POST,"/api/opinion").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/doctor").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/client").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/opinion").hasAnyAuthority("ADMIN")
                .antMatchers("/api/role").hasAnyAuthority("ADMIN");


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
