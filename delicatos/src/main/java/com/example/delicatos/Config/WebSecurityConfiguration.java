package com.example.delicatos.Config;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.delicatos.Services.UserDetailsServiceImplementation;
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    UserDetailsServiceImplementation userDetailsService;

    @Autowired
    public WebSecurityConfiguration(UserDetailsServiceImplementation userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception{
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/","/login","/logout").permitAll();
//        http.authorizeRequests().antMatchers("/customer","/customer/**").permitAll();
//        http.authorizeRequests().antMatchers("/restaurant","/restaurant/**").access("hasAuthority('restaurant')");
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//        http.authorizeRequests().and().formLogin().loginPage("/login")
//                .defaultSuccessUrl("/welcome",true).failureUrl("/login?error=true").usernameParameter("email")
//                .passwordParameter("password").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
        http.authorizeRequests()
                .antMatchers("/users").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }
}
