package com.example.finalProject.security;

import com.example.finalProject.security.filter.CustomAuthenticationFilter;
import com.example.finalProject.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //39-40 leidzia pakeisti login url
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

//        http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
//                .and()
//                .csrf()
//                .ignoringAntMatchers("/h2-console/**")
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin().and()

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/").permitAll();
//        http.authorizeRequests().antMatchers("/api/v1/login/**", "/api/v1/token/refresh").permitAll(); // allow every user to access the
//        http.authorizeRequests().antMatchers(GET, "/api/v1/portfolios/**").hasAnyAuthority("ROLE_SUPER_ADMIN");
//        http.authorizeRequests().antMatchers(GET, "/api/v1/positions/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter); // check the user whenever they try to log in
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Kadangi pradejau nuo spring security login/logout visur reikia authentifikacijos. Overidinam metoda su websecurity
     * ir nurodom kelia kuriam nereiktu jokios authentifikacijos.
     *
     * @param web
     * @throws Exception
     */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
